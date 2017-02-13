package dominio;

import java.util.ArrayList;

import projetoDAO.VeiculoDAO;

public class Veiculo 
{
	private String modelo;
	private String placa;
	private String cor;
	private Motorista motorista;
	private int numeroVagas;
	
	private ArrayList<Carona> caronas;
	
	public Veiculo(String modelo,String placa,String cor,int numeroVagas)
	{
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;		
		
		caronas = new ArrayList<Carona>();
		
	}
	
	public Veiculo(String modelo,String placa,String cor,int numeroVagas, Motorista motorista)
	{
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
		this.motorista = motorista;
		
		caronas = new ArrayList<Carona>();
		
	}

	public String getCor() 
	{
		return cor;
	}

	public void setCor(String cor) 
	{
		this.cor = cor;
	}

	public String getModelo() 
	{
		return modelo;
	}

	public String getPlaca() 
	{
		return placa;
	}
	
	public int getNumeroVagas()
	{
		return numeroVagas;
	}
	
	public Motorista getMotorista()
	{
		return motorista;
	}
	
	public boolean addCarona(Carona carona)
	{
		return this.getCaronas().add(carona);
		
	}

	public ArrayList<Carona> getCaronas() 
	{
		return caronas;
	}

	public void setCaronas(ArrayList<Carona> caronas) 
	{
		this.caronas = caronas;
	}

	public void setMotorista(Motorista novoMotorista) {
		this.motorista = novoMotorista;
		
	}

	public void armazenar(String emailDono) throws ClassNotFoundException 
	{
		VeiculoDAO veiculodao = new VeiculoDAO();
		
		veiculodao.adicionaVeiculo(emailDono, this.placa, this.cor, this.modelo);
		
		
	}
	
	
	
	

}
