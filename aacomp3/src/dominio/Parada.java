package dominio;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ParadaDTO;
import projetoTDG.ParadaTDG;

public class Parada extends HttpServlet {

	private int id;
	private Logradouro logradouro;
	private Carona carona;
	private Usuario caroneiro;
	private String tipo;
	
	public Parada(){
		super();
	}
	
	public Parada(Logradouro log, Carona car, Usuario usu, String tipo){		
		
		this.logradouro = log;
		this.carona = car;
		this.caroneiro = usu;
		this.setTipo(tipo);
		
	}
	
	public Parada(int ID, Logradouro log, Carona car, Usuario usu, String tipo){
		
		this.id = ID;
		this.logradouro = log;
		this.carona = car;
		this.caroneiro = usu;
		this.setTipo(tipo);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String opcao = request.getParameter("opcao");

		if(opcao.equals("novaParada"))
		{
			this.criarParada(request, response);
		}

		else if(opcao.equals("mesmaParada"))
		{
			System.out.println("Falta implementar isso");
		}


	}
	
	protected void criarParada(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();


		Usuario recuperado = (Usuario)session.getAttribute("novoUsuario");
	

		//pega informaï¿½ï¿½es do CEP do logradouro Origem
		String cepOrigem = request.getParameter("cepOrigem");
		String estadoOrigem = request.getParameter("ufOrigem");
		String cidadeOrigem = request.getParameter("cidadeOrigem");
		String distritoOrigem = request.getParameter("bairroOrigem");
		String enderecoOrigem = request.getParameter("ruaOrigem");		
		String numero1 = request.getParameter("numeroOrigem");
		int numeroOrigem = Integer.parseInt(numero1);

		Logradouro origem = new Logradouro(cepOrigem,estadoOrigem,cidadeOrigem,distritoOrigem,enderecoOrigem, numeroOrigem);
		
		//para transformar os strings recebidos em Data para o Banco
		
		

		try {		

			//verifica se logradouro origem jï¿½ existe, se sim seta o ID do logradouro para podermos colocar ele na ParadaOrigem
			if(origem.verifica()){
				origem.recuperaID();
			}
			//logradourou nï¿½o existe ainda, deve ser armazenado
			else{
				origem.armazena();
				origem.recuperaID();
			}
			
			this.carona = (Carona) session.getAttribute("novaCarona");
			this.caroneiro = (Usuario) session.getAttribute("novoCaroneiro");
			this.logradouro = origem;
			
			this.armazenar();
		
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	protected void adicionarCaroneiro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	
	// métodos para mexer no banco
	
	public void armazenar() throws ClassNotFoundException{
		
		ParadaTDG auxPara = new ParadaTDG();
		
		auxPara.adicionarParada(this.caroneiro.getEmail(), this.logradouro.getId(), this.carona.getId());
	}
	
	
	
	
	
	
	// daqui para abaixo apenas getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Logradouro getLog() {
		return logradouro;
	}

	public void setLog(Logradouro log) {
		this.logradouro = log;
	}

	public Carona getCarona() {
		return carona;
	}

	public void setCarona(Carona carona) {
		this.carona = carona;
	}

	public Usuario getCaroneiro() {
		return caroneiro;
	}

	public void setCaroneiro(Usuario caroneiro) {
		this.caroneiro = caroneiro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
