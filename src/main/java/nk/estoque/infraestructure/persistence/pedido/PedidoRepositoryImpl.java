package nk.estoque.infraestructure.persistence.pedido;

import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.repositories.PedidoRepository;
import nk.estoque.infraestructure.entity.pedido.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoPersistence persistence;
    private final PedidoMapper mapper;

    public PedidoRepositoryImpl(PedidoPersistence persistence, PedidoMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return persistence.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return persistence.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Pedido save(Pedido pedido) {
        PedidoEntity entity = mapper.toEntity(pedido);
        PedidoEntity saved = persistence.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        persistence.deleteById(id);
    }

    @Override
    public Page<Pedido> findByClienteId(Long clienteId, Pageable pageable) {
        return persistence.findByClienteId(clienteId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Pedido> findByFuncionarioId(Long funcionarioId, Pageable pageable) {
        return persistence.findByFuncionarioId(funcionarioId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Pedido removerServico(Long pedidoId, Long servicoId) {
        PedidoEntity pedido = persistence.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + pedidoId));
        boolean removed = pedido.getServicos().removeIf(servico -> servico.getId().equals(servicoId));
        if (!removed) {
            throw new IllegalArgumentException("Serviço " + servicoId + " não encontrado no pedido " + pedidoId);
        }
        PedidoEntity atualizado = persistence.save(pedido);

        return mapper.toDomain(atualizado);
    }

    @Override
    public Pedido adicionarServico(Long pedidoId, Long servicoId) {
        return null;
    }
}
