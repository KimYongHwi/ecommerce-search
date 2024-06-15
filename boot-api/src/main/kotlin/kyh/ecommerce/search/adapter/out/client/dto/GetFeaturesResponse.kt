package kyh.ecommerce.search.adapter.out.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetFeaturesResponse(
    val featureMap: Map<Long, Features>
) {
    data class Features(
        @JsonProperty("image_features")
        val imageFeatures: List<Double>,

        @JsonProperty("text_features")
        val textFeatures: List<Double>,
    )
}
