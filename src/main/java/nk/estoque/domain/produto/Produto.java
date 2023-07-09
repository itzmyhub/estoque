package NK.estoque.domain.produto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    private BigDecimal valor;

    private int quantidadeItens;

    private int codigoProduto;

    private boolean emEstoque;

    public Produto(String nome, BigDecimal valor, int quantidadeItens, int codigoProduto, boolean emEstoque) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeItens = quantidadeItens;
        this.codigoProduto = codigoProduto;
        this.emEstoque = emEstoque;
    }

    public Produto() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public boolean isEmEstoque() {
        return emEstoque;
    }

    public void setEmEstoque(boolean emEstoque) {
        this.emEstoque = emEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
