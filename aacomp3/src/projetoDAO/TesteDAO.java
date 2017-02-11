package projetoDAO;

import java.util.ArrayList;
import java.util.HashSet;

import dominio.Usuario;

public class TesteDAO {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub		
		
		UsuarioDAO teste = new UsuarioDAO();
		Usuario teste2 = new Usuario(null,null,null);
		String email = "adeberto@adeberto.com.br";
		
		ArrayList aux = teste.recuperaPorEmail(email);
		
		for(int i = 0; i < aux.size();i++){
			System.out.println(aux.get(i));
		}
		
		System.out.println(teste2.verificaEmail(email));
		System.out.println(teste.procuraEmail(8));
		
		
		
		
	}

}
