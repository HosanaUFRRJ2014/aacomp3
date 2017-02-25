package projetoTDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

	public class ParticipaTDG {
	
	private Connection conexao;
	
	public ParticipaTDG() throws ClassNotFoundException{
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public void adicionaParticipa(String emailUsuario, int idGrupo){
		
		String sql = "insert into participa" + "(emailusuario,idgrupo,ativo)" + "values(?,?,true)";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,emailUsuario);
			stmt.setInt(2, idGrupo);
			
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}	
	}
	
	//retorna o nome de todos grupos em que um usuario está
	public ArrayList<Integer> gruposDoUsuario(String emailUsuario){
		
		String sql = "select idgrupo from participa where emailusuario=?";
		
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, emailUsuario);
			
			ResultSet rs = stmt.executeQuery();			
			
			//retorno será List que irá receber os ids dos grupos
			ArrayList<Integer> retorno = new ArrayList<Integer>();	
			
			while(rs.next()){
				retorno.add(rs.getInt(1));
			}
			rs.close();
			stmt.close();
			return retorno;
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	//retorna o email de todos usuarios de um grupo
	public ArrayList<String> usuariosDoGrupo(int idGrupo){
		
		String sql = "select emailusuario from participa where idgrupo=?";
		
			
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1, idGrupo);
			
			ResultSet rs = stmt.executeQuery();			
			
			//retorno será List que irá receber os emails dos usuarios
			ArrayList<String> retorno = new ArrayList<String>();	
			
			while(rs.next()){
				retorno.add(rs.getString(1));
			}
			rs.close();
			stmt.close();
			return retorno;
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	
	
}
