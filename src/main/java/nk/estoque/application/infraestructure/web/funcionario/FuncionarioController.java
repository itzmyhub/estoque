package nk.estoque.application.infraestructure.web.funcionario;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.Funcionario;
import nk.estoque.domain.funcionario.TodosFuncionarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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
}
