package kyh.ecommerce.search.adapter.`in`.web.product

import kyh.ecommerce.search.adapter.`in`.web.product.dto.ProductSearchDto
import kyh.ecommerce.search.application.usecase.SearchUseCase
import kyh.ecommerce.search.application.usecase.dto.Parameter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    val searchUseCase: SearchUseCase
) {

    @GetMapping("/search")
    fun search(request: ProductSearchDto.Request): ProductSearchDto.Response {
        val result = searchUseCase.search(
            Parameter.Search(keyword = request.keyword, image = request.image)
        )

        return ProductSearchDto.Response(products = result.products, total = result.total)
    }
}
