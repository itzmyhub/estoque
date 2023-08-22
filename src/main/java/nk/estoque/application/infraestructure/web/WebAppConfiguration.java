package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.persistence.TodosClientesAdapter;
import nk.estoque.application.infraestructure.persistence.TodosFuncionariosAdapter;
import nk.estoque.application.infraestructure.persistence.TodosProdutosAdapter;
import nk.estoque.application.infraestructure.persistence.TodosTrabalhosAdapter;
import nk.estoque.application.infraestructure.repository.ClienteRepository;
import nk.estoque.application.infraestructure.repository.FuncionarioRepository;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import nk.estoque.application.infraestructure.repository.TrabalhoRepository;
import nk.estoque.application.infraestructure.service.JwtUserDetailsService;
import nk.estoque.application.infraestructure.service.UsuarioService;
import nk.estoque.domain.model.cliente.TodosClientes;
import nk.estoque.domain.model.funcionario.TodosFuncionarios;
import nk.estoque.domain.model.produto.TodosProdutos;
import nk.estoque.domain.model.trabalho.TodosTrabalhos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

    private final ProdutoRepository produtoRepository;

    private final FuncionarioRepository funcionarioRepository;

    private final TrabalhoRepository trabalhoRepository;

    private final ClienteRepository clienteRepository;


    public WebAppConfiguration(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, TrabalhoRepository trabalhoRepository, ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.trabalhoRepository = trabalhoRepository;
        this.clienteRepository = clienteRepository;
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

    @Bean
    public TodosClientes todosClientes() { return new TodosClientesAdapter(clienteRepository);}

}
