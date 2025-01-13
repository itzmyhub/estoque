package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.pedido.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private BigDecimal valorAdicional;

    @ManyToMany
    private List<ProdutoEntity> produtos;

    @ManyToMany
    private List<ServicoEntity> servicos;

    @ManyToOne
    private FuncionarioEntity funcionario;

    @ManyToOne
    private ClienteEntity cliente;

    private LocalDateTime dataHora;

    @Column
    private BigDecimal valorFinal;

    public static PedidoEntity fromPedido(Pedido pedido) {
        return PedidoEntity.builder()
                .valorAdicional(pedido.getValorAdicional())
                .dataHora(pedido.getDataHora())
                .valorFinal(pedido.getValorFinal())
                .build();
    }
}
