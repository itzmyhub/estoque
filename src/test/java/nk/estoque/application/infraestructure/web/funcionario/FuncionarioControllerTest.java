package nk.estoque.application.infraestructure.web.funcionario;

import com.fasterxml.jackson.databind.ObjectMapper;
import nk.estoque.application.infraestructure.entity.Funcionario;
import nk.estoque.application.infraestructure.web.BaseWebControllerTest;
import nk.estoque.domain.model.funcionario.TodosFuncionarios;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTest extends BaseWebControllerTest {

    private static final Long ID_1 = 1L;

    private static final String NOME = "italo machado vilarino";

    private static final String CPF = "04511025339";

    private static final BigDecimal SALARIO = new BigDecimal("1200.0");

    private static final String TELEFONE = "(63)99228-2171";

    @MockBean
    private TodosFuncionarios todosFuncionarios;

    @Test
    void deve_listar_os_funcionarios() throws Exception{
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Jhonny");
        when(todosFuncionarios.listaPaginada()).thenReturn(List.of(funcionario));

        ResultActions result = mockMvc.perform(get("/funcionarios").header("Authorization", "Bearer " + authorizedToken)
                .contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.[0].nome", equalTo("Jhonny")));
    }

    @Test
    void deve_criar_um_funcionario() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .id(ID_1)
                .nome(NOME)
                .salario(SALARIO)
                .telefone(TELEFONE)
                .cpf(CPF).build();

        when(todosFuncionarios.criarFuncionario(funcionario)).thenReturn(funcionario);

        ObjectMapper objectMapper = new ObjectMapper();
        String funcionarioJSON = objectMapper.writeValueAsString(funcionario);

        ResultActions resultActions = mockMvc.perform(post("/funcionarios").header("Authorization", "Bearer " + authorizedToken).contentType(MediaType.APPLICATION_JSON).content(funcionarioJSON));
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void deve_atualizar_um_funcionario() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .id(ID_1)
                .nome(NOME)
                .salario(SALARIO)
                .telefone(TELEFONE)
                .cpf(CPF).build();

        when(todosFuncionarios.atualizarFuncionario(funcionario.getId(), any(Funcionario.class))).thenReturn(funcionario);

        ObjectMapper objectMapper = new ObjectMapper();
        String funcionarioJSON = objectMapper.writeValueAsString(funcionario);

        ResultActions resultActions = mockMvc.perform(put("/funcionarios/{id}", funcionario.getId()).header("Authorization", "Bearer " + authorizedToken).contentType(MediaType.APPLICATION_JSON).content(funcionarioJSON));

        resultActions.andExpect(status().isOk());
                //.andExpect(jsonPath("$.nome", equalTo("italo machado vilarino")));

        verify(todosFuncionarios).atualizarFuncionario(eq(1L), any(Funcionario.class));
    }

    @Test
    void deve_excluir_um_funcionario() throws Exception{
        Funcionario funcionario = Funcionario.builder()
                .id(ID_1)
                .nome(NOME)
                .salario(SALARIO)
                .telefone(TELEFONE)
                .cpf(CPF).build();

        doNothing().when(todosFuncionarios).excluirFuncionario(funcionario.getId());

        ResultActions resultActions = mockMvc.perform(delete("/funcionarios/{id}", funcionario.getId()).header("Authorization", "Bearer " + authorizedToken));

        resultActions.andExpect(status().isNoContent());

        verify(todosFuncionarios, times(1)).excluirFuncionario(funcionario.getId());
    }
}
