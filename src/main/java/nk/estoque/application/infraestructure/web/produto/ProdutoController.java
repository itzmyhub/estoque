package nk.estoque.application.infraestructure.web.produto;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.application.infraestructure.payloads.ProdutoPayload;
import nk.estoque.domain.produto.Produto;
import nk.estoque.application.infraestructure.service.produto.ProdutosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/produtos")
@CrossOrigin(origins = "http://localhost:3039", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ProdutoController {
    private final ProdutosService produtoService;

    public ProdutoController(ProdutosService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<ProdutoEntity>> getForAdmin(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(produtoService.listaPaginada(pageable), OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoEntity>> get(@PageableDefault Pageable pageable, ProdutoFilter filter) {
        return new ResponseEntity<>(produtoService.listaPaginada(pageable, filter), OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<ProdutoEntity> postForAdmin(@Valid @RequestBody ProdutoPayload produtoPayload) {
        Produto produto = produtoPayload.toProduto();
        return new ResponseEntity<>(produtoService.criar(produto), CREATED);
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> post(@Valid @RequestBody ProdutoPayload produtoPayload) {
        Produto produto = produtoPayload.toProduto();
        return new ResponseEntity<>(produtoService.criar(produto), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> put(@PathVariable Long id, @RequestBody @Valid ProdutoPayload produtoPayload) {
        Produto produto = produtoPayload.toProduto();
        return new ResponseEntity<>(produtoService.atualizarProduto(id, produto), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
