package nk.estoque.domain.repositories;

import nk.estoque.domain.models.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Optional<Produto> findById(Long id);

    List<Produto> findAllById(List<Long> ids);

    Produto save(Produto produto);

    void deleteById(Long id);

    Page<Produto> findAll(Pageable pageable);

    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
