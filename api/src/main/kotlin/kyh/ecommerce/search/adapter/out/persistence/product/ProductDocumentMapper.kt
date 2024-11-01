package kyh.ecommerce.search.adapter.out.persistence.product

import kyh.ecommerce.search.adapter.out.client.dto.GetFeaturesResponse
import kyh.ecommerce.search.document.ProductDocument
import kyh.ecommerce.search.domain.Product

class ProductDocumentMapper {
    companion object {
        fun toDocument(
            product: Product,
            response: List<GetFeaturesResponse.Feature>
        ): ProductDocument {
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
                product.imageUrl,
                product.getSearchKeywords(),
                response.find { it.itemId == product.id }?.imageFeatures,
                response.find { it.itemId == product.id }?.laserAllKeywordsTextFeatures,
                response.find { it.itemId == product.id }?.laserItemNamesTextFeatures,
                response.find { it.itemId == product.id }?.distiluseAllKeywordsTextFeatures,
                response.find { it.itemId == product.id }?.distiluseItemNamesTextFeatures,
                response.find { it.itemId == product.id }?.e5AllKeywordsTextFeatures,
                response.find { it.itemId == product.id }?.e5ItemNamesTextFeatures,
            )
        }

        fun ProductDocument.toDomain(): Product {
            return Product(
                productNo,
                gender,
                mainCategory,
                subCategory,
                articleType,
                baseColor,
                season,
                year,
                usage,
                productDisplayName,
                imageUrl,
            )
        }
    }
}
