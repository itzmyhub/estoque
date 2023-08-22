package nk.estoque.application.infraestructure.web.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import nk.estoque.application.infraestructure.entity.Cliente;
import nk.estoque.application.infraestructure.security.JwtTokenUtil;
import nk.estoque.application.infraestructure.service.JwtUserDetailsService;
import nk.estoque.application.infraestructure.web.BaseWebControllerTest;
import nk.estoque.domain.model.cliente.TodosClientes;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmRootEntityType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest extends BaseWebControllerTest {

    private static final Long ID_1 = 1L;

    private static final String NOME = "italo machado vilarino";

    private static final String CPF = "04511025339";

    private static final String EMAIL = "italomachadovilarino@gmail.com";

    private static final String TELEFONE = "(63)99228-2171";

    private static final Long ID_SERVICO = 1L;

    @MockBean
    TodosClientes todosClientes;


    @Test
    void deve_listar_os_clientes() throws Exception {
        Cliente cliente = Cliente.builder()
                .id(ID_1)
                .nome(NOME)
                .cpf(CPF)
                .email(EMAIL)
                .telefone(TELEFONE)
                .id_servico(ID_SERVICO).build();

        when(todosClientes.listaPaginada()).thenReturn(List.of(cliente, cliente));

        ResultActions resultActions = mockMvc.perform(get("/clientes").header("Authorization", "Bearer " + authorizedToken));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.[0].nome", equalTo("italo machado vilarino")));

    }

    @Test
    void deve_criar_um_novo_cliente() throws Exception {
        Cliente cliente = Cliente.builder()
                .id(ID_1)
                .nome(NOME)
                .cpf(CPF)
                .email(EMAIL)
                .telefone(TELEFONE)
                .id_servico(ID_SERVICO)
                .build();

        when(todosClientes.novoCliente(any(Cliente.class))).thenReturn(cliente);

        ObjectMapper objectMapper = new ObjectMapper();
        String clienteJSON = objectMapper.writeValueAsString(cliente);

        ResultActions resultActions = mockMvc.perform(post("/clientes").header("Authorization", "Bearer " + authorizedToken).contentType(APPLICATION_JSON).content(clienteJSON));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf", equalTo("04511025339")));
    }

    @Test
    void deve_atualizar_um_cliente() throws Exception {
        Cliente cliente = Cliente.builder()
                .id(ID_1)
                .nome(NOME)
                .cpf(CPF)
                .email(EMAIL)
                .telefone(TELEFONE)
                .id_servico(ID_SERVICO)
                .build();

        when(todosClientes.atualizarCliente(cliente.getId(), cliente)).thenReturn(cliente);

        ObjectMapper objectMapper = new ObjectMapper();
        String clienteJSON = objectMapper.writeValueAsString(cliente);

        ResultActions resultActions = mockMvc.perform(put("/clientes/{id}", cliente.getId()).header("Authorization", "Bearer " + authorizedToken).contentType(APPLICATION_JSON).content(clienteJSON));

        resultActions.andExpect(status().isOk());

        verify(todosClientes).atualizarCliente(eq(1L), any(Cliente.class));
    }

    @Test
    void deve_excluir_um_cliente() throws Exception {
        Cliente cliente = Cliente.builder()
                .id(ID_1)
                .nome(NOME)
                .cpf(CPF)
                .email(EMAIL)
                .telefone(TELEFONE)
                .id_servico(ID_SERVICO)
                .build();

        doNothing().when(todosClientes).excluirCliente(cliente.getId());

        ResultActions resultActions = mockMvc.perform(delete("/clientes/{id}", cliente.getId()).header("Authorization", "Bearer " + authorizedToken));

        resultActions.andExpect(status().isNoContent());
        verify(todosClientes, times(1)).excluirCliente(eq(1L));
    }
}
