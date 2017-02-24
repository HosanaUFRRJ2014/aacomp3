package dominio;

import dto.LogradouroDTO;
import projetoTDG.LogradouroTDG;

public class Logradouro 
{
	private int id;
	private String CEP;
	private String estado;
	private String cidade;
	private String distrito;
	private String endereco;
	private int numero;	
	
	
	public Logradouro(String CEP, String estado, String cidade, String distrito, String rua, int numero)
	{
		this.CEP = CEP;
		this.estado = estado;
		this.cidade = cidade;
		this.distrito = distrito;
		this.endereco = rua;
		this.numero = numero;
	}
	
	
	public void armazena() throws ClassNotFoundException{
		
		LogradouroTDG aux = new LogradouroTDG();
		
		aux.adicionaLogradouro(this.CEP, this.numero, this.estado, this.cidade, this.distrito, this.endereco);
		
	}
	
	public boolean verifica() throws ClassNotFoundException{
		
		LogradouroTDG aux = new LogradouroTDG();
		
		return aux.verificaLogradouro(this.CEP, this.numero);
	}
	
	public void recuperaID() throws ClassNotFoundException{
		
		LogradouroTDG aux = new LogradouroTDG();
		
		this.id = aux.recuperaID(this.CEP, this.numero);
	}
	
	public void recuperaInfo() throws ClassNotFoundException{
		
		LogradouroTDG auxLogradouro = new LogradouroTDG();		
		LogradouroDTO mensageiro = auxLogradouro.recuperaLogradouro(this.id);
		
		this.id = mensageiro.getId();
		this.CEP = mensageiro.getCEP();
		this.numero = mensageiro.getNumero();
		this.estado = mensageiro.getEstado();
		this.cidade = mensageiro.getCidade();
		this.distrito = mensageiro.getDistrito();
		this.endereco = mensageiro.getEndereco();
		
	}
	
	
	//daqui para baixo apenas getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCEP() {
		return CEP;
	}


	public String getEstado() {
		return estado;
	}


	public String getCidade() {
		return cidade;
	}


	public String getDistrito() {
		return distrito;
	}


	public String getEndereco() {
		return endereco;
	}


	public int getNumero() {
		return numero;
	}
	

}
