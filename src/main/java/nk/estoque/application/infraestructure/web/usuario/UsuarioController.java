package nk.estoque.application.infraestructure.web.usuario;

import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import java.util.List;
import nk.estoque.application.infraestructure.entity.Usuario;
import nk.estoque.application.infraestructure.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping
  public ResponseEntity<HttpStatus> create(@Valid @RequestBody UsuarioPayLoad usuarioPayLoad) {
    usuarioService.criaUm(usuarioPayLoad.toUsuario());
    return new ResponseEntity<>(null, OK);
  }

  @GetMapping
  public ResponseEntity<List<Usuario>> get() {
    return new ResponseEntity<>(usuarioService.getAll(), OK);
  }
}
