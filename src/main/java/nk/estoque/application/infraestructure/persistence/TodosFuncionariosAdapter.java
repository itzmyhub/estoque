package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Funcionario;
import nk.estoque.application.infraestructure.repository.FuncionarioRepository;
import nk.estoque.domain.funcionario.TodosFuncionarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodosFuncionariosAdapter implements TodosFuncionarios {

    private final FuncionarioRepository funcionarioRepository;

    public TodosFuncionariosAdapter(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public List<Funcionario> listaPaginada() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionario) {
        throw new RuntimeException("implementa ae filho");
    }

    @Override
    public void excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

}
