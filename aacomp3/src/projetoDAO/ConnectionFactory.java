package projetoDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionFactory {
	
public Connection getConnection() throws ClassNotFoundException{
		
		try{
		    Class.forName("org.postgresql.Driver");
			//return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","thi802139");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
		}
		catch (SQLException e) {
			System.out.println("Erro de conexão com a base de dados porque minha senha é diferente da sua."
					+ " Favor, trocar a senha dentro do try e conectar à sua base. Arquivo ConnectionFactory.java");
			throw new RuntimeException(e);
		}				
	}

}
