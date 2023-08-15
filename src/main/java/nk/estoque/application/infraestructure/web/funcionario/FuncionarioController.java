package nk.estoque.application.infraestructure.web.funcionario;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.Funcionario;
import nk.estoque.domain.funcionario.TodosFuncionarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final TodosFuncionarios todosFuncionarios;

    public FuncionarioController(TodosFuncionarios todosFuncionarios) {
        this.todosFuncionarios = todosFuncionarios;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> get() {
        return new ResponseEntity<>(todosFuncionarios.listaPaginada(), OK);
    }

    @PostMapping
    public ResponseEntity<Funcionario> post(@Valid @RequestBody FuncionarioPayload funcionarioPayload) {
        Funcionario funcionario = new Funcionario();
        funcionario.geraFuncionario(funcionarioPayload);
        return new ResponseEntity<>(todosFuncionarios.criarFuncionario(funcionario), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> put(@PathVariable Long id, @Valid @RequestBody FuncionarioPayload funcionarioPayload) {
        Funcionario funcionario = new Funcionario();
        funcionario.geraFuncionario(funcionarioPayload);
        return new ResponseEntity<>(todosFuncionarios.atualizarFuncionario(id, funcionario), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        todosFuncionarios.excluirFuncionario(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
