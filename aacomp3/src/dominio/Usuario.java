package dominio;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecoes.CampoInvalidoException;
import projetoTDG.ParticipaTDG;
import projetoTDG.UsuarioTDG;

@WebServlet("/Usuario")
public class Usuario extends HttpServlet
{	
	private String nome;
	private String email;
	private String telefone;
	
	//cada usuário pode ser avaliado no aplicativo.
	//se recebido mais de 3 avaliações ruins, ele é expulso do grupo.
	private HashMap<Grupo, ArrayList<Avaliacao>> avaliacoesPorGrupo;


	//private LinkedHashMap<Grupo, Boolean> grupos;
	private LinkedList <Grupo> gruposQueUsuarioEstaAtivo;
	private LinkedList <Grupo> gruposQueUsuarioEstaInativo;


	//não funciona, pois usuario deve estar ativo ou inativo
	//em cada grupo que ele participa. Ver Map ou hash
//	private boolean ativo;
	private HashMap<Grupo, Boolean> grupos;
	//private ArrayList <Grupo> gruposParticipados;

	//apenas para a busca do Usuario no caso de criarGrupo
	public Usuario()
	{
        super();
	}
	
	public Usuario(String nome, String email, String telefone)
	{
		super();
		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		
		ArrayList avaliacao = new ArrayList<Avaliacao>();
		avaliacoesPorGrupo = new HashMap<Grupo, ArrayList<Avaliacao>>();
		//continuar a instanciação de avaliacoesPorGrupo



		gruposQueUsuarioEstaAtivo = new LinkedList<Grupo>();
		gruposQueUsuarioEstaInativo = new LinkedList<Grupo>();


	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("nomeUsuario");
		String telefone = request.getParameter("telefoneUsuario");
		String email = request.getParameter("emailUsuario");

		Usuario novoUsuario = new Usuario(nome, email, telefone); //mudar isso aqui!!!

		try{

			if(nome.equals("") || telefone.equals("") || email.equals("")){
				throw new CampoInvalidoException();
			}			
			else{				
				try {
					novoUsuario.armazenar();
					
					// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar ou n�o um veiculo
					request.setAttribute("novoUsuario", novoUsuario);
					RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucessoCadastro.jsp");
					rdSucesso.forward(request,response);
					
				} catch (ClassNotFoundException e) {
					// eclipse me OBRIGOU a criar esse Try/Catch
					e.printStackTrace();
				}
			}
		}catch(CampoInvalidoException e){	
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}



		


	}

	

	public void armazenar() throws ClassNotFoundException
	{
		UsuarioTDG usuariodao = new UsuarioTDG();
		
		usuariodao.adicionaUsuario(this.nome, this.email, this.telefone);
	}
	
	// verifica se o email est� cadastrado no banco de dados
	public boolean verificaEmail(String email) throws ClassNotFoundException
	{
		UsuarioTDG usuariodao = new UsuarioTDG();
		
		if(usuariodao.verificaEmail(email)){
			return true;
		}
		
		return false;
	}
	
	public void alterar(String nome, String telefone) throws ClassNotFoundException
	{		
		UsuarioTDG aux = new UsuarioTDG();
		
		aux.mudaInformacoes(this.email, nome, telefone);
		
	}
	
	public Usuario montaUsuario(String email) throws ClassNotFoundException{
		
		UsuarioTDG aux = new UsuarioTDG();
		Grupo auxGrupo = new Grupo();
		
		ArrayList<String> info = aux.recuperaPorEmail(email);
		
		Usuario retorno = new Usuario(info.get(0),info.get(1),info.get(2));
		
		ParticipaTDG aux2 = new ParticipaTDG();
		
		//pega o ID de todos grupos que o usuario est�
		ArrayList<Integer> gruposDoUsuario = aux2.gruposDoUsuario(email);
		
		//usuario est� em nenhum grupo? pode retornar
		if(gruposDoUsuario.isEmpty()){
			return retorno;
		}
		LinkedList<Grupo> ativos = new LinkedList<Grupo>();
		LinkedList<Grupo> inativos = new LinkedList<Grupo>();
		
		for(int contador = 0; contador<gruposDoUsuario.size();contador++){
			
			//cria grupo que o usuario est�
			Grupo doUsuario = auxGrupo.recuperaGrupo(gruposDoUsuario.get(contador), retorno);
			
			//se o usuario estiver ativo no grupo adiciona no linked list ativo
			if(doUsuario.isAtivo()){
				ativos.add(doUsuario);
			}
			else{
				inativos.add(doUsuario);
			}
		}
		
		retorno.setGruposQueUsuarioEstaAtivo(ativos);
		retorno.setGruposQueUsuarioEstaInativo(inativos);
		return retorno;
		
	}
	
	
	public void avaliar(Grupo grupo,Usuario usuarioAvaliado, int estrelas)
	{
		Avaliacao avaliacao = new Avaliacao(estrelas);
		
		//pegar do hashmap avaliacoesPorGrupo, dado
		//o grupo do usuario que está avaliado, colocar
		// o número de estrelas referentes.
		
		ArrayList<Avaliacao> avaliacoes = usuarioAvaliado.getAvaliacoesPorGrupo().get(grupo);
		avaliacoes.add(avaliacao);
		
		usuarioAvaliado.getAvaliacoesPorGrupo().put(grupo, avaliacoes);
	
		
		
	}	
	
	
	public void criarGrupo(String nome, String descricao, String regras)
	{
		Grupo grupo = new Grupo(this, nome, descricao, regras);
		this.participarDoGrupo(grupo);
	}
	
	public void criarGrupo(String nome, String descricao, String regras, int limMaxAvaliacoesRuins)
	{
		Grupo grupo = new Grupo(this, nome, descricao, regras, limMaxAvaliacoesRuins);
		this.participarDoGrupo(grupo);
	}
	
	public boolean participarDoGrupo(Grupo grupo)
	{
		//ver método adicionarUsuario em Grupo.java
		return gruposQueUsuarioEstaAtivo.add(grupo);
	}
	
	
	/** Está removendo o usuário do array de usuários do grupo, 
	 * não está apenas inativando-o **/
	public void sairDoGrupo(Grupo grupo)
	{
		grupo.getUsuarios().remove(this);
	}
	
	public void convidar(Grupo grupo, String email)
	{
		//Não sei o que por aqui dentro. 
		//Me veio na mente várias coisas de web service que fogem ao escopo do trabalho.
		
		//Pensando em exibir uma mensagem de convite para o usuário que vai enviar e ele só 
		//apertará um botão enviar ou um botão cancelar
		
		//só para testes
		System.out.println("Contato " + email + " convidado");
		
	}
	
	public Motorista virarMotorista(Veiculo veiculo)
	{
		Motorista motorista = new Motorista(this.getNome(), this.getEmail(), this.getTelefone(), veiculo);
		return motorista;
	}
	
	public boolean ehMotorista(String email) throws ClassNotFoundException{
		
		UsuarioTDG aux = new UsuarioTDG();
		
		return aux.procuraMotorista(email);		
		
	}
	
	
	
	
	//daqui para baixo apenas getters and setters

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public HashMap<Grupo, ArrayList<Avaliacao>> getAvaliacoesPorGrupo() 
	{
		return avaliacoesPorGrupo;
	}

	public void setAvaliacoesPorGrupo(HashMap<Grupo, ArrayList<Avaliacao>> avaliacoesPorGrupo) 
	{
		this.avaliacoesPorGrupo = avaliacoesPorGrupo;
	}

	public String getEmail() {
		return email;
	}


	public LinkedList<Grupo> getGruposQueUsuarioEstaAtivo() {
		return gruposQueUsuarioEstaAtivo;
	}

	public void setGruposQueUsuarioEstaAtivo(LinkedList<Grupo> gruposQueUsuarioEstaAtivo) {
		this.gruposQueUsuarioEstaAtivo = gruposQueUsuarioEstaAtivo;
	}

	public LinkedList<Grupo> getGruposQueUsuarioEstaInativo() {
		return gruposQueUsuarioEstaInativo;
	}

	public void setGruposQueUsuarioEstaInativo(LinkedList<Grupo> gruposQueUsuarioEstaInativo) {
		this.gruposQueUsuarioEstaInativo = gruposQueUsuarioEstaInativo;
	}
	

	
	//pensando em colocar os métodos convidar e participar tb, pois
	//se tratam de regras de negócio. Nesse caso, esses métodos acessariam
	//a camada de dados tb. Penso isso baseada no livro de Padrões Arquiteturais
	//do Martin Fowler e nos exemplos da disciplina.
	
	
	
	

}
