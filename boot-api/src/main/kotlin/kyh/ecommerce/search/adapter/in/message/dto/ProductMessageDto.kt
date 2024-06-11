package kyh.ecommerce.search.adapter.`in`.message.dto

data class ProductMessageDto(
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
)