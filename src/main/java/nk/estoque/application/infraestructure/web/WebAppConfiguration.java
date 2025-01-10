package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.persistence.FuncionarioServiceImpl;
import nk.estoque.application.infraestructure.persistence.ProdutosServiceImpl;
import nk.estoque.application.infraestructure.persistence.PedidoServiceImpl;
import nk.estoque.application.infraestructure.persistence.ServicoServiceImpl;
import nk.estoque.application.infraestructure.repository.FuncionarioRepository;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import nk.estoque.application.infraestructure.repository.PedidoRepository;
import nk.estoque.application.infraestructure.repository.ServicoRepository;
import nk.estoque.domain.funcionario.FuncionarioService;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.domain.pedido.PedidoService;
import nk.estoque.domain.servico.ServicoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

    private final ProdutoRepository produtoRepository;

    private final FuncionarioRepository funcionarioRepository;

    private final PedidoRepository trabalhoRepository;

    private final ServicoRepository servicoRepository;

    public WebAppConfiguration(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, PedidoRepository trabalhoRepository, ServicoRepository servicoRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.trabalhoRepository = trabalhoRepository;
        this.servicoRepository = servicoRepository;
    }

    @Bean
    public ProdutosService produtosService() {
        return new ProdutosServiceImpl(produtoRepository);
    }

    @Bean
    public FuncionarioService funcionarioService() { return new FuncionarioServiceImpl(funcionarioRepository);
    }

    @Bean
    public PedidoService pedidoService() { return new PedidoServiceImpl(trabalhoRepository);
    }

    @Bean
    public ServicoService servicoService() { return new ServicoServiceImpl(servicoRepository);
    }
}
