package nk.estoque.application.infraestructure.web.cliente;

import nk.estoque.application.infraestructure.entity.ClienteEntity;
import nk.estoque.domain.cliente.Cliente;
import nk.estoque.domain.cliente.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> get() {
        return new ResponseEntity<>(clienteService.listaPaginada(), OK);
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> post(@RequestBody ClientePayload clientePayload) {
        Cliente cliente = clientePayload.toCliente();
        return new ResponseEntity<>(clienteService.criarCliente(cliente), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> put(@RequestBody ClientePayload clientePayload, @PathVariable Long id) {
        Cliente cliente = clientePayload.toCliente();
        return new ResponseEntity<>(clienteService.atualizarCliente(cliente, id), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
