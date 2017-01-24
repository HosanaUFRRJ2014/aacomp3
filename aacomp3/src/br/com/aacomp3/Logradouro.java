package br.com.aacomp3;

public class Logradouro 
{
	private String CEP;
	private String estado;
	private String cidade;
	private String distrito;
	private String endereco;
	private int numero;
	
	
	public Logradouro(String CEP, String estado, String cidade, String distrito, String endereco, int numero)
	{
		this.CEP = CEP;
		this.estado = estado;
		this.cidade = cidade;
		this.distrito = distrito;
		this.endereco = endereco;
		this.numero = numero;
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
	
	

	/*Fazer a verificação do CEP!!!*/
	
	
	
	

}
