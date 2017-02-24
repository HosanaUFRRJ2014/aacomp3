package excecoes;

public class ErroConexaoBaseDadosException extends RuntimeException {

	public ErroConexaoBaseDadosException() {
		super("Erro de conexão com a base de dados. Esse erro ocorreu porque, no arquivo ConnectionFactory.java"
				+ " as configurações de acesso à base de dados estão incorretas."
				+ " Por favor, configure-as corretamente");
	}


}
