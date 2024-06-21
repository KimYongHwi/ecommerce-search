package kyh.ecommerce.search.application.usecase

import kyh.ecommerce.search.application.usecase.dto.Parameter
import kyh.ecommerce.search.application.usecase.dto.ProductSearchResult

interface SearchUseCase {

    fun search(parameter: Parameter.Search): ProductSearchResult
}
