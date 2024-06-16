package kyh.ecommerce.search.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.opensearch.client.RestClient
import org.opensearch.client.json.jackson.JacksonJsonpMapper
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.transport.rest_client.RestClientTransport
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.apache.http.HttpHost

@Configuration
@EnableConfigurationProperties(OpenSearchProperties::class)
class OpenSearchConfig(
    private val openSearchProperties: OpenSearchProperties
) {
    @Bean
    fun openSearchObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule())

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return objectMapper
    }

    @Bean
    fun openSearchClient(openSearchObjectMapper: ObjectMapper): OpenSearchClient {
        val (host, port, schema) = openSearchProperties
        val restClient = RestClient.builder(HttpHost(host, port, schema))
            .build()

        return OpenSearchClient(
            RestClientTransport(restClient, JacksonJsonpMapper(openSearchObjectMapper))
        )
    }
}
