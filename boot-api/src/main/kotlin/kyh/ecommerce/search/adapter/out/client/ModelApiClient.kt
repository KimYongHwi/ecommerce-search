package kyh.ecommerce.search.adapter.out.client

import kyh.ecommerce.search.adapter.out.client.dto.GetFeaturesRequest
import kyh.ecommerce.search.adapter.out.client.dto.GetFeaturesResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "modelApiClient", url = "\${feign.url.model-api}")
interface ModelApiClient {

    @PostMapping("/features")
    fun getFeatures(@RequestBody getFeatures: GetFeaturesRequest): GetFeaturesResponse
}
