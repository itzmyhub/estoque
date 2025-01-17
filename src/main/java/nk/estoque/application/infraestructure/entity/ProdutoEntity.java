package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.*;
import nk.estoque.domain.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @Column
    private BigDecimal valor;

    @Column
    private int quantidadeEstoque;

    private String codigoDeBarras;

    private boolean emEstoque;

    private String marca;

    private String detalhe;

    public static ProdutoEntity fromProduto(Produto produto) {
        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .valor(produto.getValor())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .codigoDeBarras(produto.getCodigoDeBarras().codigo())
                .emEstoque(produto.temQuantidadeEmEstoque())
                .marca(produto.getMarca().nome())
                .detalhe(produto.getDetalhe().descricao())
                .build();
    }
}
