package dominio;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Motorista extends Usuario {


	private ArrayList<Veiculo> veiculos;

	
	//apenas para facilitar na jsp
	public Motorista()
	{
		
	}
	
	public Motorista(String nome, String email, String telefone, Veiculo veiculo) {
		super(nome, email, telefone);
		veiculos = new ArrayList<Veiculo>();
		veiculos.add(veiculo);
	}
	
	public boolean addVeiculo(Veiculo veiculo)
	{
		return veiculos.add(veiculo);
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

	public ArrayList<Veiculo> getVeiculos() {
		return veiculos;
	}
	
	
	
	

}
