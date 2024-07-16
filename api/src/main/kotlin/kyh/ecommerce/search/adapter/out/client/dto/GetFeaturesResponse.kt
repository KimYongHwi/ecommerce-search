package kyh.ecommerce.search.adapter.out.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

class GetFeaturesResponse {
    data class Feature(
        @JsonProperty("item_id")
        val itemId: Long,

        @JsonProperty("image_features")
        val imageFeatures: List<Double>,

        @JsonProperty("text_features")
        val textFeatures: List<Double>,
    )

    data class TextFeatures(
        @JsonProperty("text_features")
        val textFeatures: List<Float>,
    )

    data class ImageFeatures(
        @JsonProperty("text_features")
        val imageFeatures: List<Float>
    )
}
