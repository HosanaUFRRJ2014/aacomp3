package br.com.aacomp3;

import java.util.ArrayList;
import java.util.HashMap;

public class Grupo 
{
	private String nome;
	private String descricao;
	private String regras; //ou um array de regras?
	
	private long limMinAvaliacoesRuins;
	
	private boolean ativo;
	
	private ArrayList <Usuario> usuarios;
	
	
	public Grupo(Usuario donoGrupo, String n, String d, String r, long lim)
	{
		nome = n;
		descricao = d;
		regras = r;
		limMinAvaliacoesRuins = lim;
		usuarios = new ArrayList<Usuario>();
		usuarios.add(donoGrupo);
	
		
	}
	
	public boolean adicionarUsuario(Usuario u)
	{
		return usuarios.add(u);
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
