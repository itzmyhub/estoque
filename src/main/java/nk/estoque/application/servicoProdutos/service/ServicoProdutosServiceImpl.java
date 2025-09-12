package nk.estoque.application.servicoProdutos.service;


import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.domain.repositories.ServicoProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicoProdutosServiceImpl implements ServicoProdutosService {

    private final ServicoProdutosRepository servicoProdutosRepository;

    public ServicoProdutosServiceImpl(
            ServicoProdutosRepository servicoProdutosRepository
    ) {
        this.servicoProdutosRepository = servicoProdutosRepository;
    }


    @Override
    public void deletarProdutosDoServico(Long servicoId) {
        List<ServicoProdutos> existentes = servicoProdutosRepository.findByServicoId(servicoId);
        servicoProdutosRepository.deleteAll(existentes);
    }

    @Override
    public List<ServicoProdutos> listarProdutosDoServico(Long servicoId) {
        return servicoProdutosRepository.findByServicoId(servicoId);
    }
}

