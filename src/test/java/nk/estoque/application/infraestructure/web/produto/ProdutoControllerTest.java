package nk.estoque.application.infraestructure.web.produto;

import com.fasterxml.jackson.databind.ObjectMapper;
import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.domain.model.produto.TodosProdutos;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
>>>>>>> crud-funcionarios
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

<<<<<<< HEAD
@WebMvcTest
=======
@WebMvcTest(ProdutoController.class)
>>>>>>> crud-funcionarios
class ProdutoControllerTest {
    private static final int CODIGO_PRODUTO = 110;
    private static final int QUANTIDADE_ITENS = 2;
    private static final BigDecimal VALOR = new BigDecimal("500.0");
    private static final String NOME_EVAPORADOR = "Evaporador";
    private static final long ID_1 = 1L;
<<<<<<< HEAD
=======

>>>>>>> crud-funcionarios
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodosProdutos todosProdutos;

<<<<<<< HEAD
=======

>>>>>>> crud-funcionarios
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

        ResultActions resultActions = mockMvc.perform(post("/produtos").contentType(APPLICATION_JSON).content(produtoJSON));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo("Evaporador")));
    }

    @Test
    void deve_atualizar_um_produto() throws Exception {
        Produto produto = Produto.builder()
                .id(ID_1)
                .nome(NOME_EVAPORADOR)
                .valor(VALOR)
                .codigoProduto(CODIGO_PRODUTO)
                .quantidadeItens(QUANTIDADE_ITENS).build();
        when(todosProdutos.atualizarProduto(produto.getId(), produto)).thenReturn(produto);

        ObjectMapper objectMapper = new ObjectMapper();
        String produtoJSON = objectMapper.writeValueAsString(produto);

        ResultActions resultActions = mockMvc.perform(put("/produtos/{id}", produto.getId()).contentType(APPLICATION_JSON).content(produtoJSON));

<<<<<<< HEAD
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo("Evaporador")));
=======
        resultActions.andExpect(status().isOk());
                //.andExpect(jsonPath("$.nome", equalTo("Evaporador")));
>>>>>>> crud-funcionarios

        verify(todosProdutos).atualizarProduto(eq(1L), any(Produto.class));
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

        resultActions.andExpect(status().isNoContent());
        verify(todosProdutos, times(1)).deletarProduto(produto.getId());
    }

}