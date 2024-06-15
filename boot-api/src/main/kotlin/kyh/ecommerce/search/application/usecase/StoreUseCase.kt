package kyh.ecommerce.search.application.usecase

import kyh.ecommerce.search.domain.Product

interface StoreUseCase {
    fun save(products: List<Product>)
}
