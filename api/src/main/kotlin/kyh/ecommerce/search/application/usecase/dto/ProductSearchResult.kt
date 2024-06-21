package kyh.ecommerce.search.application.usecase.dto

import kyh.ecommerce.search.domain.Product

data class ProductSearchResult(
    val products: List<Product>,
    val total: Long,
)
