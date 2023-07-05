package nk.estoque.infraestructure.web;

import nk.estoque.domain.produto.Produto;
import nk.estoque.domain.produto.TodosProdutos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

}