package execoes;

public class EmailInvalidoException extends Exception {

	public EmailInvalidoException(){
		super("Você digitou um email invalido!");
	}
	
}
