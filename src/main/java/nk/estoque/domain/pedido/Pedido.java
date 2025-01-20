package nk.estoque.domain.pedido;

import lombok.Data;
import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.domain.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pedido {
    private Long id;
    private BigDecimal valorAdicional;
    private Long funcionarioId;
    private Long clienteId;
    private List<Long> servicosId;
    private LocalDateTime dataHora;
    private List<PedidoProdutos> pedidoProdutos;
    private BigDecimal valorFinal;

    public ProdutoEntity atualizaQuantidadeProduto(ProdutoEntity produtoEntity, int quantidade) {

        Produto produto = ProdutoEntity.fromEntityToDomain(produtoEntity);
        produto.subtraiQuantidadeEmEstoque(quantidade);
        produtoEntity.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoEntity.setEmEstoque(produto.temQuantidadeEmEstoque());
        return produtoEntity;
    }

    public BigDecimal calculaValorFinal(BigDecimal valorTotalServicos, BigDecimal valorTotalProdutos) {
        return valorAdicional.add(valorTotalProdutos.add(valorTotalServicos));
    }

}
