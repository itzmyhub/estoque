package nk.estoque.application.infraestructure.web.produto;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.domain.model.produto.TodosProdutos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/produtos")
@CrossOrigin(origins = "http://localhost:3039", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ProdutoController {
    private final TodosProdutos todosProdutos;

    public ProdutoController(TodosProdutos todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<Produto>> getForAdmin(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(todosProdutos.listaPaginada(pageable), OK);
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> get(@PageableDefault Pageable pageable, ProdutoFilter filter) {
        return new ResponseEntity<>(todosProdutos.listaPaginada(pageable, filter), OK);
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
