package dominio;

import java.util.ArrayList;

import projetoTDG.VeiculoTDG;

public class Veiculo 
{
	private int id;
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
	

	public Veiculo(int ID,String modelo,String placa,String cor,int numeroVagas)
	{
		this.id = ID;
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
		
		VeiculoTDG aux = new VeiculoTDG();
		
		return aux.veiculosDeUmDono(email);
	}
	
	

	public void armazenar() throws ClassNotFoundException 
	{
		VeiculoTDG veiculodao = new VeiculoTDG();

		veiculodao.adicionaVeiculo(this.motorista.getEmail(), this.placa, this.cor, this.modelo,this.numeroVagas);


	}

	public void alterar(String novaCor) throws ClassNotFoundException {
		
		VeiculoTDG veiculodao = new VeiculoTDG();

		//precisa desse método no DAO
		veiculodao.mudaCor(this.id, novaCor);

	}	
	
	public void recuperaID(String modelo, String placa) throws ClassNotFoundException {
		
		VeiculoTDG aux = new VeiculoTDG();
		
		this.setID(aux.recuperaID(modelo,placa));
	}
	
	public Veiculo recuperaCarro(int ID) throws ClassNotFoundException{
		
		VeiculoTDG aux = new VeiculoTDG();
		ArrayList<String> info = aux.buscaInformacoes(ID);
		Veiculo novoMontado = new Veiculo(Integer.parseInt(info.get(0)),info.get(4),info.get(2),info.get(3),Integer.parseInt(info.get(5)));
		
		
		return novoMontado;
		
	}
	
	public ArrayList<String> recuperaInfo() throws ClassNotFoundException{
		
		VeiculoTDG aux = new VeiculoTDG();
		
		return aux.buscaInformacoes(this.id);
		
	}
	
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

	public void setID(int ID) {
		
		this.id = ID;

	}
	
	public int getID() {
		
		return this.id;	

	}




}
