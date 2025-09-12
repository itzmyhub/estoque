package nk.estoque.domain.repositories;

import nk.estoque.domain.models.servico.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ServicoRepository {
    Page<Servico> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Servico> findAll(Pageable pageable);
    Servico servicoPorId(Long id);
    Servico save(Servico servico);
    void deleteById(Long id);
}
