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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:3039", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> get(@PageableDefault Pageable pageable) {
        List<PedidoDTO> dtos = pedidoService.listaPaginada(pageable)
                .stream()
                .map(PedidoDTO::fromDomain)
                .toList();
        return new ResponseEntity<>(dtos, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(@PathVariable Long id) {
        Pedido pedido = pedidoService.pedidoPorId(id);
        return new ResponseEntity<>(PedidoDTO.fromDomain(pedido), OK);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> post(@Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido novoPedido = pedidoDTO.toDomain();
        Pedido criado = pedidoService.criarPedido(novoPedido);
        return new ResponseEntity<>(PedidoDTO.fromDomain(criado), CREATED);
    }

    @PostMapping("/{id}/servicos")
    public ResponseEntity<PedidoDTO> adicionarServico(
            @PathVariable Long id,
            @RequestBody Long servicoId) {

        Pedido atualizado = pedidoService.adicionarServico(id, servicoId);
        return ResponseEntity.ok(PedidoDTO.fromDomain(atualizado));
    }

    @DeleteMapping("/{id}/servicos/{servicoId}")
    public ResponseEntity<PedidoDTO> removerServico(
            @PathVariable Long id,
            @PathVariable Long servicoId) {

        Pedido atualizado = pedidoService.removerServico(id, servicoId);
        return ResponseEntity.ok(PedidoDTO.fromDomain(atualizado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> put(@PathVariable Long id, @Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoDTO.toDomain();
        Pedido atualizado = pedidoService.atualizarPedido(id, pedido);
        return new ResponseEntity<>(PedidoDTO.fromDomain(atualizado), OK);
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
        return ResponseEntity.ok(PedidoDTO.fromDomain(atualizado));
    }

    @DeleteMapping("/{pedidoId}/produtos/{produtoId}")
    public ResponseEntity<PedidoDTO> removerProduto(
            @PathVariable Long pedidoId,
            @PathVariable Long produtoId
    ) {
        Pedido atualizado = pedidoService.removerProduto(pedidoId, produtoId);
        return ResponseEntity.ok(PedidoDTO.fromDomain(atualizado));
    }
}
