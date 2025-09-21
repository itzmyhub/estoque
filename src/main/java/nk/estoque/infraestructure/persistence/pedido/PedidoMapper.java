package nk.estoque.infraestructure.persistence.pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import nk.estoque.infraestructure.entity.cliente.ClienteEntity;
import nk.estoque.infraestructure.entity.funcionario.FuncionarioEntity;
import nk.estoque.infraestructure.entity.pedido.PedidoEntity;
import nk.estoque.infraestructure.entity.pedido.PedidoProdutosEntity;
import nk.estoque.infraestructure.entity.pedido.PedidoProdutosKey;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.infraestructure.entity.servico.ServicoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public PedidoEntity toEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();

        entity.setId(pedido.getId());
        entity.setValorAdicional(pedido.getValorAdicional());
        entity.setValorFinal(pedido.getValorFinal());
        entity.setDataHora(pedido.getDataHora());
        entity.setStatus(pedido.getStatus());

        if (pedido.getServicosId() != null) {
            List<ServicoEntity> servicos = pedido.getServicosId().stream()
                    .map(id -> {
                        ServicoEntity se = new ServicoEntity();
                        se.setId(id);
                        return se;
                    })
                    .toList();
            entity.setServicos(servicos);
        }

        if (pedido.getPedidoProdutos() != null) {
            List<PedidoProdutosEntity> pedidoProdutosEntity = pedido.getPedidoProdutos().stream()
                    .map(pp -> toEntity(pp, entity))
                    .toList();
            entity.setPedidoProdutos(pedidoProdutosEntity);
        }

        if (pedido.getClienteId() != null) {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setId(pedido.getClienteId());
            entity.setCliente(cliente);
        }

        if (pedido.getFuncionarioId() != null) {
            FuncionarioEntity funcionario = new FuncionarioEntity();
            funcionario.setId(pedido.getFuncionarioId());
            entity.setFuncionario(funcionario);
        }

        return entity;
    }

    private PedidoProdutosEntity toEntity(PedidoProdutos pp, PedidoEntity pedidoEntity) {
        PedidoProdutosKey key = new PedidoProdutosKey();
        key.setProdutoId(pp.getProdutoId());

        ProdutoEntity produtoRef = entityManager.getReference(ProdutoEntity.class, pp.getProdutoId());

        return PedidoProdutosEntity.builder()
                .id(key)
                .pedido(pedidoEntity)
                .produto(produtoRef)
                .quantidade(pp.getQuantidade())
                .build();
    }

    public Pedido toDomain(PedidoEntity entity) {
        Pedido pedido = new Pedido();

        pedido.setId(entity.getId());
        pedido.setValorAdicional(entity.getValorAdicional());
        pedido.setValorFinal(entity.getValorFinal());
        pedido.setDataHora(entity.getDataHora());
        pedido.setStatus(entity.getStatus());

        if (entity.getServicos() != null) {
            pedido.setServicosId(
                    entity.getServicos().stream()
                            .map(ServicoEntity::getId)
                            .toList()
            );
        }

        if (entity.getPedidoProdutos() != null) {
            pedido.setPedidoProdutos(
                    entity.getPedidoProdutos().stream()
                            .map(this::toDomain)
                            .toList()
            );
        }

        if (entity.getCliente() != null) {
            pedido.setClienteId(entity.getCliente().getId());
        }

        if (entity.getFuncionario() != null) {
            pedido.setFuncionarioId(entity.getFuncionario().getId());
        }

        return pedido;
    }

    private PedidoProdutos toDomain(PedidoProdutosEntity entity) {
        return new PedidoProdutos(entity.getPedido().getId(), entity.getProduto().getId(), entity.getQuantidade());
    }
}

