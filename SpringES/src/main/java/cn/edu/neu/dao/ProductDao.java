package cn.edu.neu.dao;

import cn.edu.neu.bean.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 32098
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Long> {

}
