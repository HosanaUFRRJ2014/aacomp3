package projetoDAO;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import dominio.Usuario;

public class TesteDAO {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub				
		
		
		GregorianCalendar data = new GregorianCalendar();
		String teste = "11/02";
		SimpleDateFormat dia = new SimpleDateFormat("dd/mm");
		Date teste2;
		Long mili = data.getTimeInMillis();
		String hora = "10:10:15";
		Time horas = new Time(mili);
		try {
			teste2 = dia.parse(teste);
			System.out.println(horas.toString());
			System.out.println(dia.format(teste2));
		} catch (ParseException e) {
			System.out.println("Você digitou data em um formato invalido!");
		}
		
		
		
		
	}

}
