package cn.edu.neu.learn;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author 32098
 */
public class ESClientDemo {
    public static void main(String[] args) throws IOException {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        // Close ES Client
        esClient.close();
    }
}
