package dominio;

import java.sql.Date;
import java.util.ArrayList;

public class Motorista extends Usuario {

	private ArrayList<Veiculo> veiculos;

	
	public Motorista(String nome, String email, String telefone, Veiculo veiculo) {
		super(nome, email, telefone);
		veiculos = new ArrayList<Veiculo>();
		veiculos.add(veiculo);
	}
	
	public boolean addVeiculo(Veiculo veiculo)
	{
		return veiculos.add(veiculo);
	}
	
	public void criarCarona(Veiculo veiculo, Date dia, Date horarioSaida, Logradouro logrOrigem, Logradouro logrDestino)
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
	

}
