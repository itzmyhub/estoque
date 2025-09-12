package nk.estoque.application.servico.dto;

import lombok.Data;
import nk.estoque.application.servicoProdutos.dto.ServicoProdutosDTO;
import nk.estoque.domain.models.servico.Servico;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ServicoDTO {

    private Long id;
    private String nome;
    private BigDecimal maoDeObra;
    private BigDecimal valorTotal;
    private List<ServicoProdutosDTO> servicoProdutos;

    public Servico toDomain() {
        Servico servico = new Servico();
        servico.setId(this.id);
        servico.setNome(this.nome);
        servico.setMaoDeObra(this.maoDeObra);
        servico.setValorTotal(this.valorTotal);

        if (this.servicoProdutos != null) {
            servico.setServicoProdutos(
                    this.servicoProdutos.stream()
                            .map(ServicoProdutosDTO::toDomain)
                            .collect(Collectors.toList())
            );
        }

        return servico;
    }

    public static ServicoDTO fromDomain(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setMaoDeObra(servico.getMaoDeObra());
        dto.setValorTotal(servico.getValorTotal());

        if (servico.getServicoProdutos() != null) {
            dto.setServicoProdutos(
                    servico.getServicoProdutos().stream()
                            .map(ServicoProdutosDTO::fromDomain)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}