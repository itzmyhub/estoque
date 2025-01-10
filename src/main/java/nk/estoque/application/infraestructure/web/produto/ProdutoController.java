package nk.estoque.application.infraestructure.web.produto;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import java.util.List;
import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.domain.produto.ProdutosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {
    private final ProdutosService produtoService;

    public ProdutoController(ProdutosService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Produto>> getForAdmin() {
        return new ResponseEntity<>(produtoService.listaPaginada(), OK);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> get() {
        return new ResponseEntity<>(produtoService.listaPaginada(), OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<Produto> postForAdmin(@Valid @RequestBody ProdutoPayload produtoPayload) {
        Produto produto = produtoPayload.toProduto();
        return new ResponseEntity<>(produtoService.criar(produto), CREATED);
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody ProdutoPayload produtoPayload) {
        Produto produto = produtoPayload.toProduto();
        return new ResponseEntity<>(produtoService.criar(produto), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @RequestBody @Valid ProdutoPayload produtoPayload) {
        Produto produto = produtoPayload.toProduto();
        return new ResponseEntity<>(produtoService.atualizarProduto(id, produto), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
