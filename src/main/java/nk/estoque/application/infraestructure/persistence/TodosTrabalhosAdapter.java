package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.application.infraestructure.entity.Trabalho;
import nk.estoque.application.infraestructure.repository.TrabalhoRepository;
import nk.estoque.domain.trabalho.TodosTrabalhos;

import java.util.List;
import java.util.Optional;

public class TodosTrabalhosAdapter implements TodosTrabalhos {

    private final TrabalhoRepository trabalhoRepository;

    public TodosTrabalhosAdapter (TrabalhoRepository trabalhoRepository) {
        this.trabalhoRepository = trabalhoRepository;
    }

    @Override
    public List<Trabalho> listaPaginada() {
        return trabalhoRepository.findAll();
    }

    @Override
    public Trabalho criarTrabalho(Trabalho trabalho) {
        return trabalhoRepository.save(trabalho);
    }

    @Override
    public Trabalho atualizarTrabalho(Long id, Trabalho trabalho) {
        Optional<Trabalho> trabalhoEncontrado = trabalhoRepository.findById(id);
        if (trabalhoEncontrado.isEmpty()) {
            throw new RuntimeException("implementar melhor dps");
        }
        // preguiça de pensar em um jeito melhor dps arrumo são 5 da manhã
        trabalho.setId(id);
        return trabalhoRepository.save(trabalho);
    }

    @Override
    public void deletarTrabalho(Long id) {
        trabalhoRepository.deleteById(id);
    }
}
