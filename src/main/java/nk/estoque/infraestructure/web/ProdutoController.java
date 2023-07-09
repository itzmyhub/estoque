package NK.estoque.infraestructure.web;

import NK.estoque.domain.produto.Produto;
import NK.estoque.domain.produto.ProdutoValidation;
import NK.estoque.domain.produto.TodosProdutos;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RestController
public class ProdutoController {
    private final TodosProdutos todosProdutos;

    public ProdutoController(TodosProdutos todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> get() {
        return new ResponseEntity<>(todosProdutos.listaPaginada(), HttpStatus.OK);
    }

//    @PostMapping("/produtos")
//    public ResponseEntity post(@Valid ProdutoValidation produto, BindingResult bindingResult) {
//        todosProdutos.criarProduto(produto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping("/produtos/{id}")
//    public ResponseEntity<Produto> put(@PathVariable Long id, Produto produto) {
//        return new ResponseEntity<>(todosProdutos.atualizarProduto(id, produto), HttpStatus.OK);
//    }
}
