package kyh.ecommerce.search.application.port

import kyh.ecommerce.search.domain.Product

interface StorePort {
    fun save(products: List<Product>)
}
