package kyh.ecommerce.search.application.service

import kyh.ecommerce.search.application.port.IndexingPort
import kyh.ecommerce.search.application.usecase.IndexingUseCase
import kyh.ecommerce.search.domain.Product
import org.springframework.stereotype.Service

@Service
class IndexingService(
    val indexingPort: IndexingPort
) : IndexingUseCase {
    private val chunkSize = 100

    override fun indexing(products: List<Product>) {
        products.chunked(chunkSize).forEach {
            indexingPort.indexing(it)
            Thread.sleep(50)
        }
    }
}
