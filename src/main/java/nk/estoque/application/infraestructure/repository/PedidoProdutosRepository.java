package nk.estoque.application.infraestructure.repository;

import nk.estoque.application.infraestructure.entity.pedido.PedidoProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoProdutosRepository extends JpaRepository<PedidoProdutosEntity, Long> {
}
