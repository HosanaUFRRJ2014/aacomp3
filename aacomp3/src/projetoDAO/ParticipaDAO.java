package projetoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

	public class ParticipaDAO {
	
		private Connection conexao;
	
	public ParticipaDAO() throws ClassNotFoundException{
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public void adicionaParticipa(int idUsuario, int idGrupo){
		String sql = "insert into participa" + "(idusuario,idgrupo,ativo)" + "values(?,?,true)";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,idUsuario);
			stmt.setInt(2, idGrupo);
			
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}	
	}
	
	public Set<String> gruposDoUsuario(int idUsuario){
		
		String sql = "select nome from grupos where idgrupo=?";
		String sql2 = "select idgrupo from participa where idusuario=?";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
			
			stmt2.setInt(1,idUsuario);
			
			ResultSet rs2 = stmt2.executeQuery();
			ArrayList<Integer> idGrupos = new ArrayList<Integer>();
			while(rs2.next()){
				idGrupos.add(rs2.getInt(1));
			}
			rs2.close();
			stmt2.close();
			Set<String> retorno = new HashSet<String>();			
			for(int contador = 0;contador<idGrupos.size();contador++){
				stmt.setInt(1,idGrupos.get(contador));
				ResultSet rs = stmt.executeQuery();
				retorno.add(rs.getString(1));
				rs.close();
			}
			stmt.close();
			return retorno;
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
public Set<String> usuariosDoGrupo(int idGrupo){
		
		String sql2 = "select idusuario from participa where idgrupo=?";
		String sql = "select nome from usuarios where idusuario=?";
			
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
			
			stmt2.setInt(1,idGrupo);
			
			//rs2 vai receber todos IDs dos usuarios que estão no grupo
			ResultSet rs2 = stmt2.executeQuery();
			ArrayList<Integer> idUsuarios = new ArrayList<Integer>();
			while(rs2.next()){
				idUsuarios.add(rs2.getInt(1));
			}
			rs2.close();
			stmt2.close();
			//retorno será Set que irá receber os nomes dos usuarios
			Set<String> retorno = new HashSet<String>();			
			for(int contador = 0;contador<idUsuarios.size();contador++){
				//passa para STMT os IDs dos usuarios para poder então pegar o nome do usuario
				stmt.setInt(1,idUsuarios.get(contador));
				//rs recebe então o nome do usuario com ID pego no grupo
				ResultSet rs = stmt.executeQuery();
				// coloca em retorno o nome do Usuario 
				retorno.add(rs.getString(1));
				rs.close();
			}
			stmt.close();
			return retorno;
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	
	
}
