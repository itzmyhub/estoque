package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.persistence.TodosFuncionariosAdapter;
import nk.estoque.application.infraestructure.persistence.TodosProdutosAdapter;
import nk.estoque.application.infraestructure.persistence.TodosTrabalhosAdapter;
import nk.estoque.application.infraestructure.repository.FuncionarioRepository;
import nk.estoque.application.infraestructure.repository.TrabalhoRepository;
import nk.estoque.domain.funcionario.TodosFuncionarios;
import nk.estoque.domain.produto.TodosProdutos;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import nk.estoque.domain.trabalho.TodosTrabalhos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

    private final ProdutoRepository produtoRepository;

    private final FuncionarioRepository funcionarioRepository;

    private final TrabalhoRepository trabalhoRepository;

    public WebAppConfiguration(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, TrabalhoRepository trabalhoRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.trabalhoRepository = trabalhoRepository;
    }

    @Bean
    public TodosProdutos todosProdutos() {
        return new TodosProdutosAdapter(produtoRepository);
    }

    @Bean
    public TodosFuncionarios todosFuncionarios() { return new TodosFuncionariosAdapter(funcionarioRepository);
    }

    @Bean
    public TodosTrabalhos todosTrabalhos() { return new TodosTrabalhosAdapter(trabalhoRepository);
    }
}
