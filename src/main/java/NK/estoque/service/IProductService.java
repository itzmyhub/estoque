package NK.estoque.service;

import NK.estoque.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    List<Product> getAllProducts();

}
