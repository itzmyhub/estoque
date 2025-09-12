package nk.estoque.infraestructure.persistence.pedidoProdutos;

import nk.estoque.infraestructure.entity.pedido.PedidoProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoProdutosPersistence extends JpaRepository<PedidoProdutosEntity, Long> {
    List<PedidoProdutosEntity> findByPedidoId(Long pedidoId);
}
