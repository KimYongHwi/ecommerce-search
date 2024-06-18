package kyh.ecommerce.search.adapter.out.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetFeaturesRequest(
    @JsonProperty("item_ids")
    val itemIds: List<Long>,
    val images: List<String>,
    val texts: List<String>,
)
