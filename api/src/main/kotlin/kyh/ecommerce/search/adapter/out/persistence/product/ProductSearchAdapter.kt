package kyh.ecommerce.search.adapter.out.persistence.product

import kyh.ecommerce.search.adapter.out.client.ModelApiClient
import kyh.ecommerce.search.adapter.out.client.dto.ModelApiRequest
import kyh.ecommerce.search.adapter.out.persistence.product.ProductDocumentMapper.Companion.toDomain
import kyh.ecommerce.search.application.port.SearchPort
import kyh.ecommerce.search.application.usecase.dto.Parameter
import kyh.ecommerce.search.application.usecase.dto.ProductSearchResult
import kyh.ecommerce.search.document.ProductDocument
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery
import org.opensearch.client.opensearch._types.query_dsl.FunctionScoreQuery
import org.opensearch.client.opensearch._types.query_dsl.HybridQuery
import org.opensearch.client.opensearch._types.query_dsl.KnnQuery
import org.opensearch.client.opensearch._types.query_dsl.MatchQuery
import org.opensearch.client.opensearch._types.query_dsl.Query
import org.opensearch.client.opensearch.core.SearchRequest
import org.opensearch.client.opensearch.core.search.SourceConfig
import org.opensearch.client.opensearch.core.search.SourceFilter
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
        val response = modelApiClient.getFeatures(
            request = ModelApiRequest.GetTextFeatureRequest(listOf(parameter.keyword))
        ).first()

        val bQuery = BoolQuery.of { bqBuilder ->
            bqBuilder.must { qBuilder ->
                qBuilder.match { matchQb -> matchQb.withKeyword(parameter.keyword) }
            }
        }.toQuery()

        val fsQuery = FunctionScoreQuery.of { fsQb ->
            fsQb.query { qb -> qb.knn { knnQb -> knnQb.withTextFeatures(response.textFeatures) } }
        }.toQuery()

        val query = Query.Builder()
            .hybrid(
                HybridQuery.Builder()
                    .queries(listOf(bQuery, fsQuery))
                    .build()
            )
            .build()

        val request = SearchRequest.of {
            it.index(indexName)
                .source(
                    SourceConfig.of { builder ->
                        builder.filter { fBuilder -> fBuilder.excludeFeatures() }
                    }
                )
                .query(query)
                .from(parameter.getFrom())
                .size(parameter.size)
        }

        val hits = openSearchClient.search(request, ProductDocument::class.java)
        val products = hits.hits().hits().mapNotNull { it.source()?.toDomain() }

        return ProductSearchResult(products = products, total = hits.hits().total().value())
    }

    private fun MatchQuery.Builder.withKeyword(keyword: String) = apply {
        this.field("searchKeywords").query { it.stringValue(keyword) }
    }

    private fun KnnQuery.Builder.withTextFeatures(features: List<Float>) = apply {
        this.field("textFeatures")
            .vector(features.toFloatArray())
            .k(10000)
    }

    private fun SourceFilter.Builder.excludeFeatures() = apply {
        this.excludes(listOf("imageFeatures", "textFeatures"))
    }
}
