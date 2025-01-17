package nk.estoque.application.infraestructure.entity.pedido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.application.infraestructure.entity.cliente.ClienteEntity;
import nk.estoque.application.infraestructure.entity.funcionario.FuncionarioEntity;
import nk.estoque.application.infraestructure.entity.servico.ServicoEntity;
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

    @OneToMany(mappedBy = "pedido", cascade=CascadeType.REMOVE)
    private List<PedidoProdutosEntity> pedidoProdutos;

    @ManyToMany
    private List<ServicoEntity> servicos;

    @ManyToOne(optional = false,cascade=CascadeType.REMOVE)
    private FuncionarioEntity funcionario;

    @ManyToOne(optional = false,cascade=CascadeType.REMOVE)
    private ClienteEntity cliente;

    private LocalDateTime dataHora;

    @Column
    private BigDecimal valorFinal;

    public static PedidoEntity fromPedido(Pedido pedido) {
        return PedidoEntity.builder()
                .valorAdicional(pedido.getValorAdicional())
                .dataHora(pedido.getDataHora())
                .valorFinal(pedido.getValorFinal())
                .pedidoProdutos(PedidoProdutosEntity.fromPedidoProdutosList(pedido.getPedidoProdutos()))
                .build();
    }
}
