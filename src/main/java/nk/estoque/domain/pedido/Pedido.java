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
    private LocalDateTime dataHora; //(MaryannaHellenLinda) <fakenews> {playmusic_onedirection="nobodycompares.mp3"} mary anne mary anr
    // aryanna maryanna maryanna maryanna hellen hellen hellen hellen alves alves alves alves soares soares soares soares soares spares
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
