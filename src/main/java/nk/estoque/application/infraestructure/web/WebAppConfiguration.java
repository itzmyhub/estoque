package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.persistence.TodosFuncionariosAdapter;
import nk.estoque.application.infraestructure.persistence.TodosProdutosAdapter;
import nk.estoque.application.infraestructure.repository.FuncionarioRepository;
import nk.estoque.domain.funcionario.TodosFuncionarios;
import nk.estoque.domain.produto.TodosProdutos;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses={TodosFuncionariosAdapter.class, TodosProdutosAdapter.class})
public class WebAppConfiguration {

    private final ProdutoRepository produtoRepository;

    private final FuncionarioRepository funcionarioRepository;

    public WebAppConfiguration(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Bean
    public TodosProdutos todosProdutos() {
        return new TodosProdutosAdapter(produtoRepository);
    }

    @Bean
    public TodosFuncionarios todosFuncionarios() { return new TodosFuncionariosAdapter(funcionarioRepository);
    }
}
