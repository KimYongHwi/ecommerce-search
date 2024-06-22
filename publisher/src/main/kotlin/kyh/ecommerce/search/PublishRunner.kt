package kyh.ecommerce.search

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.FileNotFoundException
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
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val objectMapper = ObjectMapper()
        val filePath = "products/fashion-product-images-small.csv"
        val inputStream = javaClass.classLoader.getResourceAsStream(filePath)
            ?: throw FileNotFoundException("File not found: $filePath")

        val messages = csvReader().readAllWithHeader(inputStream).map {
            ProductMessage(
                id = it["id"]!!.toLong(),
                gender = it["gender"]!!.toString(),
                mainCategory = it["masterCategory"]!!.toString(),
                subCategory = it["subCategory"]!!.toString(),
                articleType = it["articleType"]!!.toString(),
                baseColor = it["baseColour"]!!.toString(),
                season = it["season"]!!.toString(),
                year = it["year"]!!.toString(),
                usage = it["usage"]!!.toString(),
                productDisplayName = it["productDisplayName"]!!.toString(),
                imageUrl = it["link"]!!.toString(),
            )
        }

        messages.forEach {
            kafkaTemplate.send(productsTopic, objectMapper.writeValueAsString(it))
        }
    }
}
