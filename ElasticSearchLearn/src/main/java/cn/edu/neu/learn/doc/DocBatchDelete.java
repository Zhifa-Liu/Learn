package cn.edu.neu.learn.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author 32098
 */
public class DocBatchDelete {
    public static void main(String[] args) throws IOException {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("student").id("1001"));
        request.add(new DeleteRequest().index("student").id("1002"));
        request.add(new DeleteRequest().index("student").id("1003"));

        BulkResponse responses = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(responses.getItems()));

        // Close ES Client
        esClient.close();
    }
}
