package nk.estoque.domain.produto;

import lombok.Data;
import nk.estoque.domain.produto.models.CodigoDeBarras;
import nk.estoque.domain.produto.models.Detalhe;
import nk.estoque.domain.produto.models.Marca;

import java.math.BigDecimal;

@Data
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
