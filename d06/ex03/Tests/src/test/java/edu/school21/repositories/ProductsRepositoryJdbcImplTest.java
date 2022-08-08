package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ProductsRepositoryJdbcImplTest {

    private ProductsRepository productsRepository;
    private EmbeddedDatabase dataSource;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Mike", 75),
            new Product(1L, "Tom", 50),
            new Product(2L, "Tim", 200),
            new Product(3L, "John", 100),
            new Product(4L, "Bob", 150)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(0L, "Mike", 75);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "John", 100);

    @BeforeEach
    public void init() throws SQLException {
         dataSource = new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.HSQL).
                setScriptEncoding("UTF-8").
                addScript("schema.sql").addScript("data.sql").build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource.getConnection());
    }

    @Test
    public void testFindAll(){
        try {
            assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById(){
        try {
            assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(0L).get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        try {
            Product product = new Product(3L, "John", 100);
            productsRepository.update(product);
            assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(product.getId()).get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave(){
        try {
            int sizeBefore = productsRepository.findAll().size();
            productsRepository.save(new Product(5L, "Bob", 170));
            int sizeAfter = productsRepository.findAll().size();
            assertEquals(sizeBefore, sizeAfter - 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {;
        try {
            int sizeBefore = productsRepository.findAll().size();
            productsRepository.delete(4L);
            int sizeAfter = productsRepository.findAll().size();
            assertEquals(sizeBefore, sizeAfter + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void finish(){
        dataSource.shutdown();
    }
}
