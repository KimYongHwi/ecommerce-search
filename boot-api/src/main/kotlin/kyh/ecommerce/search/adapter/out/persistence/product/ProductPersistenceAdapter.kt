package kyh.ecommerce.search.adapter.out.persistence.product

import kyh.ecommerce.search.adapter.out.client.ModelApiClient
import kyh.ecommerce.search.adapter.out.client.dto.GetFeaturesRequest
import kyh.ecommerce.search.application.port.StorePort
import kyh.ecommerce.search.domain.Product
import org.opensearch.action.bulk.BulkRequest
import org.opensearch.action.index.IndexRequest
import org.opensearch.action.support.WriteRequest
import org.opensearch.client.RequestOptions
import org.opensearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Repository
class ProductPersistenceAdapter(
    val modelApiClient: ModelApiClient,
    val openSearchClient: RestHighLevelClient,
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

        val indexRequests = products
            .map { ProductDocumentMapper.toDocument(it, response) }
            .map {
                IndexRequest(indexName)
                    .source(it)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
            }

        openSearchClient.bulk(BulkRequest().add(indexRequests), RequestOptions.DEFAULT)
    }
}
