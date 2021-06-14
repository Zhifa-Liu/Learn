package cn.edu.neu.learn.index;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


import java.io.IOException;

/**
 * @author 32098
 */
public class DeleteIndex {
    public static void main(String[] args) throws IOException {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        AcknowledgedResponse getIndexResponse = esClient.indices().delete(new DeleteIndexRequest("student"), RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.isAcknowledged());

        // Close ES Client
        esClient.close();
    }
}

