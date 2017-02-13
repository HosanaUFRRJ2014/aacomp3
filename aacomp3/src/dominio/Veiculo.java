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

	public Veiculo(){
		//para auxiliar na crian�ar de veiculos nas jsp
	}
	

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
	
	
	public ArrayList<String> veiculosDeUmDono(String email) throws ClassNotFoundException{
		
		VeiculoDAO aux = new VeiculoDAO();
		
		return aux.veiculosDeUmDono(email);
	}
	
	
//	public void armazenar(String emailDono) throws ClassNotFoundException 
//	{
//		VeiculoDAO veiculodao = new VeiculoDAO();
//		
//		veiculodao.adicionaVeiculo(emailDono, this.placa, this.cor, this.modelo);
//		
//		
//	}	
	
	//daqui para baixo apenas getters and setters
	
//	public void setMotorista(Motorista novoMotorista) {
//		this.motorista = novoMotorista;
//		
//	}

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

	public void alterar(String novaCor) {
		VeiculoDAO veiculodao = new VeiculoDAO();

		//precisa desse método no DAO
		veiculodao.mudaCor(novaCor);

	}





}
