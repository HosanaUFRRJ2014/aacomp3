package dominio;

public class Parada {

	private int id;
	private Logradouro log;
	private Carona carona;
	private Usuario caroneiro;
	
	
	
	
	
	
	
	// daqui para abaixo apenas getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Logradouro getLog() {
		return log;
	}

	public void setLog(Logradouro log) {
		this.log = log;
	}

	public Carona getCarona() {
		return carona;
	}

	public void setCarona(Carona carona) {
		this.carona = carona;
	}

	public Usuario getCaroneiro() {
		return caroneiro;
	}

	public void setCaroneiro(Usuario caroneiro) {
		this.caroneiro = caroneiro;
	}
}
