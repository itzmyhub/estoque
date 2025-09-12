package nk.estoque.application.funcionario.service;

import nk.estoque.infraestructure.entity.funcionario.FuncionarioEntity;
import nk.estoque.domain.models.funcionario.Funcionario;

import java.util.List;

public interface FuncionarioService {
    List<FuncionarioEntity> listaPaginada();

    FuncionarioEntity funcionarioPorId(Long id);

    FuncionarioEntity criarFuncionario(Funcionario funcionario);

    FuncionarioEntity atualizarFuncionario(Long id, Funcionario funcionario);

    void excluirFuncionario(Long id);
}
