package kyh.ecommerce.search.adapter.`in`.message

import kyh.ecommerce.search.adapter.`in`.message.ProductMessageMapper.Companion.toDomain
import kyh.ecommerce.search.application.usecase.IndexingUseCase
import kyh.ecommerce.search.config.KafkaConfig
import kyh.ecommerce.search.message.ProductMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class ProductKafkaListener(
    val indexingUseCase: IndexingUseCase
) {
    @KafkaListener(
        topics = ["\${spring.kafka.topics.products}"],
        containerFactory = KafkaConfig.PRODUCT_BATCH_LISTENER
    )
    fun listenProducts(@Payload messages: List<ProductMessage>) {
        val products = messages.map { it.toDomain() }
        indexingUseCase.indexing(products)
    }
}
