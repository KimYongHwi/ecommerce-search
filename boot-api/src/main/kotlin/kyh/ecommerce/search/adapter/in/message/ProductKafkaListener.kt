package kyh.ecommerce.search.adapter.`in`.message

import kyh.ecommerce.search.adapter.`in`.message.ProductMessageMapper.Companion.toDomain
import kyh.ecommerce.search.application.usecase.StoreUseCase
import kyh.ecommerce.search.config.KafkaConfig
import kyh.ecommerce.search.message.ProductMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProductKafkaListener(
    val storeUseCase: StoreUseCase
) {
    @KafkaListener(
        topics = ["\${spring.kafka.topics.products}"],
        containerFactory = KafkaConfig.PRODUCT_BATCH_LISTENER
    )
    fun listenProducts(messages: List<ProductMessage>) {
        val products = messages.map { it.toDomain() }
        storeUseCase.save(products)
    }
}
