package br.com.seguros.excecoes;

public class ExcecaoSeguradoNaoEncontrado extends RuntimeException {
    public ExcecaoSeguradoNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
