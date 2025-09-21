package nk.estoque.application.funcionario.service;

import nk.estoque.infraestructure.entity.funcionario.FuncionarioEntity;
import nk.estoque.infraestructure.utils.exceptions.IdNaoEncontradoException;
import nk.estoque.infraestructure.persistence.FuncionarioRepository;
import nk.estoque.domain.models.funcionario.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public List<FuncionarioEntity> listaPaginada() {
        return funcionarioRepository.findAll();
    }

    @Override
    public FuncionarioEntity funcionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("ID numero" + id + "não encontrado!"));
    }

    @Override
    public FuncionarioEntity criarFuncionario(Funcionario funcionario) {
        FuncionarioEntity funcionarioEntity = FuncionarioEntity.fromFuncionario(funcionario);
        return funcionarioRepository.save(funcionarioEntity);
    }

    @Override
    public FuncionarioEntity atualizarFuncionario(Long id, Funcionario funcionario) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Funcionário com ID " + id + " não encontrado"));

        //TODO IMPLEMENTAR A ATUALIZAÇÃO DO FUNCIONÁRIO

        return funcionarioRepository.save(funcionarioEntity);
    }

    @Override
    public void excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

}
