package dominio;

import java.util.ArrayList;
import java.util.HashMap;

import projetoDAO.UsuarioDAO;

public class Usuario 
{
	private String nome;
	private String email;
	private String telefone;
	
	//cada usuário pode ser avaliado no aplicativo.
	//se recebido mais de 3 avaliações ruins, ele é expulso do grupo.
	private HashMap<Grupo, ArrayList<Avaliacao>> avaliacoesPorGrupo;
	
	//não funciona, pois usuario deve estar ativo ou inativo
	//em cada grupo que ele participa. Ver Map ou hash
//	private boolean ativo;
	private HashMap<Grupo, Boolean> grupos;
	//private ArrayList <Grupo> gruposParticipados;
	
	public Usuario(String nome, String email, String telefone)
	{
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		
		ArrayList avaliacao = new ArrayList<Avaliacao>();
		avaliacoesPorGrupo = new HashMap<Grupo, ArrayList<Avaliacao>>();
		//continuar a instanciação de avaliacoesPorGrupo
		
		
		grupos = new HashMap<Grupo, Boolean>();

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
		return grupos.put(grupo,true);
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
		
		UsuarioDAO aux = new UsuarioDAO();
		
		return aux.procuraMotorista(email);		
		
	}

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
	
	public void armazenar() throws ClassNotFoundException
	{
		UsuarioDAO usuariodao = new UsuarioDAO();
		
		usuariodao.adicionaUsuario(this.nome, this.email, this.telefone);
	}
	
	// verifica se o email est� cadastrado no banco de dados
	public boolean verificaEmail(String email) throws ClassNotFoundException
	{
		UsuarioDAO usuariodao = new UsuarioDAO();
		
		if(usuariodao.verificaEmail(email)){
			return true;
		}
		
		return false;
	}
	
	public void alterar(String email,String nome, String telefone) throws ClassNotFoundException
	{		
		UsuarioDAO aux = new UsuarioDAO();
		
		aux.mudaInformacoes(email, nome, telefone);
		
	}
	
	public Usuario montaUsuario(String email) throws ClassNotFoundException{
		
		UsuarioDAO aux = new UsuarioDAO();
		
		ArrayList<String> info = aux.recuperaPorEmail(email);
		
		Usuario retorno = new Usuario(info.get(0),info.get(1),info.get(2));
		
		return retorno;
		
	}
	
	//pensando em colocar os métodos convidar e participar tb, pois
	//se tratam de regras de negócio. Nesse caso, esses métodos acessariam
	//a camada de dados tb. Penso isso baseada no livro de Padrões Arquiteturais
	//do Martin Fowler e nos exemplos da disciplina.
	
	
	
	

}
