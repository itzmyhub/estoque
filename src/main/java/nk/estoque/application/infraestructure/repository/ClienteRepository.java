package nk.estoque.application.infraestructure.repository;

import nk.estoque.application.infraestructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
