package dominio;

public class Veiculo 
{
	private String modelo;
	private String placa;
	private String cor;
	//private Motorista motorista;
	
	public Veiculo(String m,String p,String c)
	{
		modelo = m;
		placa = p;
		cor = c;
		
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public String getPlaca() {
		return placa;
	}
	
	
	

}
