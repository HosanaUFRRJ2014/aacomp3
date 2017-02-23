package excecoes;

public class JaExisteException extends Exception {

	public JaExisteException(){
		super("Você está tentando adicionar algo que já existe no banco!");
	}
}
