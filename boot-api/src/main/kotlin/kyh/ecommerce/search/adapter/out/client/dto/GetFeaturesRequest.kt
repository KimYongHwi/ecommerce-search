package kyh.ecommerce.search.adapter.out.client.dto

data class GetFeaturesRequest(
    val itemIds: List<Long>,
    val images: List<String>,
    val texts: List<String>,
)
