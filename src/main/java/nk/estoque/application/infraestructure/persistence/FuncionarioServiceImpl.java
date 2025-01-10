package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Funcionario;
import nk.estoque.application.infraestructure.repository.FuncionarioRepository;
import nk.estoque.domain.funcionario.FuncionarioService;

import java.util.List;
import java.util.Optional;

public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
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
        Optional<Funcionario> funcionarioEncontrado = funcionarioRepository.findById(id);
        if (funcionarioEncontrado.isEmpty()) {
            throw new RuntimeException("implementar melhor dps");
        }
        funcionario.setId(id);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

}
