package nk.estoque.application.infraestructure.persistence.repository;

import nk.estoque.application.infraestructure.entity.funcionario.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
}
