package dominio;

import java.util.ArrayList;
import java.util.HashMap;

import dto.GrupoDTO;
import excecoes.JaExisteException;
import projetoTDG.GrupoTDG;
import projetoTDG.ParticipaTDG;
import projetoTDG.UsuarioTDG;

public class Grupo 
{
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
	
	public void recuperaID(String nome,String descricao) throws ClassNotFoundException{
		
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
		
		for(int contador = 0; contador<emails.size(); contador++){		
			
			auxUsuario.montaUsuario(emails.get(contador));			
			this.usuarios.add(auxUsuario);			
		}		
	}
	
	public Grupo recuperaGrupo(int ID,Usuario dono) throws ClassNotFoundException{
		
		GrupoTDG auxGrupo = new GrupoTDG();
		GrupoDTO mensageiro = auxGrupo.recuperaGrupo(ID);
		
		
		Grupo retorno = new Grupo(mensageiro.getId(),dono,mensageiro.getNome(),mensageiro.getDescricao(),
				mensageiro.getRegras(),mensageiro.getLimitMin(),mensageiro.isAtivo());
				
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
