package kyh.ecommerce.search.application.port

import kyh.ecommerce.search.domain.Product

interface IndexingPort {

    fun indexing(products: List<Product>)
}
