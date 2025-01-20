package nk.estoque.application.infraestructure.entity.produto;

import jakarta.persistence.*;
import nk.estoque.domain.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.produto.models.CodigoProduto;
import nk.estoque.domain.produto.models.Detalhe;
import nk.estoque.domain.produto.models.Marca;
import nk.estoque.domain.produto.models.TipoCodigoProduto;

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

    private String codigoProduto;

    private boolean emEstoque;

    private String marca;

    private String detalhe;

    public static ProdutoEntity fromProduto(Produto produto) {
        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .valor(produto.getValor())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .codigoProduto(produto.getCodigoProduto().codigo())
                .emEstoque(produto.temQuantidadeEmEstoque())
                .marca(produto.getMarca().nome())
                .detalhe(produto.getDetalhe().descricao())
                .build();
    }

    public static Produto fromEntityToDomain(ProdutoEntity produtoEntity) {
        if (produtoEntity == null) {
            return null;
        }

        Produto produto = new Produto();
        produto.setId(produtoEntity.getId());
        produto.setNome(produtoEntity.getNome());
        produto.setValor(produtoEntity.getValor());
        produto.setQuantidadeEstoque(produtoEntity.getQuantidadeEstoque());
        produto.temQuantidadeEmEstoque();

        if (produtoEntity.getCodigoProduto() != null) {
            produto.setCodigoProduto(new CodigoProduto(produtoEntity.getCodigoProduto(), TipoCodigoProduto.EAN13));
        }

        if (produtoEntity.getMarca() != null) {
            produto.setMarca(new Marca(produtoEntity.getMarca()));
        }

        if (produtoEntity.getDetalhe() != null) {
            produto.setDetalhe(new Detalhe(produtoEntity.getDetalhe()));
        }

        return produto;
    }
}
