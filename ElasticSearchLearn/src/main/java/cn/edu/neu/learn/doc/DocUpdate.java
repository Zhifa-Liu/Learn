package cn.edu.neu.learn.doc;

import cn.edu.neu.bean.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class DocUpdate {
    public static void main(String[] args) throws IOException {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        UpdateRequest request = new UpdateRequest();
        request.index("student").id("1001");
        request.doc(XContentType.JSON, "gender", "女性");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());

        // Close ES Client
        esClient.close();
    }
}
