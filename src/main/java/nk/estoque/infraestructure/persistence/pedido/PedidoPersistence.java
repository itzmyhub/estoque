package nk.estoque.infraestructure.persistence.pedido;

import nk.estoque.infraestructure.entity.pedido.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoPersistence extends JpaRepository<PedidoEntity, Long> {

    Page<PedidoEntity> findByClienteId(Long clienteId, Pageable pageable);

    Page<PedidoEntity> findByFuncionarioId(Long funcionarioId, Pageable pageable);
}

