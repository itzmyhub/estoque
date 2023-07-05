package nk.estoque.infraestructure.web;

import nk.estoque.domain.produto.TodosProdutos;
import nk.estoque.infraestructure.persistence.TodosProdutosAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebContextApp {

    @Bean
    public TodosProdutos todosProdutos() {
        return new TodosProdutosAdapter();
    }
}
