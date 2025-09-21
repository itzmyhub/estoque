package nk.estoque.application.pedido.useCase;

import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import nk.estoque.domain.models.produto.Produto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CalculadoraValorPedido {

    public BigDecimal calcular(Pedido pedido, BigDecimal valorTotalServicos, List<Produto> produtos) {
        BigDecimal valorTotalProdutos = BigDecimal.ZERO;

        if (pedido.getPedidoProdutos() != null) {
            for (PedidoProdutos pp : pedido.getPedidoProdutos()) {
                Produto produto = produtos.stream()
                        .filter(p -> p.getId().equals(pp.getProdutoId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException(
                                "Produto com id " + pp.getProdutoId() + " não encontrado na lista."));

                BigDecimal subtotal = produto.getValor()
                        .multiply(BigDecimal.valueOf(pp.getQuantidade()));
                valorTotalProdutos = valorTotalProdutos.add(subtotal);
            }
        }

        BigDecimal valorAdicional = pedido.getValorAdicional() != null
                ? pedido.getValorAdicional()
                : BigDecimal.ZERO;

        return valorTotalServicos
                .add(valorTotalProdutos)
                .add(valorAdicional);
    }
}
