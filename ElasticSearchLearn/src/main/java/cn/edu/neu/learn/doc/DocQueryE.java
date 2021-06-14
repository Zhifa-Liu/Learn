package cn.edu.neu.learn.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author 32098
 *
 * 组合查询
 */
public class DocQueryE {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        SearchRequest request = new SearchRequest();
        request.indices("student");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // boolQueryBuilder.must(QueryBuilders.matchQuery("age", 20));
        // boolQueryBuilder.must(QueryBuilders.matchQuery("gender", "男性"));
        // boolQueryBuilder.mustNot(QueryBuilders.matchQuery("gender", "女性"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 20));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 30));
        searchSourceBuilder.query(boolQueryBuilder);

        request.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for(SearchHit hit: hits){
            System.out.println(hit.getSourceAsString());
        }
        restHighLevelClient.close();
    }
}
