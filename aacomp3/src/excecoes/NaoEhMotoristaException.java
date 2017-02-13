package excecoes;

public class NaoEhMotoristaException extends Exception {

	public NaoEhMotoristaException(){
		super("Você ainda não é motorita para poder criar uma Carona.");
	}
}
