package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.application.infraestructure.web.produto.ProdutoPayload;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    private BigDecimal valor;

    private int quantidadeItens;

    private int codigoProduto;

    private boolean emEstoque;

    public void geraProduto(ProdutoPayload produtoPayload) {
        this.id = produtoPayload.getId();
        this.nome = produtoPayload.getNome();
        this.valor = produtoPayload.getValor();
        this.quantidadeItens = produtoPayload.getQuantidadeItens();
        this.codigoProduto = produtoPayload.getCodigoProduto();
        this.emEstoque = produtoPayload.isEmEstoque();
    }
}
