package kyh.ecommerce.search.application.port

import kyh.ecommerce.search.application.usecase.dto.Parameter
import kyh.ecommerce.search.application.usecase.dto.ProductSearchResult

interface SearchPort {

    fun search(parameter: Parameter.Search): ProductSearchResult
}
