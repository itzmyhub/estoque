package nk.estoque.application.infraestructure.repository;

import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoProdutosRepository extends JpaRepository<ServicoProdutosEntity, Long> {
}
