package kyh.ecommerce.search.adapter.`in`.message

import kyh.ecommerce.search.domain.Product
import kyh.ecommerce.search.message.ProductMessage

class ProductMessageMapper {
    companion object {
        fun ProductMessage.toDomain(): Product {
            return Product(
                id,
                gender,
                mainCategory,
                subCategory,
                articleType,
                baseColor,
                season,
                year,
                usage,
                productDisplayName,
                imageUrl
            )
        }
    }
}
