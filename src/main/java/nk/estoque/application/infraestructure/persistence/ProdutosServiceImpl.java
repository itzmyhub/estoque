package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.ProdutoEntity;
import nk.estoque.application.infraestructure.exceptions.IdNaoEncontradoException;
import nk.estoque.domain.produto.Produto;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;

import java.util.ArrayList;
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
        List<ProdutoEntity> produtos = produtoRepository.findAllById(ids);

        if (produtos.size() != ids.size()) {
            List<Long> idsNaoEncontrados = new ArrayList<>(ids);
            produtos.forEach(produto -> idsNaoEncontrados.remove(produto.getId()));

            throw new IdNaoEncontradoException("Produtos com os IDs não encontrados: " + idsNaoEncontrados);
        }

        return produtos;
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
