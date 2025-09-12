package nk.estoque.domain.repositories;

import nk.estoque.domain.models.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PedidoRepository {

    Page<Pedido> findAll(Pageable pageable);

    Optional<Pedido> findById(Long id);

    Pedido save(Pedido pedido);

    void deleteById(Long id);

    Page<Pedido> findByClienteId(Long clienteId, Pageable pageable);

    Page<Pedido> findByFuncionarioId(Long funcionarioId, Pageable pageable);
}
