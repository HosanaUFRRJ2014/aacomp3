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
import dominio.Veiculo;

public class TesteDAO {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub				
		
		String teste = "fusca/UFO1523";
		String info[] = teste.split("/");
		Veiculo vei = new Veiculo();
		
		System.out.println(info[0]+"   " + info[1]);
		
		vei.recuperaID(info[0], info[1]);
		System.out.println(vei.getID());
		
		
		Veiculo novo = vei.recuperaCarro(vei.getID());
		System.out.println(novo.getModelo());
		
			ArrayList<String> informa = novo.recuperaInfo();
			for(int i=0;i<informa.size();i++){
				System.out.println(informa.get(i));
			}
		}
		
		
		
	}


