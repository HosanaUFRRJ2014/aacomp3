package dominio;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projetoDAO.CaronaDAO;


@WebServlet("/Carona")
public class Carona extends HttpServlet
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcao = request.getParameter("opcao");
		
		if(opcao.equals("criarCarona"))
		{
			this.criarCarona(request, response);
		}
		
		else if(opcao.equals("alterarCarona"))
		{
			System.out.println("Falta implementar isso");
		}
		
		
	}
	
	/**
	 * Para usar dentro do doPost.
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 * @throws ServletException 
	 * **/
	
	public void criarCarona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		
		Usuario recuperado = (Usuario)session.getAttribute("novoUsuario");
		
		
		String diaCarona = request.getParameter("diaCarona");
		String horaSaida = request.getParameter("horaSaida");
		String veiculoEscolhido = request.getParameter("veiculoEscolhido");
		
		//pega informa��es do CEP do logradouro Origem
		String cepOrigem = request.getParameter("cepOrigem");
		String estadoOrigem = request.getParameter("ufOrigem");
		String cidadeOrigem = request.getParameter("cidadeOrigem");
		String distritoOrigem = request.getParameter("bairroOrigem");
		String enderecoOrigem = request.getParameter("ruaOrigem");		
		String numero1 = request.getParameter("numeroOrigem");
		int numeroOrigem = Integer.parseInt(numero1);
		
		Logradouro origem = new Logradouro(cepOrigem,estadoOrigem,cidadeOrigem,distritoOrigem,enderecoOrigem, numeroOrigem);
		
		//pega informa��es do CEP do logradouro destino
		String cepDestino = request.getParameter("cepDestino");
		String estadoDestino = request.getParameter("ufDestino");
		String cidadeDestino = request.getParameter("cidadeDestino");
		String distritoDestino = request.getParameter("bairroDestino");
		String enderecoDestino = request.getParameter("ruaDestino");	
		String numero2 = request.getParameter("numeroDestino");
		int numeroDestino = Integer.parseInt(numero2);
		
		Logradouro destino = new Logradouro(cepDestino,estadoDestino,cidadeDestino,distritoDestino,enderecoDestino, numeroDestino);
		
		
		
		//para transformar os strings recebidos em Data para o Banco
		DateFormat formata = new SimpleDateFormat("dd/mm");
		
		try {
			java.util.Date dia = formata.parse(diaCarona);
			Date diaSQL = new Date(dia.getTime());
			Time hora = Time.valueOf(horaSaida);
			
			//verifica se logradouro origem j� existe, se sim seta o ID do logradouro para podermos colocar ele na ParadaOrigem
			if(origem.verifica()){
				origem.recuperaID();
			}
			//logradourou n�o existe ainda, deve ser armazenado
			else{
				origem.armazena();
				origem.recuperaID();
			}
			
			//verifica se logradouro destino j� existe
			if(destino.verifica()){
				destino.recuperaID();
			}
			//logradourou n�o existe ainda, deve ser armazenado
			else{
				origem.armazena();
				origem.recuperaID();
			}
			
			String[] infoVeiculo = veiculoEscolhido.split("/");
			Veiculo aux = new Veiculo();
			Motorista motorista = recuperado.virarMotorista(aux);
			aux.recuperaID(infoVeiculo[0], infoVeiculo[1]);
			
			Veiculo montado = aux.recuperaCarro(aux.getID());
			montado.setMotorista(motorista);
					
			
			//finalmente adicionar carona
			
			//Carona novaCarona = new Carona(montado,diaSQL, hora, origem, destino);
			this.setVeiculo(montado);
			this.setDia(diaSQL);
			this.setHorarioSaida(hora);
			this.setLogrOrigem(origem);
			this.setLogrDestino(destino);
			this.armazena();
			
		} catch (ParseException e) {
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void armazena() throws ClassNotFoundException{
		
		CaronaDAO novaCarona = new CaronaDAO();
		
		novaCarona.adicionarCarona(this.veiculo.getMotorista().getEmail(), this.dia, this.horarioSaida, this.logrOrigem.getId(), this.logrDestino.getId(),this.veiculo.getNumeroVagas(), this.veiculo.getID());

	}
	
	//daqui para baixo apenas getters and setters
	

	public int getId() {
		return id;
	}

	private void setDia(Date dia) {
		this.dia = dia;
	}

	private void setHorarioSaida(Time horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	private void setLogrOrigem(Logradouro logrOrigem) {
		this.logrOrigem = logrOrigem;
	}

	private void setLogrDestino(Logradouro logrDestino) {
		this.logrDestino = logrDestino;
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
