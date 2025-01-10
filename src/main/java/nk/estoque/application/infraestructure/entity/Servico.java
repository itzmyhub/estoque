package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servico {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Produto> produtos;

    @Column
    private BigDecimal maoDeObra;

    @Column
    private BigDecimal totalValue;

    public BigDecimal calcularTotalValue(List<Produto> produtos, BigDecimal maoDeObra) {
        BigDecimal totalProdutos = produtos.stream()
                .map(produto -> produto.getValor().multiply(new BigDecimal(produto.getQuantidadeItens())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalProdutos.add(maoDeObra);
    }
}
