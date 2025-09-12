package nk.estoque.infraestructure.persistence.pedidoProdutos;

import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import nk.estoque.infraestructure.entity.pedido.PedidoEntity;
import nk.estoque.infraestructure.entity.pedido.PedidoProdutosEntity;
import nk.estoque.infraestructure.entity.pedido.PedidoProdutosKey;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoProdutosMapper {

    // Converte Domain -> Entity
    public PedidoProdutosEntity toEntity(PedidoProdutos pedidoProdutos) {
        PedidoProdutosEntity entity = new PedidoProdutosEntity();

        entity.setId(new PedidoProdutosKey(pedidoProdutos.getPedidoId(), pedidoProdutos.getProdutoId()));
        entity.setQuantidade(pedidoProdutos.getQuantidade());

        // Apenas cria referência parcial (ID) para Produto e Pedido
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(pedidoProdutos.getProdutoId());
        entity.setProduto(produto);

        PedidoEntity pedido = new PedidoEntity();
        pedido.setId(pedidoProdutos.getPedidoId());
        entity.setPedido(pedido);

        return entity;
    }

    public PedidoProdutos toDomain(PedidoProdutosEntity entity) {
        return new PedidoProdutos(
                entity.getProduto().getId(),
                entity.getPedido().getId(),
                entity.getQuantidade()
        );
    }

    public List<PedidoProdutosEntity> toEntityList(List<PedidoProdutos> lista) {
        if (lista == null) return Collections.emptyList();
        return lista.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<PedidoProdutos> toDomainList(List<PedidoProdutosEntity> lista) {
        if (lista == null) return Collections.emptyList();
        return lista.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}