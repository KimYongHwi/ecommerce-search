package kyh.ecommerce.search.application.usecase

import kyh.ecommerce.search.domain.Product

interface IndexingUseCase {
    fun indexing(products: List<Product>)
}
