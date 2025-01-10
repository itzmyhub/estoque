package nk.estoque.domain.funcionario;

import nk.estoque.application.infraestructure.entity.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FuncionarioService {
    List<Funcionario> listaPaginada();

    Funcionario criarFuncionario(Funcionario funcionario);

    Funcionario atualizarFuncionario(Long id, Funcionario funcionario);

    void excluirFuncionario(Long id);
}
