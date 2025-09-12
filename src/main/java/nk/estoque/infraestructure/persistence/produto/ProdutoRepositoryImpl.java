package nk.estoque.infraestructure.persistence.produto;

import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.repositories.ProdutoRepository;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoPersistence persistence;
    private final ProdutoMapper mapper;

    public ProdutoRepositoryImpl(ProdutoPersistence persistence, ProdutoMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return persistence.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Produto> findAllById(List<Long> ids) {
        return persistence.findAllById(ids)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Produto save(Produto produto) {
        ProdutoEntity entity = mapper.toEntity(produto);
        ProdutoEntity saved = persistence.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        persistence.deleteById(id);
    }

    @Override
    public Page<Produto> findAll(Pageable pageable) {
        return persistence.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable) {
        return persistence.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::toDomain);
    }
}
