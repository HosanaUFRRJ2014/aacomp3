package projetoTDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ParadaDTO;

public class ParadaTDG {

	private Connection conexao;
	
	public ParadaTDG() throws ClassNotFoundException{
		
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
	
	public ArrayList<ParadaDTO> paradasDeUmaCarona(int idCarona){
		
		String sql = "select * from parada where idcarona=?";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			
			stmt.setInt(1, idCarona);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<ParadaDTO> retorno = new ArrayList<ParadaDTO>();
			
			while(rs.next()){
				ParadaDTO parada = new ParadaDTO();
				parada.setId(rs.getInt(1));
				parada.set
			}
			stmt.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
		
		
	}
	
}
