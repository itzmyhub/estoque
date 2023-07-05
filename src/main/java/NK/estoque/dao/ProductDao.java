package NK.estoque.dao;

import NK.estoque.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {

    private static Product product = new Product(
            "Evaporador",
            new BigDecimal("1000.0"),
            4,
            100,
            true
    );

    public List<Product> getAllProducts() {
        return Arrays.asList(product);
    }
}
