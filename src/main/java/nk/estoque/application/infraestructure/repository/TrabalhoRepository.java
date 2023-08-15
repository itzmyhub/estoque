package nk.estoque.application.infraestructure.repository;

import nk.estoque.application.infraestructure.entity.Trabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
}
