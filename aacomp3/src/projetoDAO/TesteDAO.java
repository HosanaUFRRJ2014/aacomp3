package projetoDAO;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;

import dominio.Grupo;
import dominio.Usuario;
import dominio.Veiculo;

public class TesteDAO {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub				
		
		
		Usuario aux = new Usuario();
		ParticipaDAO aux2 = new ParticipaDAO();
		Grupo aux3 = new Grupo();
		
		Usuario novo = aux.montaUsuario("dessa@hotmail.com.br");
		ArrayList<Integer> ids = aux2.gruposDoUsuario(novo.getEmail());
		
		
		//novo.getGruposQueUsuarioEstaAtivo().get(0).getNome();
		
		System.out.println(novo.getNome());
		System.out.println(ids.get(0));
		
		Grupo grupo = aux3.recuperaGrupo(ids.get(0), novo);
		
		System.out.println(grupo.isAtivo());
		
	}
}

