package cn.edu.neu.learn.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author 32098
 */
public class CreateIndex {
    public static void main(String[] args) throws Exception {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        CreateIndexResponse createIndexRequest = esClient.indices().create(new CreateIndexRequest("student"), RequestOptions.DEFAULT);
        System.out.println(createIndexRequest.isAcknowledged());

        // Close ES Client
        esClient.close();
    }
}
