package NK.estoque;

import NK.estoque.domain.produto.Produto;
import NK.estoque.infraestructure.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class EstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(ProdutoRepository produtoRepository) {
		return (args) -> {
			Produto produto1 = new Produto("Evaporador", new BigDecimal("1000.0"), 1, 100, true);
			Produto produto2 = new Produto("Filtro", new BigDecimal("100.0"), 2, 50, true);
			Produto produto3 = new Produto("Condensador", new BigDecimal("450.0"), 3, 120, true);
			produtoRepository.save(produto1);
			produtoRepository.save(produto2);
			produtoRepository.save(produto3);
		};
	}

}
