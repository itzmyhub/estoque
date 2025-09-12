package nk.estoque.infraestructure.persistence.servicoProdutos;

import nk.estoque.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.infraestructure.entity.servico.ServicoProdutosKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoProdutosPersistence extends JpaRepository<ServicoProdutosEntity, ServicoProdutosKey> {
    List<ServicoProdutosEntity> findByServicoId(Long servicoId);
}
