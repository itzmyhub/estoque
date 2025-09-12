package nk.estoque.infraestructure.persistence.servico;

import nk.estoque.infraestructure.entity.servico.ServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface ServicoPersistence extends JpaRepository<ServicoEntity, Long>, ListPagingAndSortingRepository<ServicoEntity, Long> {
    Page<ServicoEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    @EntityGraph(attributePaths = {"servicoProdutos", "servicoProdutos.produto"})
    Optional<ServicoEntity> findWithProdutosById(Long id);
}
