package dominio;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuario 
{
	private String nome;
	private String email;
	private String telefone;
	
	//não funciona, pois usuario deve estar ativo ou inativo
	//em cada grupo que ele participa. Ver Map ou hash
//	private boolean ativo;
	HashMap<Grupo, Boolean> grupos;
	//private ArrayList <Grupo> gruposParticipados;
	
	public Usuario(String n, String e, String t)
	{
		nome = n;
		email = e;
		telefone = t;
		
		grupos = new HashMap<Grupo, Boolean>();
	//	grupos.put(grupo,true);
		
///		gruposParticipados = new ArrayList<Grupo>();
	//	ativo = true;
	}
	
	public boolean participar(Grupo grupo)
	{
		return grupos.put(grupo,true);
	}
	
	public void convidar(String email)
	{
		//Não sei o que por aqui dentro. 
		//Me veio na mente várias coisas de web service que fogem ao escopo do trabalho.
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

	/*
	public boolean isAtivo() {
		return ativo;
	} */

	/*
	public void serTornadoInativo() {
		this.ativo = ativo;
	}*/
	

/*	public ArrayList<Grupo> getGruposParticipados() {
		return gruposParticipados;
	}

	public void setGruposParticipados(ArrayList<Grupo> gruposParticipados) {
		this.gruposParticipados = gruposParticipados;
	}
*/
	public String getEmail() {
		return email;
	}
	
	
	

}
