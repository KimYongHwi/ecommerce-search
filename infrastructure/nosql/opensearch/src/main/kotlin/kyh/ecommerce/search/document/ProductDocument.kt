package kyh.ecommerce.search.document

import org.springframework.data.annotation.Id

data class ProductDocument(
    @Id
    val productNo: Long,

    val gender: String,

    val mainCategory: String,

    val subCategory: String,

    val articleType: String,

    val baseColor: String,

    val season: String,

    val year: String,

    val usage: String,

    val productDisplayName: String,

    val image: String,
)
