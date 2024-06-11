package kyh.ecommerce.search.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.opensearch.client.RestHighLevelClient
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration
import org.opensearch.data.client.orhlc.ClientConfiguration
import org.opensearch.data.client.orhlc.RestClients
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
class OpenSearchConfig(
    private val openSearchProperties: OpenSearchProperties
): AbstractOpenSearchConfiguration() {
    @Bean
    fun openSearchObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule())

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return objectMapper
    }

    @Bean
    override fun opensearchClient(): RestHighLevelClient {
        val config =  ClientConfiguration.builder()
            .connectedTo("${openSearchProperties.host}:${openSearchProperties.port}")
            .build();

        return RestClients.create(config).rest();
    }
}
