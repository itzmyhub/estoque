package nk.estoque.infraestructure.persistence.servico;

import nk.estoque.domain.models.servico.Servico;
import nk.estoque.domain.repositories.ServicoRepository;
import nk.estoque.infraestructure.entity.servico.ServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ServicoRepositoryImpl implements ServicoRepository {

    private final ServicoPersistence persistence;
    private final ServicoMapper mapper;

    public ServicoRepositoryImpl(ServicoPersistence persistence, ServicoMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public Page<Servico> findByNomeContainingIgnoreCase(String nome, Pageable pageable) {
        return persistence.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Servico> findAll(Pageable pageable) {
        return persistence.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public Servico servicoPorId(Long id) {
        ServicoEntity entity = persistence.findWithProdutosById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado: " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public Servico save(Servico servico) {
        ServicoEntity entity = mapper.toEntity(servico);
        ServicoEntity saved = persistence.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        persistence.deleteById(id);
    }
}

