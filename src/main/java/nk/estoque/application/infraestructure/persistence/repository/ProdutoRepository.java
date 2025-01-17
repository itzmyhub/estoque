package nk.estoque.application.infraestructure.persistence.repository;

import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
