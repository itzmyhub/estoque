package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.ProdutoEntity;
import nk.estoque.application.infraestructure.exceptions.IdNaoEncontradoException;
import nk.estoque.domain.produto.Produto;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;

import java.util.List;

public class ProdutosServiceImpl implements ProdutosService {
    private final ProdutoRepository produtoRepository;

    public ProdutosServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoEntity> listaPaginada() {
        return produtoRepository.findAll();
    }

    @Override
    public List<ProdutoEntity> produtosPorId(List<Long> ids) {
        return produtoRepository.findAllById(ids);
    }

    @Override
    public ProdutoEntity produtoPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("Produto com ID " + id + " não encontrado"));
    }

    @Override
    public ProdutoEntity criar(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoEntity.fromProduto(produto);
        return produtoRepository.save(produtoEntity);
    }

    @Override
    public ProdutoEntity atualizarProduto(Long id, Produto produto) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Produto com ID " + id + " não encontrado"));

        //TODO IMPLEMENTAR A ATUALIZAÇÃO DO PRODUTO

        return produtoRepository.save(produtoEntity);

    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
