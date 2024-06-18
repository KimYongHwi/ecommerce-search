package kyh.ecommerce.search.adapter.`in`.web.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController {
    @GetMapping("/search")
    fun search() {

    }
}
