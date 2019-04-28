package br.com.dr2.lavanderia.exception;

public class ObjetoNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String msg) {
		super(msg);
	}

	public ObjetoNaoEncontradoException(String msg, Throwable e) {
		super(msg, e);
	}

}
