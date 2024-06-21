package kyh.ecommerce.search.adapter.`in`.web.product.dto

import kyh.ecommerce.search.domain.Product

class ProductSearchDto {

    data class Request(val keyword: String?, val image: String?)

    data class Response(val products: List<Product>, val total: Long)
}
