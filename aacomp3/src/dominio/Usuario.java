package dominio;

import java.util.ArrayList;
import java.util.HashMap;

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
		this.participar(grupo);
	}
	
	public void criarGrupo(String nome, String descricao, String regras, int limMaxAvaliacoesRuins)
	{
		Grupo grupo = new Grupo(this, nome, descricao, regras, limMaxAvaliacoesRuins);
		this.participar(grupo);
	}
	
	public boolean participar(Grupo grupo)
	{
		return grupos.put(grupo,true);
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
	
	public void armazenar()
	{
		//manipulações do banco de dados usando a classe UsuarioTDG do
		//do pacote dados
	}
	
	public void alterar(String nome, String telefone)
	{
		//manipulações do banco de dados usando a classe UsuarioTDG do
		//do pacote dados
		
	}
	
	//pensando em colocar os métodos convidar e participar tb, pois
	//se tratam de regras de negócio. Nesse caso, esses métodos acessariam
	//a camada de dados tb. Penso isso baseada no livro de Padrões Arquiteturais
	//do Martin Fowler e nos exemplos da disciplina.
	
	//Como fazer isso sem service layer?
	
	
	

}
