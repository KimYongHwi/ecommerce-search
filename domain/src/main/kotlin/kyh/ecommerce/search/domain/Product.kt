package kyh.ecommerce.search.domain

data class Product(
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
    fun getSearchKeywords(): String {
        return "${this.gender} ${this.mainCategory} ${this.subCategory} ${this.articleType} ${this.baseColor} ${this.season} ${this.usage} ${this.productDisplayName}"
    }
}
