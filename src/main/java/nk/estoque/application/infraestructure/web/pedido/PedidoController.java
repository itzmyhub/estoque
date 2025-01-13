package nk.estoque.application.infraestructure.web.pedido;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.PedidoEntity;
import nk.estoque.domain.pedido.Pedido;
import nk.estoque.domain.pedido.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Controller
@RequestMapping(path = "/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoEntity>> get() {
        return new ResponseEntity<>(pedidoService.listaPaginada(), OK);
    }

    @PostMapping
    public ResponseEntity<PedidoEntity> post(@Valid @RequestBody PedidoPayload pedidoPayload) {
        Pedido pedido = pedidoPayload.toPedido();
        return new ResponseEntity<>(pedidoService.criarPedido(pedido), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> put(@PathVariable Long id, @Valid @RequestBody PedidoPayload pedidoPayload) {
        Pedido pedido = pedidoPayload.toPedido();
        return new ResponseEntity<>(pedidoService.atualizarPedido(id, pedido), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
