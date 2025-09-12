package nk.estoque.infraestructure.web.produto;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.application.produto.dto.ProdutoDTO;
import nk.estoque.domain.models.produto.Produto;
import nk.estoque.application.produto.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/produtos")
@CrossOrigin(origins = "http://localhost:3039", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<ProdutoDTO>> getForAdmin(@PageableDefault Pageable pageable) {
        Page<ProdutoDTO> dtos = produtoService.listaPaginada(pageable)
                .map(ProdutoDTO::fromProduto);
        return new ResponseEntity<>(dtos, OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> get(@PageableDefault Pageable pageable, ProdutoFilter filter) {
        Page<ProdutoDTO> dtos = produtoService.listaPaginada(pageable, filter)
                .map(ProdutoDTO::fromProduto);
        return new ResponseEntity<>(dtos, OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<ProdutoDTO> postForAdmin(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoDTO.toProduto();
        Produto salvo = produtoService.criar(produto);
        return new ResponseEntity<>(ProdutoDTO.fromProduto(salvo), CREATED);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> post(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoDTO.toProduto();
        Produto salvo = produtoService.criar(produto);
        return new ResponseEntity<>(ProdutoDTO.fromProduto(salvo), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> put(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO) {
        Produto produto = produtoDTO.toProduto();
        Produto atualizado = produtoService.atualizarProduto(id, produto);
        return new ResponseEntity<>(ProdutoDTO.fromProduto(atualizado), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        Produto produto = produtoService.produtoPorId(id);
        if (produto == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(ProdutoDTO.fromProduto(produto), OK);
    }
}
