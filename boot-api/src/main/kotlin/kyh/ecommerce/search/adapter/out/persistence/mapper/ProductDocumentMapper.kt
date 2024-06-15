package kyh.ecommerce.search.adapter.out.persistence.mapper

import kyh.ecommerce.search.adapter.out.client.dto.GetFeaturesResponse
import kyh.ecommerce.search.document.ProductDocument
import kyh.ecommerce.search.domain.Product

class ProductDocumentMapper {
    companion object {
        fun toDocument(product: Product, response: GetFeaturesResponse): ProductDocument {
            return ProductDocument(
                product.id,
                product.gender,
                product.mainCategory,
                product.subCategory,
                product.articleType,
                product.baseColor,
                product.season,
                product.year,
                product.usage,
                product.productDisplayName,
                product.image,
                response.featureMap[product.id]?.imageFeatures,
                response.featureMap[product.id]?.textFeatures
            )
        }
    }
}
