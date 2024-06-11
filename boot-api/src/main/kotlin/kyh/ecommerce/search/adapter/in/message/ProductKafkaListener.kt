package kyh.ecommerce.search.adapter.`in`.message

import kyh.ecommerce.search.config.KafkaConfig
import kyh.ecommerce.search.message.ProductMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProductKafkaListener {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(
        topics = ["\${spring.kafka.topics.products}"],
        containerFactory = KafkaConfig.PRODUCT_BATCH_LISTENER
    )
    fun listenProducts(messages: List<ProductMessage>) {
        messages.forEach {
            logger.info("product: ${it.id}")
        }
    }
}
