package dominio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excecoes.CampoInvalidoException;
import dto.GrupoDTO;
import excecoes.JaExisteException;
import projetoTDG.*;



@WebServlet("/Grupo")
public class Grupo extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String descricao;
	private String regras; 

	private int limitMin;


	private boolean ativo;

	private ArrayList <Usuario> usuarios;

	public Grupo(){

	}

	//Default de min de avaliações ruins é 3, caso o dono não queira definir
	public Grupo(Usuario donoGrupo, String nomeGrupo, String descricao, String regras)
	{
		this.nome = nomeGrupo;
		this.descricao = descricao;
		this.regras = regras;
		limitMin = 3;
		usuarios = new ArrayList<Usuario>();
		usuarios.add(donoGrupo);


	}

	//construtor para caso o usuário queira padronizar o limMinAvaliacoesRuins
	public Grupo(Usuario donoGrupo, String nomeGrupo, String descricao, String regras, int lim)
	{
		this(donoGrupo, nomeGrupo, descricao,  regras);

		this.limitMin = lim;

	}

	//construtor para recuperar do banco de dados
	public Grupo(int ID,Usuario donoGrupo, String nomeGrupo, String descricao, String regras, int lim,boolean Ativo)
	{
		this(donoGrupo, nomeGrupo, descricao,  regras,lim);
		this.id = ID;
		this.ativo = Ativo;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String opcao = request.getParameter("opcao");


		if(opcao.equals("criarGrupo"))
		{
			this.criarGrupo(request, response);
		}

		else if(opcao.equals("alterarGrupo"))
		{
			this.alterarGrupo(request, response);
		}


	}


	/**
	 * Método para ser usado dentro do doPost.
	 * @throws ServletException, IOException
	 * 
	 * **/

	public void criarGrupo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
			HttpSession session = request.getSession();

			Usuario recuperado = (Usuario)session.getAttribute("novoUsuario");

			String nomeGrupo = request.getParameter("nomeGrupo");
			String regrasGrupo = request.getParameter("regrasGrupo");
			String descricaoGrupo = request.getParameter("descricaoGrupo");
			int limiteGrupo = 3;

			try {
				if(nomeGrupo.equals("") || regrasGrupo.equals("")|| regrasGrupo.equals("Digite as regras para o grupo aqui.") || descricaoGrupo.equals("")){
					throw new CampoInvalidoException();
				}	
				else if(this.garanteIntegridade(nomeGrupo, descricaoGrupo)){					
					throw new JaExisteException();					
				}
				else{					
					if(!request.getParameter("limiteAvalRuinsGrupo").equals("")){	
						limiteGrupo = Integer.parseInt(request.getParameter("limiteAvalRuinsGrupo"));	
					}
				}

				this.setNome(nomeGrupo);
				this.setDescricao(descricaoGrupo);
				this.setRegras(regrasGrupo);
				this.setLimMinAvaliacoesRuins(limiteGrupo);

				this.armazenar();
				this.recuperaID(this.getNome(),this.getDescricao());
				this.adicionarUsuario(recuperado);

				session.setAttribute("novoGrupo", this);

				RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucesso/sucessoCriarGrupo.jsp");
				rdSucesso.forward(request, response);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(CampoInvalidoException e){
				
				RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
				rdErro.forward(request, response);
			}
			catch(JaExisteException e){

				RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/jaExiste.jsp");
				rdErro.forward(request, response);

			}
		}
	}



/**
 * Método para ser usado dentro do doPost.
 * @throws IOException 
 * @throws ServletException 
 * 
 * **/

public void alterarGrupo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
{        

	HttpSession session = request.getSession();
	
	Grupo escolhido = (Grupo) session.getAttribute("grupoEscolhido");
	PrintWriter out = response.getWriter();
	out.println(escolhido);

	

	String nomeGrupo = escolhido.getNome();
	String descricaoGrupo = escolhido.getDescricao();

	String novoNome = request.getParameter("novoNome");
	String novaDescricao = request.getParameter("novaDescricao");
	String limiteDigitado = request.getParameter("novoLimMin");


	try 
	{		
		//tratando casos de campos em branco
		// todos campos em branco = erro
		if(novoNome.equals("") && novaDescricao.equals("") && limiteDigitado.equals("")){
			throw new CampoInvalidoException();
		}	
		if(novoNome.equals("")==false){
			this.setNome(novoNome);
		}
		if(novaDescricao.equals("")==false){
			this.setDescricao(novaDescricao);;
		}
		if(limiteDigitado.equals("")==false){
			int novoLimite = Integer.parseInt(limiteDigitado);
			this.setLimMinAvaliacoesRuins(novoLimite);;
		}
		if(this.garanteIntegridade(this.getNome(), this.getDescricao())){
			throw new JaExisteException();
		}

		this.recuperaID(nomeGrupo,descricaoGrupo);
		this.alterar(this.getNome(), this.getDescricao(), this.getLimMinAvaliacoesRuins());
		
		// uma vez alterado o grupo previamente escolhido, nao ha mais necessidade de armazena-lo
		session.removeAttribute("grupoEscolhido");

		RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucesso/sucessoAlterar.jsp");
		rdSucesso.forward(request,response);

	}
	catch (ClassNotFoundException e) {
		// eclipse me OBRIGOU a criar esse Try/Catch
		e.printStackTrace();
	}
	catch(CampoInvalidoException e){
		RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
		rdErro.forward(request, response);
	} catch (JaExisteException e) {
		RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/jaExiste.jsp");
		rdErro.forward(request, response);
	}

}


//m�todos referentes ao banco

public void armazenar() throws ClassNotFoundException, JaExisteException{

	GrupoTDG aux = new GrupoTDG();

	if(!aux.garanteIntegridade(this.nome, this.descricao)){
		throw new JaExisteException();
	}else{
		aux.adicionaGrupo(this.nome, this.descricao, this.regras, this.limitMin);
	}		
}

public void alterar(String novoNome,String novaDescricao,int novoLimite) throws ClassNotFoundException{


	GrupoTDG aux = new GrupoTDG();


	aux.alteraGrupo(this.id, novoNome, novaDescricao, novoLimite);

}

public void recuperaID(String nome, String descricao) throws ClassNotFoundException{

	GrupoTDG aux = new GrupoTDG();

	this.setId(aux.recuperaID(nome,descricao));

}

public void adicionarUsuario(Usuario novoUsuario) throws ClassNotFoundException
{

	ParticipaTDG aux = new ParticipaTDG();

	aux.adicionaParticipa(novoUsuario.getEmail(), this.id);	

}


public void recuperaUsuarios() throws ClassNotFoundException{

	ParticipaTDG auxParticipa = new ParticipaTDG();		

	Usuario auxUsuario = new Usuario();

	ArrayList<String> emails = auxParticipa.usuariosDoGrupo(this.id);
	ArrayList<Usuario> montar = new ArrayList<Usuario>();
	
	for(String email : emails){		
		
		auxUsuario.montaUsuario(email);			
		montar.add(auxUsuario);

	}
	this.setUsuarios(montar);			
}

public void recuperaGrupo(int ID) throws ClassNotFoundException{

	GrupoTDG auxGrupo = new GrupoTDG();
	GrupoDTO mensageiro = auxGrupo.recuperaGrupo(ID);
	
	this.id = mensageiro.getId();
	this.nome = mensageiro.getNome();
	this.descricao = mensageiro.getDescricao();
	this.regras = mensageiro.getRegras();
	this.ativo = mensageiro.isAtivo();
	this.limitMin = mensageiro.getLimitMin();
	

}

public boolean garanteIntegridade(String nome, String descricao) throws ClassNotFoundException{

	GrupoTDG auxGrupo = new GrupoTDG();

	return auxGrupo.garanteIntegridade(nome, descricao);
}




//daqui para baixo apenas getters and setters


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() 
{
	return nome;
}

public void setNome(String nome) 
{
	this.nome = nome;
}

public String getDescricao() 
{
	return descricao;
}

public void setDescricao(String descricao) 
{
	this.descricao = descricao;
}

public int getLimMinAvaliacoesRuins() 
{
	return limitMin;
}

public void setLimMinAvaliacoesRuins(int limMinAvaliacoesRuins)
{
	this.limitMin = limMinAvaliacoesRuins;
}

public boolean isAtivo() 
{
	return this.ativo;
}

//	public boolean desativarGrupo(boolean ativo) 
//	{
//		if(todosUsuariosInativos(this.usuarios) == true)
//		{
//			this.ativo = false;
//			//grupo desativado com sucesso
//			return true; 
//		}
//	//	else
//		System.out.println("Grupo tem um usuário ativo. Não pode ser destivado no momento"); //println temporário
//	    return false;
//	}

public ArrayList<Usuario> getUsuarios() 
{
	return usuarios;
}

public void setUsuarios(ArrayList<Usuario> usuarios)
{
	this.usuarios = usuarios;
}

public String getRegras() 
{
	return regras;
}

public boolean desativar(Usuario usuario)
{



	return false;

}

public void setRegras(String regras) {
	this.regras = regras;
}

public void setAtivo(boolean ativo) {
	this.ativo = ativo;
}



}
