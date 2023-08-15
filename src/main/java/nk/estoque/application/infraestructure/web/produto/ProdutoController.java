package nk.estoque.application.infraestructure.web.produto;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.domain.produto.TodosProdutos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping(path = "/produtos")
public class ProdutoController {
    private final TodosProdutos todosProdutos;

    public ProdutoController(TodosProdutos todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> get() {
        return new ResponseEntity<>(todosProdutos.listaPaginada(), OK);
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