package NK.estoque.infraestructure.web;

import NK.estoque.infraestructure.persistence.TodosProdutosAdapter;
import NK.estoque.domain.produto.TodosProdutos;
import NK.estoque.infraestructure.repository.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebContextApp {

    private final ProdutoRepository produtoRepository;

    public WebContextApp(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Bean
    public TodosProdutos todosProdutos() {
        return new TodosProdutosAdapter(produtoRepository);
    }
}
