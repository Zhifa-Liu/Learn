package cn.edu.neu;

import cn.edu.neu.bean.Product;
import cn.edu.neu.dao.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 32098
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESProductDaoTest {

    @Autowired
    private ProductDao productDao;

    /**
     * 新增
     *
     * POSTMAN, GET http://localhost:9200/product/_doc/2
     */
    @Test
    public void save(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("华为手机");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImagePath("http://www.atguigu/hw.jpg");
        productDao.save(product);
    }

    /**
     * 更新
     *
     * POSTMAN, GET http://localhost:9200/product/_doc/2
     */
    @Test
    public void update(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("小米手机");
        product.setCategory("手机");
        product.setPrice(9999.0);
        product.setImagePath("http://www.atguigu/xm.jpg");
        productDao.save(product);
    }

    /**
     * 查询: By id
     *
     */
    @Test
    public void findById(){
        Product product = productDao.findById(2L).get();
        System.out.println(product);
    }

    /**
     * 查询：All
     */
    @Test
    public void findAll(){
        Iterable<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * 删除
     *
     */
    @Test
    public void delete(){
        Product product = new Product();
        product.setId(2L);
        productDao.delete(product);
    }


    /**
     * 批量新增
     */
    @Test
    public void saveAll(){
        List<Product> productList = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(i);
            product.setTitle("小米[\"+i+\"]");
            product.setCategory("手机");
            product.setPrice(1999.0 + i);
            product.setImagePath("http://www.atguigu/xm.jpg");
            productList.add(product);
        }
        productDao.saveAll(productList);
    }

    /**
     * 分页查询
     *
     */
    @Test
    public void findByPageable(){
        // 设置排序(排序方式，正序还是倒序，排序的 id)
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        // 当前页，第一页从 0 开始， 1 表示第二页
        int currentPage=0;
        // 每页显示多少条
        int pageSize = 5;
        // 设置查询分页
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize,sort);
        // 分页查询
        Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product product : productPage.getContent()) {
            System.out.println(product);
        }
    }
}

