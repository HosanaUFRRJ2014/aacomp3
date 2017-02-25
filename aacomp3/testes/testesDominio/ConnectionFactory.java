package testesDominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import excecoes.ErroConexaoBaseDadosException;



public class ConnectionFactory {

	public Connection getConnection() throws ClassNotFoundException, ErroConexaoBaseDadosException{

		try{
			Class.forName("org.postgresql.Driver");
			//	return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","thi802139");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste","postgres","postgres");
		}
		catch (SQLException e) {
			throw new ErroConexaoBaseDadosException();
		}				
	}

}
