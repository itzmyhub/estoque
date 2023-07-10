package nk.estoque.application.infraestructure.web;

import nk.estoque.application.infraestructure.persistence.TodosProdutosAdapter;
import nk.estoque.domain.produto.TodosProdutos;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfiguration {

    private final ProdutoRepository produtoRepository;

    public WebAppConfiguration(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Bean
    public TodosProdutos todosProdutos() {
        return new TodosProdutosAdapter(produtoRepository);
    }
}
