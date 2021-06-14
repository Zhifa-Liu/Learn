package cn.edu.neu;

import cn.edu.neu.bean.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 32098
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESIndexTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Test
    public void createIndex(){
        System.out.println("create index");
    }

    @Test
    public void deleteIndex(){
        boolean flg = elasticsearchRestTemplate.deleteIndex(Product.class);
        System.out.println("deleted index ? " + flg);
    }
}
