package nk.estoque.application.infraestructure.web.cliente;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.Cliente;
import nk.estoque.application.infraestructure.persistence.TodosClientesAdapter;
import nk.estoque.domain.model.cliente.TodosClientes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final TodosClientes todosClientes;

    ClienteController(TodosClientes todosClientes) {
        this.todosClientes = todosClientes;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<>(todosClientes.listaPaginada(), OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody @Valid ClientePayLoad clientePayLoad) {
        Cliente cliente = clientePayLoad.toCliente();
        return new ResponseEntity<>(todosClientes.novoCliente(cliente), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> put(@PathVariable Long id, @RequestBody @Valid ClientePayLoad clientePayLoad) {
        Cliente cliente = clientePayLoad.toCliente();
        return new ResponseEntity<>(todosClientes.atualizarCliente(id, cliente), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        todosClientes.excluirCliente(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
