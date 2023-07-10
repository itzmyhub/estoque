package nk.estoque.domain.produto;

public class CodigoDeBarras {
    private final String codigo;
    private TipoCodigoDeBarras tipo;

    public CodigoDeBarras(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoCodigoDeBarras getTipo() {
        return tipo;
    }

    // TODO implementar validação desse tipo de código
}
