package kyh.ecommerce.search.adapter.out.persistence.product

import kyh.ecommerce.search.adapter.out.client.ModelApiClient
import kyh.ecommerce.search.adapter.out.client.dto.GetFeaturesRequest
import kyh.ecommerce.search.application.port.StorePort
import kyh.ecommerce.search.domain.Feature
import kyh.ecommerce.search.domain.Product
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch._types.Refresh
import org.opensearch.client.opensearch.core.bulk.BulkOperation
import org.opensearch.client.opensearch.core.bulk.IndexOperation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Repository
class ProductPersistenceAdapter(
    val modelApiClient: ModelApiClient,
    val openSearchClient: OpenSearchClient,
    @Value("\${opensearch.index.products.name}")
    val indexName: String
) : StorePort {
    override fun save(products: List<Product>) {
        val response = modelApiClient.getFeatures(
            GetFeaturesRequest(
                itemIds = products.map { it.id },
                images = products.map { it.image },
                texts = products.map { it.productDisplayName }
            )
        )

        val bulkOps = products
            .map { ProductDocumentMapper.toDocument(it, response) }
            .map {
                BulkOperation.Builder().index(
                    IndexOperation.of { io ->
                        io.index(indexName).document(it)
                    }
                ).build()
            }

        openSearchClient.bulk(
            org.opensearch.client.opensearch.core.BulkRequest.Builder()
                .index(indexName)
                .operations(bulkOps)
                .refresh(Refresh.True).build()
        )
    }
}
