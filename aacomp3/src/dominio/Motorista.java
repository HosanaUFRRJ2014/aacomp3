package dominio;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;

public class Motorista extends Usuario {


	private LinkedList<Veiculo> veiculos;

	
	//apenas para facilitar na jsp
	public Motorista()
	{
		
	}
	
	public Motorista(String nome, String email, String telefone, Veiculo veiculo) {
		super(nome, email, telefone);
		veiculos = new LinkedList<Veiculo>();
		veiculos.add(veiculo);
	}
	
	public boolean addVeiculo(Veiculo veiculo)
	{
		return veiculos.add(veiculo);
	}
	
	public void criarCarona(Veiculo veiculo, Date dia, Time horarioSaida, Logradouro logrOrigem, Logradouro logrDestino)
	{
		Carona carona = new Carona(veiculo, dia, horarioSaida, logrOrigem, logrDestino);
		veiculo.addCarona(carona);
	    
		//comunicao como banco de dados.  
		carona.armazenar();
	
	}
	
	public boolean alterarVeiculoCarona(Carona carona, Veiculo veiculo)
	{
		boolean alterado = carona.alterarVeiculo(veiculo);
         
		if(alterado == true)
		{
			//comunicao como banco de dados. 
			carona.alterar();
		}
		
		return alterado;
		
	}
	
	public void cancelarCarona(Carona carona)
	{
	     carona.setCancelada(true);	
	}

	public LinkedList<Veiculo> getVeiculos() {
		return veiculos;
	}
	
	
	
	

}
