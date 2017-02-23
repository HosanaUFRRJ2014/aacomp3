package dominio;

import java.sql.Date;
import java.sql.Time;

import projetoDAO.CaronaDAO;

public class Carona 
{
	private int id;
	private Veiculo veiculo;
	private Date dia;
	private Time horarioSaida;
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
	 * 
	 * 
	 * É necessário salvar quais usuários estão na carona ou só preciso
	 * saber o numero de vagas? Se preciso salvar, salvo em Caronas. (esxiste a posssibilidade de
	 * salvar em veículos)
	 * *******/

	public Carona(Veiculo veiculo, Date dia, Time horarioSaida, Logradouro logrOrigem, Logradouro logrDestino) 
	{
		this.veiculo = veiculo;
		this.dia = dia;
		this.horarioSaida = horarioSaida;
		this.logrOrigem = logrOrigem;
		this.logrDestino = logrDestino;
		this.cancelada = false;
	}
	
	public void armazena() throws ClassNotFoundException{
		
		CaronaDAO novaCarona = new CaronaDAO();
		
		novaCarona.adicionarCarona(this.veiculo.getMotorista().getEmail(), this.dia, this.horarioSaida, this.logrOrigem.getId(), this.logrDestino.getId(),this.veiculo.getNumeroVagas(), this.veiculo.getID());

	}
	
	//daqui para baixo apenas getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


	public Time getHorarioSaida() {
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


	public void alterar()
	{
		//comunicacao com a camada de dados
	}






}
