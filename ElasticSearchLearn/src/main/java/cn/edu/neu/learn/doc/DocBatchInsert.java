package cn.edu.neu.learn.doc;

import cn.edu.neu.bean.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author 32098
 */
public class DocBatchInsert {
    public static void main(String[] args) throws IOException {
        // Create ES Client
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("master", 9200))
        );

        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("student").id("1001").source(XContentType.JSON, "name", "ls", "gender", "女性", "age", 30));
        request.add(new IndexRequest().index("student").id("1002").source(XContentType.JSON, "name", "mhb", "gender", "男性", "age", 20));
        request.add(new IndexRequest().index("student").id("1003").source(XContentType.JSON, "name", "shj", "gender", "男性", "age", 40));
        request.add(new IndexRequest().index("student").id("1004").source(XContentType.JSON, "name", "lax", "gender", "女性", "age", 25));
        request.add(new IndexRequest().index("student").id("1005").source(XContentType.JSON, "name", "xzl", "gender", "男性", "age", 20));
        request.add(new IndexRequest().index("student").id("1006").source(XContentType.JSON, "name", "zss", "gender", "女性", "age", 36));

        BulkResponse responses = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(Arrays.toString(responses.getItems()));

        // Close ES Client
        esClient.close();
    }
}
