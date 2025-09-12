package nk.estoque.domain.repositories;

import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;

import java.util.List;

public interface ServicoProdutosRepository {
    ServicoProdutos save(ServicoProdutos servicoProduto);
    List<ServicoProdutos> saveAll(List<ServicoProdutos> servicoProdutos);
    List<ServicoProdutos> findByServicoId(Long servicoId);
    void deleteAll(List<ServicoProdutos> servicoProdutos);
}
