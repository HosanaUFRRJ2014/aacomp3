package dominio;

import java.util.ArrayList;
import java.util.HashMap;

import projetoDAO.GrupoDAO;
import projetoDAO.ParticipaDAO;

public class Grupo 
{
	private int id;
	private String nome;
	private String descricao;
	private String regras; 
	
	private int limMinAvaliacoesRuins;
	
	private boolean ativo;
	
	private ArrayList <Usuario> usuarios;
	
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
		limMinAvaliacoesRuins = lim;
		
	}
	
	public void armazenar() throws ClassNotFoundException{
		
		GrupoDAO aux = new GrupoDAO();
		ParticipaDAO aux2 = new ParticipaDAO();
		
		aux.adicionaGrupo(this.nome, this.descricao, this.regras, this.limMinAvaliacoesRuins);
		aux2.adicionaParticipa(this.usuarios.get(0).getEmail(), this.id);
		
	}
	
	
	
	
	
	
	
	

	//daqui para baixo apenas getters and setters
	public boolean adicionarUsuario(Usuario u)
	{
		//u.participarDoGrupo(this); Eu dúvida se faço isso aqui ou faço externamente.
		return usuarios.add(u);
	}
	
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

	public long getLimMinAvaliacoesRuins() 
	{
		return limMinAvaliacoesRuins;
	}

	public void setLimMinAvaliacoesRuins(long limMinAvaliacoesRuins)
	{
		this.limMinAvaliacoesRuins = limMinAvaliacoesRuins;
	}

	public boolean isAtivo() 
	{
		return ativo;
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
