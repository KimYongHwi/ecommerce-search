package kyh.ecommerce.search.application.service

import kyh.ecommerce.search.application.port.StorePort
import kyh.ecommerce.search.application.usecase.StoreUseCase
import kyh.ecommerce.search.domain.Product
import org.springframework.stereotype.Service

@Service
class StoreService(
    val storePort: StorePort
): StoreUseCase {
    override fun save(products: List<Product>) {
        storePort.save(products)
    }
}
