package nk.estoque.domain.funcionario;

import nk.estoque.application.infraestructure.entity.FuncionarioEntity;

import java.util.List;

public interface FuncionarioService {
    List<FuncionarioEntity> listaPaginada();

    FuncionarioEntity funcionarioPorId(Long id);

    FuncionarioEntity criarFuncionario(Funcionario funcionario);

    FuncionarioEntity atualizarFuncionario(Long id, Funcionario funcionario);

    void excluirFuncionario(Long id);
}
