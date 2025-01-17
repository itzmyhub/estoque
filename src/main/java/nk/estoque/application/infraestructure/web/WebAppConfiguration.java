package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.persistence.repository.*;
import nk.estoque.application.infraestructure.service.cliente.ClienteServiceImpl;
import nk.estoque.application.infraestructure.service.funcionario.FuncionarioServiceImpl;
import nk.estoque.application.infraestructure.service.pedido.PedidoProdutosServiceImpl;
import nk.estoque.application.infraestructure.service.pedido.PedidoServiceImpl;
import nk.estoque.application.infraestructure.service.produto.ProdutosServiceImpl;
import nk.estoque.application.infraestructure.service.servico.ServicoProdutosServiceImpl;
import nk.estoque.application.infraestructure.service.servico.ServicoServiceImpl;
import nk.estoque.application.infraestructure.service.cliente.ClienteService;
import nk.estoque.application.infraestructure.service.funcionario.FuncionarioService;
import nk.estoque.application.infraestructure.service.pedido.PedidoProdutosService;
import nk.estoque.application.infraestructure.service.produto.ProdutosService;
import nk.estoque.application.infraestructure.service.pedido.PedidoService;
import nk.estoque.application.infraestructure.service.servico.ServicoProdutosService;
import nk.estoque.application.infraestructure.service.servico.ServicoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

    private final ProdutoRepository produtoRepository;

    private final FuncionarioRepository funcionarioRepository;

    private final PedidoRepository trabalhoRepository;

    private final ServicoRepository servicoRepository;

    private final ClienteRepository clienteRepository;

    private final ServicoProdutosRepository servicoProdutosRepository;

    private final PedidoProdutosRepository pedidoProdutosRepository;

    public WebAppConfiguration(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, PedidoRepository trabalhoRepository, ServicoRepository servicoRepository, ClienteRepository clienteRepository, ServicoProdutosRepository servicoProdutosRepository, PedidoProdutosRepository pedidoProdutosRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.trabalhoRepository = trabalhoRepository;
        this.servicoRepository = servicoRepository;
        this.clienteRepository = clienteRepository;
        this.servicoProdutosRepository = servicoProdutosRepository;
        this.pedidoProdutosRepository = pedidoProdutosRepository;
    }

    @Bean
    public ProdutosService produtosService() {
        return new ProdutosServiceImpl(produtoRepository);
    }

    @Bean
    public FuncionarioService funcionarioService() { return new FuncionarioServiceImpl(funcionarioRepository);
    }

    @Bean
    public PedidoService pedidoService() { return new PedidoServiceImpl(trabalhoRepository, servicoService(), produtosService(), clienteService(), funcionarioService(), pedidoProdutosService());
    }

    @Bean
    public ServicoService servicoService() { return new ServicoServiceImpl(servicoRepository, produtosService(), servicoProdutosService());
    }

    @Bean
    public ClienteService clienteService() { return new ClienteServiceImpl(clienteRepository);
    }

    @Bean
    public ServicoProdutosService servicoProdutosService() { return new ServicoProdutosServiceImpl(servicoProdutosRepository, produtosService());
    }

    @Bean
    public PedidoProdutosService pedidoProdutosService() { return new PedidoProdutosServiceImpl(pedidoProdutosRepository, produtosService());
    }

}
