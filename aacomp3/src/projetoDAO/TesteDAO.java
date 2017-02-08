package projetoDAO;

import java.util.HashSet;

import dominio.Usuario;

public class TesteDAO {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub		
		
		UsuarioDAO teste = new UsuarioDAO();
		String email = "juberto@juberto.com";
		
		System.out.println(teste.verificaEmail(email));
		
		
		
		
	}

}
