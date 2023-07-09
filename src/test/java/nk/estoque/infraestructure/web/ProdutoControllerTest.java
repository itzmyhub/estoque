package nk.estoque.infraestructure.web;

import NK.estoque.domain.produto.Produto;
import NK.estoque.domain.produto.TodosProdutos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodosProdutos todosProdutos;

    @Test
    void deve_listar_produtos() throws Exception {
        Produto produto1 = new Produto("Evaporador", new BigDecimal("1000.0"), 1, 100, true);
        Produto produto2 = new Produto("Filtro", new BigDecimal("100.0"), 2, 50, true);
        when(todosProdutos.listaPaginada()).thenReturn(List.of(produto1, produto2));

        ResultActions resultActions = mockMvc.perform(get("/produtos"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.[0].nome", equalTo("Evaporador")));
        resultActions.andExpect(jsonPath("$.[1].nome", equalTo("Compressor")));
    }

//    @Test
//    void deve_criar_um_produto() throws Exception {
//        Produto produto = new Produto();
//        produto.setNome("Filtro");
//        doNothing().when(todosProdutos).criarProduto(produto);
//
//        ResultActions resultActions = mockMvc.perform(post("/produtos"));
//
//        resultActions.andExpect(status().isCreated());
//    }
//
//    @Test
//    void deve_atualizar_um_produto() throws Exception {
//        Produto produto = new Produto();
//        produto.setNome("Filtro");
//        when(todosProdutos.atualizarProduto((long) eq(1), produto)).thenReturn(produto);
//
//        ResultActions resultActions = mockMvc.perform(put("/produtos/{id}", 1));
//
//        resultActions.andExpect(status().isOk());
//        verify(todosProdutos, times(1)).atualizarProduto((long) eq(1), any(Produto.class));
//    }

}