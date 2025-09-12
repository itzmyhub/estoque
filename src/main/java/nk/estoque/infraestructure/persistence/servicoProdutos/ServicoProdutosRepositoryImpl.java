package nk.estoque.infraestructure.persistence.servicoProdutos;

import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.domain.repositories.ServicoProdutosRepository;
import nk.estoque.infraestructure.entity.servico.ServicoProdutosEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicoProdutosRepositoryImpl implements ServicoProdutosRepository {

    private final ServicoProdutosPersistence persistence;
    private final ServicoProdutosMapper mapper;

    public ServicoProdutosRepositoryImpl(ServicoProdutosPersistence persistence, ServicoProdutosMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public ServicoProdutos save(ServicoProdutos servicoProduto) {
        ServicoProdutosEntity entity = mapper.toEntity(servicoProduto);
        ServicoProdutosEntity saved = persistence.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<ServicoProdutos> saveAll(List<ServicoProdutos> servicoProdutos) {
        List<ServicoProdutosEntity> entities = servicoProdutos.stream()
                .map(mapper::toEntity)
                .toList();
        List<ServicoProdutosEntity> saved = persistence.saveAll(entities);
        return saved.stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<ServicoProdutos> findByServicoId(Long servicoId) {
        return persistence.findByServicoId(servicoId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteAll(List<ServicoProdutos> servicoProdutos) {
        List<ServicoProdutosEntity> entities = servicoProdutos.stream()
                .map(mapper::toEntity)
                .toList();
        persistence.deleteAll(entities);
    }
}
