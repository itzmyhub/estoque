package nk.estoque.application.infraestructure.web.pedido;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import nk.estoque.domain.pedido.Pedido;

@Data
public class PedidoPayload {

    @NotNull(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a zero!")
    private BigDecimal valorAdicional;

    private List<Long> produtos;

    private List<Long> servicos;

    private Long funcionario;

    private Long cliente;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private BigDecimal valorFinal;

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setValorAdicional(valorAdicional);
        pedido.setProdutos(produtos);
        pedido.setServicos(servicos);
        pedido.setFuncionario(funcionario);
        pedido.setCliente(cliente);
        pedido.setValorFinal(valorFinal);
        return pedido;
    }
}
