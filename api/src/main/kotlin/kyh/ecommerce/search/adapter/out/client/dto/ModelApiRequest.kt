package kyh.ecommerce.search.adapter.out.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ModelApiRequest {

    data class GetAllFeaturesRequest(
        @JsonProperty("item_ids")
        val itemIds: List<Long>,
        val images: List<String>,
        val texts: List<String>,
    )

    data class GetImageFeatureRequest(val images: List<String>)

    data class GetTextFeatureRequest(val texts: List<String>)
}
