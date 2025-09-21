package nk.estoque.infraestructure.entity.produto;

import jakarta.persistence.*;
import nk.estoque.domain.models.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.models.produto.models.CodigoProduto;
import nk.estoque.domain.models.produto.models.Detalhe;
import nk.estoque.domain.models.produto.models.Marca;
import nk.estoque.domain.models.produto.models.TipoCodigoProduto;

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

    private String tipoCodigo;

    private String marca;

    private String detalhe;

}
