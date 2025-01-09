package nk.estoque.application.infraestructure.web.produto;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import java.util.List;
import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.domain.model.produto.TodosProdutos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {
    private final TodosProdutos todosProdutos;

    public ProdutoController(TodosProdutos todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Produto>> getForAdmin() {
        return new ResponseEntity<>(todosProdutos.listaPaginada(), OK);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> get() {
        return new ResponseEntity<>(todosProdutos.listaPaginada(), OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<Produto> postForAdmin(@Valid @RequestBody ProdutoPayload produtoPayload) {
        // da p mudar os tipos pra ProdutoPayload passar essa instancia pra dentro do adapter. a ver
        Produto produto = new Produto();
        produto.geraProduto(produtoPayload);
        return new ResponseEntity<>(todosProdutos.criar(produto), CREATED);
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody ProdutoPayload produtoPayload) {
        // da p mudar os tipos pra ProdutoPayload passar essa instancia pra dentro do adapter. a ver
        Produto produto = new Produto();
        produto.geraProduto(produtoPayload);
        return new ResponseEntity<>(todosProdutos.criar(produto), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @RequestBody @Valid ProdutoPayload produtoPayload) {
        Produto produto = new Produto();
        produto.geraProduto(produtoPayload);
        return new ResponseEntity<>(todosProdutos.atualizarProduto(id, produto), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        todosProdutos.deletarProduto(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
