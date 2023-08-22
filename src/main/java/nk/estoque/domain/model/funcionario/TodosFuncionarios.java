package nk.estoque.domain.model.funcionario;

import nk.estoque.application.infraestructure.entity.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodosFuncionarios {
    List<Funcionario> listaPaginada();

    Funcionario criarFuncionario(Funcionario funcionario);

    Funcionario atualizarFuncionario(Long id, Funcionario funcionario);

    void excluirFuncionario(Long id);
}
