package kyh.ecommerce.search.message

import kyh.ecommerce.search.domain.Product

data class ProductMessage(
    val id: Long,
    val gender: String,
    val mainCategory: String,
    val subCategory: String,
    val articleType: String,
    val baseColor: String,
    val season: String,
    val year: String,
    val usage: String,
    val productDisplayName: String,
    val image: String
) {
    companion object {
        fun toMessage(product: Product): ProductMessage {
            return ProductMessage(
                id = product.id,
                gender = product.gender,
                mainCategory = product.mainCategory,
                subCategory = product.subCategory,
                articleType = product.articleType,
                baseColor = product.baseColor,
                season = product.season,
                year = product.year,
                usage = product.usage,
                productDisplayName = product.productDisplayName,
                image = product.image
            )
        }
    }
}
