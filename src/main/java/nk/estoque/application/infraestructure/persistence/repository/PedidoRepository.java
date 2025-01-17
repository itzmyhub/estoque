package nk.estoque.application.infraestructure.persistence.repository;

import nk.estoque.application.infraestructure.entity.pedido.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
