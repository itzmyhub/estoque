package nk.estoque.domain.model.produto;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidadeEstoque;
    private CodigoDeBarras codigoDeBarras;
    private Marca marca;
    private Detalhe detalhe;

    public void adicionaQuantidadeEmEstoque(Integer quantidade) {
        quantidadeEstoque += quantidade;
    }

    public boolean temQuantidadeEmEstoque() {
        return quantidadeEstoque > 0;
    }
}
