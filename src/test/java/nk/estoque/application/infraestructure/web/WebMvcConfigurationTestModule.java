package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.security.JwtTokenUtil;
import nk.estoque.application.infraestructure.service.UsuarioService;
import nk.estoque.domain.model.cliente.TodosClientes;
import nk.estoque.domain.model.funcionario.TodosFuncionarios;
import nk.estoque.domain.model.trabalho.TodosTrabalhos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import static org.mockito.Mockito.mock;

@Configuration
public class WebMvcConfigurationTestModule {
    @Autowired private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public TodosFuncionarios todosFuncionarios() {
        return mock(TodosFuncionarios.class);
    }

    @Bean
    public TodosTrabalhos todosTrabalhos() {
        return mock(TodosTrabalhos.class);
    }

    @Bean
    public UsuarioService usuarioService() {
        return mock(UsuarioService.class);
    }

    @Bean
    public TodosClientes todosClientes() { return mock(TodosClientes.class); }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
