package kyh.ecommerce.search.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "opensearch")
data class OpenSearchProperties(
    val host: String,
    val port: Int,
    val schema: String
)
