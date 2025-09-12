package nk.estoque.domain.models.produto;

import lombok.Data;
import nk.estoque.domain.models.produto.models.CodigoProduto;
import nk.estoque.domain.models.produto.models.Detalhe;
import nk.estoque.domain.models.produto.models.Marca;

import java.math.BigDecimal;

@Data
public class Produto {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidadeEstoque;
    private CodigoProduto codigoProduto;
    private Marca marca;
    private Detalhe detalhe;

    public Produto() {

    }

    public void reservar(int quantidade) {
        validarQuantidade(quantidade);
        if (quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException(
                    String.format("Estoque insuficiente para o produto %s", nome)
            );
        }
        this.quantidadeEstoque -= quantidade;
    }

    public void adicionaQuantidadeEmEstoque(int quantidade) {
        validarQuantidade(quantidade);
        this.quantidadeEstoque += quantidade;
    }

    public void subtraiQuantidadeEmEstoque(int quantidade) {
        validarQuantidade(quantidade);
        if (quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException(
                    String.format("Não há estoque suficiente do produto %s", nome)
            );
        }
        this.quantidadeEstoque -= quantidade;
    }

    public boolean isEmEstoque() {
        return quantidadeEstoque > 0;
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
    }

    public void atualizar(Produto produto) {
        if (produto == null) return;

        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.codigoProduto = produto.getCodigoProduto();
        this.marca = produto.getMarca();
        this.detalhe = produto.getDetalhe();
        this.validarQuantidade(this.quantidadeEstoque);
    }
}
