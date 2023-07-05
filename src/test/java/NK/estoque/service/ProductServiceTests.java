package NK.estoque.service;

import NK.estoque.dao.ProductDao;
import NK.estoque.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class ProductServiceTests {

    @MockBean
    ProductDao productDao;

    @Autowired
    IProductService productService;

    @Configuration
    public static class Config {
        @Bean
        public IProductService getProductService() {
            return new ProductService();
        }
    }

    @Test
    void getAllProductsTest() {
        Product product = new Product(
                "Evaporador",
                new BigDecimal("1000.0"),
                4,
                100,
                true
        );
        given(productDao.getAllProducts())
                .willReturn(Arrays.asList(product));
        assertEquals(Arrays.asList(product), productService.getAllProducts());
    }
}
