package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Funcionario;
import nk.estoque.domain.funcionario.TodosFuncionarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodosFuncionariosAdapter implements TodosFuncionarios {

    @Override
    public List<Funcionario> listaPaginada() {
        throw new RuntimeException("implementa ae filho");
    }

    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
        throw new RuntimeException("implementa ae filho");
    }

}
