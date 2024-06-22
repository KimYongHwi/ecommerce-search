package kyh.ecommerce.search.adapter.out.persistence.product

import kyh.ecommerce.search.adapter.out.client.ModelApiClient
import kyh.ecommerce.search.adapter.out.client.dto.ModelApiRequest
import kyh.ecommerce.search.application.port.IndexingPort
import kyh.ecommerce.search.domain.Product
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch._types.Refresh
import org.opensearch.client.opensearch.core.BulkRequest
import org.opensearch.client.opensearch.core.bulk.BulkOperation
import org.opensearch.client.opensearch.core.bulk.IndexOperation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Repository
class ProductIndexingAdapter(
    val modelApiClient: ModelApiClient,
    val openSearchClient: OpenSearchClient,
    @Value("\${opensearch.index.products.name}")
    val indexName: String
) : IndexingPort {
    override fun indexing(products: List<Product>) {
        val response = modelApiClient.getFeatures(
            ModelApiRequest.GetAllFeaturesRequest(
                itemIds = products.map { it.id },
                imageUrls = products.map { it.imageUrl },
                texts = products.map { it.getSearchKeywords() }
            )
        )

        val bulkOps = products
            .map { ProductDocumentMapper.toDocument(it, response) }
            .map {
                BulkOperation.Builder().index(
                    IndexOperation.of { io -> io.index(indexName).document(it) }
                ).build()
            }

        openSearchClient.bulk(
            BulkRequest.Builder()
                .index(indexName)
                .operations(bulkOps)
                .refresh(Refresh.True).build()
        )
    }
}
