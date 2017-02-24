package projetoTDG;

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
import dto.UsuarioDTO;

public class TesteTDG {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub				
		
		
		UsuarioTDG aux = new UsuarioTDG();
		ParticipaTDG aux2 = new ParticipaTDG();
		
		UsuarioDTO mensageiro = aux.recuperaPorEmail("dessa@hotmail.com.br");
		
		ArrayList<Integer> grupos = aux2.gruposDoUsuario(mensageiro.getEmail());
		
		for(int a : grupos){
			System.out.println(a);
		}
		
		
		
	}
}

