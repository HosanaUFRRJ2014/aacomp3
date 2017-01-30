package dominio;

import java.sql.Date;

public class Carona 
{
	private Veiculo veiculo;
	private Date dia;
	private Date horarioSaida;
	private Logradouro logrOrigem;
	private Logradouro logrDestino;
	private boolean cancelada;
	
	
	/******Falta tratar:
	 * Uma carona pode ser criada/alterada
	 * O  veículo  e  o  usuário 
	 *  não  estejam  associados  a  nenhuma  
	 *  carona  no  mesmo  dia  e  horário  (em 
     * TODOS os grupos que o 
     * usuário participa).
	 * *******/
	public Carona(Veiculo veiculo, Date dia, Date horarioSaida, Logradouro logrOrigem, Logradouro logrDestino) 
	{
		this.veiculo = veiculo;
		this.dia = dia;
		this.horarioSaida = horarioSaida;
		this.logrOrigem = logrOrigem;
		this.logrDestino = logrDestino;
		this.cancelada = false;
	}



	
	private void setVeiculo(Veiculo veiculo)
	{
		this.veiculo = veiculo;
	}

	public boolean setCancelada(boolean valor)
	{
		cancelada = valor;
		
		return cancelada;
		
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	} 
	
	public Date getDia() {
		return dia;
	}


	public Date getHorarioSaida() {
		return horarioSaida;
	}


	public Logradouro getLogrOrigem() {
		return logrOrigem;
	}


	public Logradouro getLogrDestino() {
		return logrDestino;
	}
	
	public boolean isCancelada()
	{
		return cancelada;
	}
	
	public boolean alterarVeiculo(Veiculo veiculoNovo)
	{
		//se o número de vagas não vai se alterar
		if(veiculoNovo.getNumeroVagas() == veiculo.getNumeroVagas())
		{
			setVeiculo(veiculoNovo);
			return true;
		}
		
		return false;
		
	}
	

	public void armazenar()
	{
		//comunicacao com a camada de dados;
	}
	
	public void alterar()
	{
		//comunicacao com a camada de dados
	}
	
	
	
	
	

}
