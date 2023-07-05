package nk.estoque.infraestructure.web;

import nk.estoque.domain.produto.Produto;
import nk.estoque.domain.produto.TodosProdutos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProdutoController {
    private final TodosProdutos todosProdutos;

    public ProdutoController(TodosProdutos todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    @GetMapping(path = "/produtos")
    public ResponseEntity<List<Produto>> get() {
        return new ResponseEntity<>(todosProdutos.listaPaginada(), HttpStatus.OK);
    }
}
