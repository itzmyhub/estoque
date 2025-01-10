package nk.estoque.application.infraestructure.exceptions;

public class IdNaoEncontradoException extends RuntimeException{
    public IdNaoEncontradoException(String s) {
        super(s);
    }
}
