package cn.edu.neu.learn.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

/**
 * @author 32098
 */
public class DocQueryAgg {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        SearchRequest request = new SearchRequest();
        request.indices("student");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        searchSourceBuilder.aggregation(maxAggregationBuilder);
//        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
//        searchSourceBuilder.aggregation(termsAggregationBuilder);

        request.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response);
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit: hits){
            System.out.println(hit.getSourceAsString());
        }
        restHighLevelClient.close();
    }
}
