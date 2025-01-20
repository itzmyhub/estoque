package nk.estoque.application.infraestructure.persistence.repository;

import nk.estoque.application.infraestructure.entity.servico.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
}
