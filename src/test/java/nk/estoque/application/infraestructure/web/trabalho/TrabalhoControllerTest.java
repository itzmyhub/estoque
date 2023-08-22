package nk.estoque.application.infraestructure.web.trabalho;

import com.fasterxml.jackson.databind.ObjectMapper;
import nk.estoque.application.infraestructure.entity.Trabalho;
import nk.estoque.application.infraestructure.web.BaseWebControllerTest;
import nk.estoque.domain.model.trabalho.TodosTrabalhos;
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

@WebMvcTest(TrabalhoController.class)
public class TrabalhoControllerTest extends BaseWebControllerTest {

    private static final BigDecimal MAO_DE_OBRA = new BigDecimal("100.0");

    private static final long ID = 1L;

    private static final long ID_2 = 2L;

    @MockBean
    private TodosTrabalhos todosTrabalhos;

    @Test
    void deve_listar_trabalhos() throws Exception {
        Trabalho trabalho = Trabalho.builder()
                .id(ID)
                .maoDeObra(MAO_DE_OBRA)
                .build();

        Trabalho trabalho2 = Trabalho.builder()
                .id(ID_2)
                .maoDeObra(MAO_DE_OBRA)
                .build();

        when(todosTrabalhos.listaPaginada()).thenReturn(List.of(trabalho, trabalho2));

        ResultActions resultActions = mockMvc.perform(get("/trabalhos").header("Authorization", "Bearer " + authorizedToken));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", equalTo(1)));
    }

    @Test
    void deve_criar_um_novo_trabalho() throws Exception {
        Trabalho trabalho = Trabalho.builder()
                .id(ID)
                .maoDeObra(MAO_DE_OBRA)
                .build();

        when(todosTrabalhos.criarTrabalho(trabalho)).thenReturn(trabalho);

        ObjectMapper objectMapper = new ObjectMapper();
        String trabalhoJSON = objectMapper.writeValueAsString(trabalho);

        ResultActions resultActions = mockMvc.perform(post("/trabalhos").header("Authorization", "Bearer " + authorizedToken).contentType(MediaType.APPLICATION_JSON).content(trabalhoJSON));

        resultActions.andExpect(status().isCreated());
        verify(todosTrabalhos).criarTrabalho(any(Trabalho.class));
    }

    @Test
    void deve_atualizar_um_trabalho() throws Exception {
        Trabalho trabalho = Trabalho.builder()
                .id(ID)
                .maoDeObra(MAO_DE_OBRA)
                .build();

        when(todosTrabalhos.atualizarTrabalho(trabalho.getId(), trabalho)).thenReturn(trabalho);

        ObjectMapper objectMapper = new ObjectMapper();
        String trabalhoJSON = objectMapper.writeValueAsString(trabalho);

        ResultActions resultActions = mockMvc.perform(put("/trabalhos/{id}", trabalho.getId()).header("Authorization", "Bearer " + authorizedToken).contentType(MediaType.APPLICATION_JSON).content(trabalhoJSON));

        resultActions.andExpect(status().isOk());
        verify(todosTrabalhos).atualizarTrabalho(eq(1L), any(Trabalho.class));
    }

    @Test
    void deve_excluir_um_trabalho() throws Exception {
        Trabalho trabalho = Trabalho.builder()
                .id(ID)
                .maoDeObra(MAO_DE_OBRA)
                .build();

        doNothing().when(todosTrabalhos).deletarTrabalho(trabalho.getId());

        ResultActions resultActions = mockMvc.perform(delete("/trabalhos/{id}", trabalho.getId()).header("Authorization", "Bearer " + authorizedToken));
        resultActions.andExpect(status().isNoContent());
        verify(todosTrabalhos, times(1)).deletarTrabalho(trabalho.getId());
    }
}
