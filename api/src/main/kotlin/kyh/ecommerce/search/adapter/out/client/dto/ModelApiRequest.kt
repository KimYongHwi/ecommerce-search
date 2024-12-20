package kyh.ecommerce.search.adapter.out.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ModelApiRequest {

    data class GetAllFeaturesRequest(
        @JsonProperty("item_ids")
        val itemIds: List<Long>,
        @JsonProperty("image_urls")
        val imageUrls: List<String>,
        @JsonProperty("texts")
        val texts: List<String>,
        @JsonProperty("item_names")
        val itemNames: List<String>,
    )

    data class GetImageFeatureRequest(val images: List<String>)

    data class GetTextFeatureRequest(
        val texts: List<String>
    )
}
