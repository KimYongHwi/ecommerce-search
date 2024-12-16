package kyh.ecommerce.search.adapter.out.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

class GetFeaturesResponse {
    data class Feature(
        @JsonProperty("item_id")
        val itemId: Long,

        @JsonProperty("image_features")
        val imageFeatures: List<Double>,

        @JsonProperty("laser_all_keywords_text_features")
        val laserAllKeywordsTextFeatures: List<Double>?,

        @JsonProperty("laser_item_names_text_features")
        val laserItemNamesTextFeatures: List<Double>?,

        @JsonProperty("distiluse_all_keywords_text_features")
        val distiluseAllKeywordsTextFeatures: List<Double>?,

        @JsonProperty("distiluse_item_names_text_features")
        val distiluseItemNamesTextFeatures: List<Double>?,

        @JsonProperty("e5_all_keywords_text_features")
        val e5AllKeywordsTextFeatures: List<Double>?,

        @JsonProperty("e5_item_names_text_features")
        val e5ItemNamesTextFeatures: List<Double>?,

    )

    data class TextFeatures(
        @JsonProperty("distiluse_all_keywords_text_features")
        val distiluseAllKeywordsTextFeatures: List<Float>,

        @JsonProperty("e5_all_text_features")
        val e5AllTextFeatures: List<Float>,

        @JsonProperty("laser_all_keywords_text_features")
        val laserAllKeywordsTextFeatures: List<Float>,
    )

    data class ImageFeatures(
        @JsonProperty("text_features")
        val imageFeatures: List<Float>
    )
}
