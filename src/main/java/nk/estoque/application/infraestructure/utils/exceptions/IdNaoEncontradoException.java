package nk.estoque.application.infraestructure.utils.exceptions;

public class IdNaoEncontradoException extends RuntimeException{
    public IdNaoEncontradoException(String s) {
        super(s);
    }
}
