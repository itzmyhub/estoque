package nk.estoque.application.infraestructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>, ListPagingAndSortingRepository<ProdutoEntity, Long> {
    Page<ProdutoEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
