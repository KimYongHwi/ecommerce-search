package kyh.ecommerce.search.document

data class ProductDocument(
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

    val imageUrl: String,

    val searchKeywords: String,

    val imageFeatures: List<Double>?,

    val distiluseTextFeatures: List<Double>?,

    val e5TextFeatures: List<Double>?
)
