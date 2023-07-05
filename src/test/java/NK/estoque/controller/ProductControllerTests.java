package NK.estoque.controller;

import NK.estoque.model.Product;
import NK.estoque.service.IProductService;
import NK.estoque.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class ProductControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IProductService productService;

    @Test
    void getAllProductsTest() throws Exception {
        given(productService.getAllProducts())
                .willReturn(Arrays.asList(new Product(
                        "Evaporador",
                        new BigDecimal("1000.0"),
                        4,
                        100,
                        true
                )));
        mockMvc.perform(get("/products/all"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray());
        verify(productService).getAllProducts();
    }

}
