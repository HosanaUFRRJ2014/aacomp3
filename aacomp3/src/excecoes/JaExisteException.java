package excecoes;

public class JaExisteException extends Exception {

	public JaExisteException(){
		super("Voc� est� tentando adicionar algo que j� existe no banco!");
	}
}
