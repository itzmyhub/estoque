package nk.estoque.infraestructure.web;

import NK.estoque.domain.produto.Produto;
import NK.estoque.domain.produto.TodosProdutos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        Produto produto1 = new Produto();
        produto1.setNome("Evaporador");
        Produto produto2 = new Produto();
        produto2.setNome("Compressor");
        when(todosProdutos.listaPaginada()).thenReturn(List.of(produto1, produto2));

        ResultActions resultActions = mockMvc.perform(get("/produtos"));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.[0].nome", equalTo("Evaporador")));
        resultActions.andExpect(jsonPath("$.[1].nome", equalTo("Compressor")));
    }

    @Test
    void deve_criar_um_produto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Evaporador");
        produto.setId(1L);
        produto.setCodigoProduto(120);
        produto.setValor(new BigDecimal("1000.0"));
        produto.setQuantidadeItens(2);
        when(todosProdutos.criar(any(Produto.class))).thenReturn(produto);

        ObjectMapper objectMapper = new ObjectMapper();
        String produtoJSON = objectMapper.writeValueAsString(produto);

        ResultActions resultActions = mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(produtoJSON));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo("Evaporador")));
    }

    @Test
    void deve_atualizar_um_produto() throws Exception {
        Produto produto = Produto.builder()
                .id(1L)
                .nome("Evaporador")
                .valor(new BigDecimal("500.0"))
                .codigoProduto(110)
                .quantidadeItens(2).build();
        when(todosProdutos.atualizarProduto(produto.getId(), produto)).thenReturn(produto);

        ObjectMapper objectMapper = new ObjectMapper();
        String produtoJSON = objectMapper.writeValueAsString(produto);

        ResultActions resultActions = mockMvc.perform(put("/produtos/{id}", produto.getId()).contentType(MediaType.APPLICATION_JSON).content(produtoJSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo("Evaporador")));

        //aprender a fazer o verify pq ta foda
    }

    @Test
    void deve_deletar_um_produto() throws Exception{
        Produto produto = Produto.builder()
                .id(1L)
                .nome("Evaporador")
                .valor(new BigDecimal("500.0"))
                .codigoProduto(110)
                .quantidadeItens(2).build();

        doNothing().when(todosProdutos).deletarProduto(produto.getId());

        ResultActions resultActions = mockMvc.perform(delete("/produtos/{id}", produto.getId()));

        resultActions.andExpect(status().isOk());
        verify(todosProdutos, times(1)).deletarProduto(produto.getId());
    }

}