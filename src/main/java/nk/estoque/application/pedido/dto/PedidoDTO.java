package nk.estoque.application.pedido.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import nk.estoque.application.pedidoProdutos.dto.PedidoProdutosDTO;
import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;

@Data
public class PedidoDTO {

    @NotNull(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a zero!")
    private BigDecimal valorAdicional;

    private List<PedidoProdutosDTO> pedidoProdutos;

    private List<Long> servicosId;

    private Long funcionarioId;

    private Long clienteId;

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setValorAdicional(valorAdicional);
        pedido.setServicosId(servicosId);
        pedido.setFuncionarioId(funcionarioId);
        pedido.setClienteId(clienteId);

        if (pedidoProdutos != null) {
            pedido.setPedidoProdutos(
                    pedidoProdutos.stream()
                            .map(PedidoProdutosDTO::toDomain)
                            .collect(Collectors.toList())
            );
        }

        return pedido;
    }

    public static PedidoDTO fromPedido(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setValorAdicional(pedido.getValorAdicional());
        dto.setServicosId(pedido.getServicosId());
        dto.setFuncionarioId(pedido.getFuncionarioId());
        dto.setClienteId(pedido.getClienteId());

        if (pedido.getPedidoProdutos() != null) {
            dto.setPedidoProdutos(
                    pedido.getPedidoProdutos().stream()
                            .map(PedidoProdutosDTO::fromDomain)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}