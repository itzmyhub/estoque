package nk.estoque.application.infraestructure.web.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import nk.estoque.application.infraestructure.entity.Pedido;
import nk.estoque.domain.pedido.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
public class TrabalhoControllerTest {

    private static final BigDecimal MAO_DE_OBRA = new BigDecimal("100.0");

    private static final long ID = 1L;

    private static final long ID_2 = 2L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService todosTrabalhos;

    @Test
    void deve_listar_trabalhos() throws Exception {
        Pedido trabalho = Pedido.builder()
                .id(ID)
                .valorAdicional(MAO_DE_OBRA)
                .build();

        Pedido trabalho2 = Pedido.builder()
                .id(ID_2)
                .valorAdicional(MAO_DE_OBRA)
                .build();

        when(todosTrabalhos.listaPaginada()).thenReturn(List.of(trabalho, trabalho2));

        ResultActions resultActions = mockMvc.perform(get("/trabalhos"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", equalTo(1)));
    }

    @Test
    void deve_criar_um_novo_trabalho() throws Exception {
        Pedido trabalho = Pedido.builder()
                .id(ID)
                .valorAdicional(MAO_DE_OBRA)
                .build();

        when(todosTrabalhos.criarPedido(trabalho)).thenReturn(trabalho);

        ObjectMapper objectMapper = new ObjectMapper();
        String trabalhoJSON = objectMapper.writeValueAsString(trabalho);

        ResultActions resultActions = mockMvc.perform(post("/trabalhos").contentType(MediaType.APPLICATION_JSON).content(trabalhoJSON));

        resultActions.andExpect(status().isCreated());
        verify(todosTrabalhos).criarPedido(any(Pedido.class));
    }

    @Test
    void deve_atualizar_um_trabalho() throws Exception {
        Pedido trabalho = Pedido.builder()
                .id(ID)
                .valorAdicional(MAO_DE_OBRA)
                .build();

        when(todosTrabalhos.atualizarPedido(trabalho.getId(), trabalho)).thenReturn(trabalho);

        ObjectMapper objectMapper = new ObjectMapper();
        String trabalhoJSON = objectMapper.writeValueAsString(trabalho);

        ResultActions resultActions = mockMvc.perform(put("/trabalhos/{id}", trabalho.getId()).contentType(MediaType.APPLICATION_JSON).content(trabalhoJSON));

        resultActions.andExpect(status().isOk());
        verify(todosTrabalhos).atualizarPedido(eq(1L), any(Pedido.class));
    }

    @Test
    void deve_excluir_um_trabalho() throws Exception {
        Pedido trabalho = Pedido.builder()
                .id(ID)
                .valorAdicional(MAO_DE_OBRA)
                .build();

        doNothing().when(todosTrabalhos).deletarPedido(trabalho.getId());

        ResultActions resultActions = mockMvc.perform(delete("/trabalhos/{id}", trabalho.getId()));
        resultActions.andExpect(status().isNoContent());
        verify(todosTrabalhos, times(1)).deletarPedido(trabalho.getId());
    }
}
