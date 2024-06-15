package kyh.ecommerce.search.application.port

import kyh.ecommerce.search.domain.Product

interface StoreProductPort {
    fun save(products: List<Product>)
}
