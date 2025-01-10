package nk.estoque.application.infraestructure.repository;

import nk.estoque.application.infraestructure.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
