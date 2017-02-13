package dominio;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import projetoDAO.UsuarioDAO;

public class Usuario 
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

	//apenas para a busca do Usuario no caso de criarGrupo
	public Usuario()
	{

	}

	public Usuario(String nome, String email, String telefone)
	{
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;

		ArrayList avaliacao = new ArrayList<Avaliacao>();
		avaliacoesPorGrupo = new HashMap<Grupo, ArrayList<Avaliacao>>();
		//continuar a instanciação de avaliacoesPorGrupo


		gruposQueUsuarioEstaAtivo = new LinkedList<Grupo>();
		gruposQueUsuarioEstaInativo = new LinkedList<Grupo>();

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

	public void virarMotorista(Veiculo veiculo) throws ClassNotFoundException
	{
		UsuarioDAO usuariodao = new UsuarioDAO();
		usuariodao.mudaMotorista(email);

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

	public void alterar(String novoNome, String novoTelefone)
	{
		UsuarioDAO usuariodao = new UsuarioDAO();

		if(novoTelefone.equals("") && novoNome.equals("") == false)
		{

			setNome(novoNome);
			usuariodao.mudaNome(this.email, novoNome);
		}
		else if(novoNome.equals("")  && novoTelefone.equals("") == false)
		{
			setTelefone(novoTelefone);
			usuariodao.mudaTelefone(this.email, novoTelefone);
		}
		
		else if(novoNome.equals("") == false  && novoTelefone.equals("") == false)
		{
			setNome(novoNome);
			setTelefone(novoTelefone);
			
			usuariodao.mudaNome(email, this.nome);
			usuariodao.mudaTelefone(email, this.telefone);
			
			
		}


	}

	public Usuario montaUsuario(String email) throws ClassNotFoundException{

		UsuarioDAO aux = new UsuarioDAO();

		ArrayList<String> info = aux.recuperaPorEmail(email);

		Usuario retorno = new Usuario(info.get(2),info.get(3),info.get(4));

		return retorno;

	}

	//pensando em colocar os métodos convidar e participar tb, pois
	//se tratam de regras de negócio. Nesse caso, esses métodos acessariam
	//a camada de dados tb. Penso isso baseada no livro de Padrões Arquiteturais
	//do Martin Fowler e nos exemplos da disciplina.
	//=======
	//	
	//realizar busca no banco dado um email de Usu�rio
	/*A d�vida �: esse m�todo fica em Usuario. (vai exigir um monte de gambiarra, como construtor vazio)
	 *  ou fica em Grupo.(fica estranho, pois fica grupo lidando com usuario). Por hora, escolhendo a op��o 1 */
	public Usuario buscar(String email) throws ClassNotFoundException
	{
		UsuarioDAO  usuariodao = new UsuarioDAO();

		return usuariodao.buscarPorEmail(email);


	}


	//>>>>>>> refs/heads/hosana
	//	




}
