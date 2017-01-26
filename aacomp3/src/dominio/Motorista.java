package dominio;

import java.util.ArrayList;

public class Motorista extends Usuario {

	private ArrayList<Veiculo> veiculos;
	
	public Motorista(String n, String e, String t, Veiculo v) {
		super(n, e, t);
		veiculos = new ArrayList<Veiculo>();
		veiculos.add(v);
	}
	
	public boolean addVeiculo(Veiculo veiculo)
	{
		return veiculos.add(veiculo);
	}
	
	public void criarCarona()
	{
		
	}
	
	

}
