package br.com.seguros.excecoes;

public class ExcecaoApoliceNaoEncontrada extends RuntimeException {
    public ExcecaoApoliceNaoEncontrada(String mensagem) {
        super(mensagem);
    }
}
