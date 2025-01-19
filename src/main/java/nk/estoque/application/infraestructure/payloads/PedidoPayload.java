package nk.estoque.application.infraestructure.payloads;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import nk.estoque.domain.pedido.Pedido;
import nk.estoque.domain.pedido.PedidoProdutos;

@Data
public class PedidoPayload {

    @NotNull(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a zero!")
    private BigDecimal valorAdicional;

    private List<PedidoProdutos> pedidoProdutos;

    private List<Long> servicosId;

    private Long funcionarioId;

    private Long clienteId;

    @NotNull
    private LocalDateTime dataHora;

    private BigDecimal valorFinal;

    //TODO adicionar opção DESCONTO

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setValorAdicional(valorAdicional);
        pedido.setPedidoProdutos(pedidoProdutos);
        pedido.setServicosId(servicosId);
        pedido.setFuncionarioId(funcionarioId);
        pedido.setClienteId(clienteId);
        pedido.setValorFinal(valorFinal);
        return pedido;
    }
}
