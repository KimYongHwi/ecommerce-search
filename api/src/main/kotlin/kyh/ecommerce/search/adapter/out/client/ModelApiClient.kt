package kyh.ecommerce.search.adapter.out.client

import kyh.ecommerce.search.adapter.out.client.dto.GetFeaturesResponse
import kyh.ecommerce.search.adapter.out.client.dto.ModelApiRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "modelApiClient",
    url = "\${feign.url.model-api}",
)
interface ModelApiClient {

    @PostMapping("/features")
    fun getFeatures(@RequestBody request: ModelApiRequest.GetAllFeaturesRequest): List<GetFeaturesResponse.Feature>

    @PostMapping("/image/features")
    fun getFeatures(@RequestBody request: ModelApiRequest.GetImageFeatureRequest): List<GetFeaturesResponse.ImageFeatures>

    @PostMapping("/text/features")
    fun getFeatures(@RequestBody request: ModelApiRequest.GetTextFeatureRequest): List<GetFeaturesResponse.TextFeatures>
}
