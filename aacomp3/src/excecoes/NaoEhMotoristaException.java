package excecoes;

public class NaoEhMotoristaException extends Exception {

	public NaoEhMotoristaException(){
		super("Voc� ainda n�o � motorita para poder criar uma Carona.");
	}
}
