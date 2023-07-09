package NK.estoque.infraestructure.repository;

import NK.estoque.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("produtoRepository")
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
