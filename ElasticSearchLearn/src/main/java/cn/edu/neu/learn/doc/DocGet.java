package cn.edu.neu.learn.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author 32098
 */
public class DocGet {
    public static void main(String[] args) throws IOException {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        GetRequest request = new GetRequest();
        request.index("student").id("1001");
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());

        // Close ES Client
        esClient.close();
    }
}
