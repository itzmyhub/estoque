package nk.estoque.infraestructure.persistence.pedidoProdutos;

import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import nk.estoque.domain.repositories.PedidoProdutosRepository;
import nk.estoque.infraestructure.entity.pedido.PedidoProdutosEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoProdutosRepositoryImpl implements PedidoProdutosRepository {

    private final PedidoProdutosPersistence persistence;
    private final PedidoProdutosMapper mapper;

    public PedidoProdutosRepositoryImpl(PedidoProdutosPersistence persistence,
                                        PedidoProdutosMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<PedidoProdutos> pedidoProdutos, Long pedidoId) {
        List<PedidoProdutosEntity> entities = pedidoProdutos.stream()
                .map(pp -> {
                    PedidoProdutosEntity entity = mapper.toEntity(pp);
                    entity.getId().setPedidoId(pedidoId); // garante o pedidoId no EmbeddedId
                    return entity;
                })
                .toList();
        persistence.saveAll(entities);
    }

    @Override
    public void deleteByPedidoId(Long pedidoId) {
        List<PedidoProdutosEntity> existentes = persistence.findByPedidoId(pedidoId);
        persistence.deleteAll(existentes);
    }

    @Override
    public List<PedidoProdutos> findByPedidoId(Long pedidoId) {
        return persistence.findByPedidoId(pedidoId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}

