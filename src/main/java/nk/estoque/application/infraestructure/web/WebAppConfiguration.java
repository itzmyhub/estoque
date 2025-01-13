package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.persistence.*;
import nk.estoque.application.infraestructure.repository.*;
import nk.estoque.domain.cliente.ClienteService;
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

    private final ClienteRepository clienteRepository;

    public WebAppConfiguration(ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, PedidoRepository trabalhoRepository, ServicoRepository servicoRepository, ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.trabalhoRepository = trabalhoRepository;
        this.servicoRepository = servicoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Bean
    public ProdutosService produtosService() {
        return new ProdutosServiceImpl(produtoRepository);
    }

    @Bean
    public FuncionarioService funcionarioService() { return new FuncionarioServiceImpl(funcionarioRepository);
    }

    @Bean
    public PedidoService pedidoService() { return new PedidoServiceImpl(trabalhoRepository, servicoService(), produtosService(), clienteService(), funcionarioService());
    }

    @Bean
    public ServicoService servicoService() { return new ServicoServiceImpl(servicoRepository, produtosService());
    }

    @Bean
    public ClienteService clienteService() {
        return new ClienteServiceImpl(clienteRepository);
    }
}
