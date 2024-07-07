package kyh.ecommerce.search.adapter.out.persistence.product

import kyh.ecommerce.search.adapter.out.client.ModelApiClient
import kyh.ecommerce.search.adapter.out.persistence.product.ProductDocumentMapper.Companion.toDomain
import kyh.ecommerce.search.application.port.SearchPort
import kyh.ecommerce.search.application.usecase.dto.Parameter
import kyh.ecommerce.search.application.usecase.dto.ProductSearchResult
import kyh.ecommerce.search.document.ProductDocument
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery
import org.opensearch.client.opensearch.core.SearchRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Repository
class ProductSearchAdapter(
    val modelApiClient: ModelApiClient,
    val openSearchClient: OpenSearchClient,
    @Value("\${opensearch.index.products.name}")
    val indexName: String
) : SearchPort {

    override fun search(parameter: Parameter.Search): ProductSearchResult {
        val query = BoolQuery.of { bqBuilder ->
            bqBuilder
                .must { qBuilder ->
                    qBuilder.match { matchQueryBuilder ->
                        matchQueryBuilder
                            .field("searchKeywords")
                            .query { it.stringValue(parameter.keyword) }
                    }
                }
        }._toQuery()

        val request = SearchRequest.of {
            it.index(indexName).query(query).from(parameter.getFrom()).size(parameter.size)
        }
        val hits = openSearchClient.search(request, ProductDocument::class.java)
        val products = hits.hits().hits().mapNotNull { it.source()?.toDomain() }

        return ProductSearchResult(products = products, total = hits.hits().total().value())
    }
}
