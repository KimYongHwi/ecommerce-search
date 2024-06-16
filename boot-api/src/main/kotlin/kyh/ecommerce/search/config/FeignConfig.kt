package kyh.ecommerce.search.config

import feign.Retryer
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableFeignClients(basePackages = ["kyh.ecommerce.search"])
class FeignConfig {
    @Bean
    fun retryer(): Retryer {
        return Retryer.Default(PERIOD, MAX_PERIOD, MAX_ATTEMPTS)
    }

    companion object {
        const val PERIOD = 1_000L
        const val MAX_PERIOD = 2_000L
        const val MAX_ATTEMPTS = 3
    }
}
