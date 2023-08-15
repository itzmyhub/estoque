package nk.estoque.domain.trabalho;

import nk.estoque.application.infraestructure.entity.Trabalho;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodosTrabalhos {

    List<Trabalho> listaPaginada();

    Trabalho criarTrabalho(Trabalho trabalho);

    Trabalho atualizarTrabalho(Long id, Trabalho trabalho);

    void deletarTrabalho(Long id);
}
