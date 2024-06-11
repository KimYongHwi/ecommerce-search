package kyh.ecommerce.search

import com.fasterxml.jackson.databind.ObjectMapper
import com.opencsv.CSVReaderBuilder
import java.io.FileNotFoundException
import java.io.InputStreamReader
import kyh.ecommerce.search.domain.Product
import kyh.ecommerce.search.message.ProductMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PublishRunner(
    val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${spring.kafka.topics.products}")
    val productsTopic: String
): ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val objectMapper = ObjectMapper()
        val filePath = "products/fashion-product-images-small.csv"
        val inputStream = javaClass.classLoader.getResourceAsStream(filePath)
            ?: throw FileNotFoundException("File not found: $filePath")

        val products = CSVReaderBuilder(InputStreamReader(inputStream))
            .withSkipLines(1)
            .build()
            .readAll()
            .map { line ->
                Product(
                    id = line[0].toLong(),
                    gender = line[1].toString(),
                    mainCategory = line[2].toString(),
                    subCategory = line[3].toString(),
                    articleType = line[4].toString(),
                    baseColor = line[5].toString(),
                    season = line[6].toString(),
                    year = line[7].toString(),
                    usage = line[8].toString(),
                    productDisplayName = line[9].toString(),
                    image = line[10].toString(),
                )
            }

        products.forEach {
            kafkaTemplate.send(
                productsTopic,
                objectMapper.writeValueAsString(ProductMessage.toMessage(it))
            )
        }
    }
}
