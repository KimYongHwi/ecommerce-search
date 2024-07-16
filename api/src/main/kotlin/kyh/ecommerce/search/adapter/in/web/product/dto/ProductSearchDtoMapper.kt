package kyh.ecommerce.search.adapter.`in`.web.product.dto

import kyh.ecommerce.search.application.usecase.dto.Parameter

class ProductSearchDtoMapper {

    companion object {
        fun ProductSearchDto.Request.toParam(): Parameter.Search {
            return Parameter.Search(
                keyword,
                page,
                size
            )
        }
    }
}
