package cn.edu.neu.learn.doc;

import cn.edu.neu.bean.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author 32098
 */
public class DocInsert {
    public static void main(String[] args) throws IOException {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("student").id("1001");

        Student student = new Student("zs", "男性", 30);
        ObjectMapper mapper = new ObjectMapper();
        indexRequest.source(mapper.writeValueAsString(student), XContentType.JSON);

        IndexResponse indexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(indexResponse.getResult());

        // Close ES Client
        esClient.close();
    }
}
