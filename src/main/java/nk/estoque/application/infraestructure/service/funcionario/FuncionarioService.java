package nk.estoque.application.infraestructure.service.funcionario;

import nk.estoque.application.infraestructure.entity.funcionario.FuncionarioEntity;
import nk.estoque.domain.funcionario.Funcionario;

import java.util.List;

public interface FuncionarioService {
    List<FuncionarioEntity> listaPaginada();

    FuncionarioEntity funcionarioPorId(Long id);

    FuncionarioEntity criarFuncionario(Funcionario funcionario);

    FuncionarioEntity atualizarFuncionario(Long id, Funcionario funcionario);

    void excluirFuncionario(Long id);
}
