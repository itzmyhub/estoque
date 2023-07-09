package NK.estoque.infraestructure.web;

import NK.estoque.domain.produto.Produto;
import NK.estoque.domain.produto.ProdutoPayload;
import NK.estoque.domain.produto.TodosProdutos;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/produtos")
    public ResponseEntity<Produto> post(@Valid @RequestBody ProdutoPayload produtoPayload) {
        // da p mudar os tipos pra ProdutoPayload passar essa instancia pra dentro do adapter. a ver
        Produto produto = new Produto();
        produto.geraProduto(produtoPayload);
        return new ResponseEntity<>(todosProdutos.criar(produto), HttpStatus.CREATED);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @RequestBody @Valid ProdutoPayload produtoPayload) {
        Produto produto = new Produto();
        produto.geraProduto(produtoPayload);
        return new ResponseEntity<>(todosProdutos.atualizarProduto(id, produto), HttpStatus.OK);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        todosProdutos.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
