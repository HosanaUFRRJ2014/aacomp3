package excecoes;

public class UsuarioNaoEncontradoException extends Exception {

	public UsuarioNaoEncontradoException() {
		super("Erro: Email de usuário não cadastrado.");
	}

	public UsuarioNaoEncontradoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsuarioNaoEncontradoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UsuarioNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsuarioNaoEncontradoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
