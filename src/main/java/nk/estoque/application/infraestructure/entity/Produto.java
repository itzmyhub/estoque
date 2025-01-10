package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Produto {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private BigDecimal valor;

    private int quantidadeItens;

    private int codigoProduto;

    private boolean emEstoque;

    public void emEstoque() {
        this.emEstoque = produtoNoEstoque(this);
    }

    protected boolean produtoNoEstoque(Produto produto) {
        return produto.quantidadeItens > 0;
    }
}
