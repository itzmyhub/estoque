package nk.estoque.application.infraestructure.web.funcionario;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.FuncionarioEntity;
import nk.estoque.domain.funcionario.Funcionario;
import nk.estoque.domain.funcionario.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioEntity>> get() {
        return new ResponseEntity<>(funcionarioService.listaPaginada(), OK);
    }

    @PostMapping
    public ResponseEntity<FuncionarioEntity> post(@Valid @RequestBody FuncionarioPayload funcionarioPayload) {
        Funcionario funcionario = funcionarioPayload.toFuncionario();
        return new ResponseEntity<>(funcionarioService.criarFuncionario(funcionario), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioEntity> put(@PathVariable Long id, @Valid @RequestBody FuncionarioPayload funcionarioPayload) {
        Funcionario funcionario = funcionarioPayload.toFuncionario();
        return new ResponseEntity<>(funcionarioService.atualizarFuncionario(id, funcionario), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
