package kyh.ecommerce.search.application.service

import kyh.ecommerce.search.application.port.SearchPort
import kyh.ecommerce.search.application.usecase.SearchUseCase
import kyh.ecommerce.search.application.usecase.dto.Parameter
import kyh.ecommerce.search.application.usecase.dto.ProductSearchResult
import org.springframework.stereotype.Service

@Service
class SearchService(
    val searchPort: SearchPort,
) : SearchUseCase {

    override fun search(parameter: Parameter.Search): ProductSearchResult {
        return searchPort.search(parameter)
    }
}
