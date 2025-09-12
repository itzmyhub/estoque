package nk.estoque.infraestructure.web.pedido;

import jakarta.validation.Valid;
import nk.estoque.application.pedido.dto.PedidoDTO;
import nk.estoque.application.pedido.service.PedidoService;
import nk.estoque.application.pedidoProdutos.dto.PedidoProdutosDTO;
import nk.estoque.domain.models.pedido.Pedido;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> get(@PageableDefault Pageable pageable) {
        List<PedidoDTO> dtos = pedidoService.listaPaginada(pageable)
                .stream()
                .map(PedidoDTO::fromPedido)
                .toList();
        return new ResponseEntity<>(dtos, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(@PathVariable Long id) {
        Pedido pedido = pedidoService.pedidoPorId(id);
        return new ResponseEntity<>(PedidoDTO.fromPedido(pedido), OK);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> post(@Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoDTO.toPedido();
        Pedido criado = pedidoService.criarPedido(pedido);
        return new ResponseEntity<>(PedidoDTO.fromPedido(criado), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> put(@PathVariable Long id, @Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoDTO.toPedido();
        Pedido atualizado = pedidoService.atualizarPedido(id, pedido);
        return new ResponseEntity<>(PedidoDTO.fromPedido(atualizado), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PostMapping("/{pedidoId}/produtos")
    public ResponseEntity<PedidoDTO> adicionarProduto(
            @PathVariable Long pedidoId,
            @Valid @RequestBody PedidoProdutosDTO produtoDTO
    ) {
        Pedido atualizado = pedidoService.adicionarProduto(pedidoId, produtoDTO.toDomain());
        return ResponseEntity.ok(PedidoDTO.fromPedido(atualizado));
    }

    @DeleteMapping("/{pedidoId}/produtos/{produtoId}")
    public ResponseEntity<PedidoDTO> removerProduto(
            @PathVariable Long pedidoId,
            @PathVariable Long produtoId
    ) {
        Pedido atualizado = pedidoService.removerProduto(pedidoId, produtoId);
        return ResponseEntity.ok(PedidoDTO.fromPedido(atualizado));
    }
}
