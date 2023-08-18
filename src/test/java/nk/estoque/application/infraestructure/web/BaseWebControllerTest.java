package nk.estoque.application.infraestructure.web;

import static org.mockito.Mockito.doReturn;

import nk.estoque.application.infraestructure.entity.Usuario;
import nk.estoque.application.infraestructure.security.JwtRequestFilter;
import nk.estoque.application.infraestructure.security.JwtTokenUtil;
import nk.estoque.application.infraestructure.service.JwtUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@Import(WebMvcConfigurationTestModule.class)
public class BaseWebControllerTest {
  protected MockMvc mockMvc;
  @Autowired private WebApplicationContext webApplicationContext;
  @Autowired private JwtTokenUtil jwtTokenUtil;
  @MockBean private JwtUserDetailsService jwtUserDetailsService;
  protected String authorizedToken;

  @BeforeEach
  public void setUp() {
    authorizedToken = jwtTokenUtil.generateToken(umUsuarioAutenticado());
    doReturn(umUsuarioAutenticado()).when(jwtUserDetailsService).loadUserByUsername("teste@email");
    mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(new JwtRequestFilter(jwtUserDetailsService, jwtTokenUtil))
            .build();
  }

  private User umUsuarioAutenticado() {
    return umUsuario().toUserDetails();
  }

  private Usuario umUsuario() {
    return Usuario.builder().email("teste@email").password("secret").build();
  }
}
