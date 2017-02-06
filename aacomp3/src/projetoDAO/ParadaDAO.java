package projetoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParadaDAO {

	private Connection conexao;
	
	public ParadaDAO() throws ClassNotFoundException{
		
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	//usuario adiciona 
	public void adicionarParada(int idUsuario, int idLogradouro, int idCarona) throws ClassNotFoundException{
		//esse será um método bem complicado de se fazer... 
		
		// idUsuario neste caso faz referencia ao Caroneiro
		
		String sql = "insert into paradas" + "(idusuario,idlogradouro,idveiculo)" + "values(?,?,?)";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			
			stmt.setInt(1, idUsuario);
			stmt.setInt(2, idLogradouro);
			stmt.setInt(3, idCarona);					
			
			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	
}
