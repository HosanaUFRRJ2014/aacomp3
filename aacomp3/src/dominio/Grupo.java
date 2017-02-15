package dominio;

import java.util.ArrayList;
import java.util.HashMap;

import excecoes.JaExisteException;
import projetoDAO.GrupoDAO;
import projetoDAO.ParticipaDAO;
import projetoDAO.UsuarioDAO;

public class Grupo 
{
	private int id;
	private String nome;
	private String descricao;
	private String regras; 
	
	private int limMinAvaliacoesRuins;
	
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
		limMinAvaliacoesRuins = 3;
		usuarios = new ArrayList<Usuario>();
		usuarios.add(donoGrupo);
	
		
	}
	
	//construtor para caso o usuário queira padronizar o limMinAvaliacoesRuins
	public Grupo(Usuario donoGrupo, String nomeGrupo, String descricao, String regras, int lim)
	{
		this(donoGrupo, nomeGrupo, descricao,  regras);
		this.limMinAvaliacoesRuins = lim;
		
	}
	
	//construtor para recuperar do banco de dados
	public Grupo(int ID,Usuario donoGrupo, String nomeGrupo, String descricao, String regras, int lim,boolean Ativo)
	{
		this(donoGrupo, nomeGrupo, descricao,  regras,lim);
		this.id = ID;
		this.ativo = Ativo;
	}
	

	public void armazenar() throws ClassNotFoundException, JaExisteException{
		
		GrupoDAO aux = new GrupoDAO();
		
		if(!aux.garanteIntegridade(this.nome, this.descricao)){
			throw new JaExisteException();
		}else{
			aux.adicionaGrupo(this.nome, this.descricao, this.regras, this.limMinAvaliacoesRuins);
		}		
	}
	
	public void alterar(String novoNome,String novaDescricao,int novoLimite) throws ClassNotFoundException{
		
		GrupoDAO aux = new GrupoDAO();
		
		aux.alteraGrupo(this.id, novoNome, novaDescricao, novoLimite);
		
	}
	
	public void recuperaID() throws ClassNotFoundException{
		
		GrupoDAO aux = new GrupoDAO();
		
		this.setId(aux.recuperaID(this.nome, this.descricao));
		
	}
	
	public void adicionarUsuario(Usuario novoUsuario) throws ClassNotFoundException
	{
		
		ParticipaDAO aux = new ParticipaDAO();
		
		aux.adicionaParticipa(novoUsuario.getEmail(), this.id);	
		
	}
	
	public void recuperaUsuarios(int ID) throws ClassNotFoundException{
		
		ParticipaDAO aux = new ParticipaDAO();		
		Usuario auxUsu = new Usuario();
		
		ArrayList<String> emails = aux.usuariosDoGrupo(ID);
		
		for(int contador = 0; contador<emails.size(); contador++){			
			
			this.usuarios.add(auxUsu.montaUsuario(emails.get(contador)));
			
		}		
	}
	
	public Grupo recuperaGrupo(int ID,Usuario dono) throws ClassNotFoundException{
		
		GrupoDAO aux = new GrupoDAO();
		
		ArrayList<String> info = aux.recuperaGrupo(ID);
		
		Grupo retorno = new Grupo(Integer.parseInt(info.get(0)),dono,info.get(1),info.get(2),info.get(3),Integer.parseInt(info.get(4)),Boolean.parseBoolean(info.get(5)));
				
		return retorno;
		
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
		return limMinAvaliacoesRuins;
	}

	public void setLimMinAvaliacoesRuins(int limMinAvaliacoesRuins)
	{
		this.limMinAvaliacoesRuins = limMinAvaliacoesRuins;
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
	
//	public boolean todosUsuariosInativos(ArrayList<Usuario> us)
//	{
//		
//		for(Usuario u : us)
//		{
//			if(u.isAtivo() == true)
//				return false;
//		}
//		
//		return true;
//		
//	}
//	
	

}
