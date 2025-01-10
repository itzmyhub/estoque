package nk.estoque.domain.produto.models;

import lombok.Getter;

@Getter
public class CodigoDeBarras {
    private final String codigo;
    private TipoCodigoDeBarras tipo;

    public CodigoDeBarras(String codigo) {
        this.codigo = codigo;
    }

    // TODO implementar validação desse tipo de código
}
