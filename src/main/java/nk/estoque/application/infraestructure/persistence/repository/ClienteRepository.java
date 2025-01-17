package nk.estoque.application.infraestructure.persistence.repository;

import nk.estoque.application.infraestructure.entity.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
