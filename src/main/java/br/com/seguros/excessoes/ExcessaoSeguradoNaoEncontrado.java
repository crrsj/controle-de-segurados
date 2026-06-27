package br.com.seguros.excessoes;

import javax.management.RuntimeErrorException;

public class ExcessaoSeguradoNaoEncontrado extends RuntimeException {
    public ExcessaoSeguradoNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
