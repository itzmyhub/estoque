package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.security.JwtTokenUtil;
import nk.estoque.application.infraestructure.service.UsuarioService;
import nk.estoque.application.infraestructure.service.funcionario.FuncionarioService;
import nk.estoque.application.infraestructure.service.pedido.PedidoService;
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
    public FuncionarioService todosFuncionarios() {
        return mock(FuncionarioService.class);
    }

    @Bean
    public PedidoService todosTrabalhos() {
        return mock(PedidoService.class);
    }

    @Bean
    public UsuarioService usuarioService() {
        return mock(UsuarioService.class);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
