package cn.edu.neu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 32098
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document(indexName = "product", shards = 1, replicas = 1)
public class Product {
    @Id
    private Long id;
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Keyword) // 关键词，不能进行分词
    private String category;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Keyword, index = false) // index=false: can not query by using imagePath
    private String imagePath;
}
