package projetoDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import dominio.Usuario;

public class UsuarioDAO {
	
	private Connection conexao;
	
	public UsuarioDAO() throws ClassNotFoundException{
		this.conexao = new ConnectionFactory().getConnection();
	}
	
public void adicionaUsuario(String nome, String email,String telefone){
		
		String sql = "insert into usuarios " + "(nome,email,telefone,motorista)" + "values (?,?,?,?)";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setString(1,nome);
			stmt.setString(2,email);
			stmt.setString(3,telefone);
			stmt.setBoolean(4,false);
			
			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	
	public String procuraNome(int ID){
		
		String sql = "select nome from usuarios where idgrupo=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			
			ResultSet rs = stmt.executeQuery();		
			rs.next();
			String retorno = rs.getString(1);
			
			rs.close();
			stmt.close();
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	public String procuraEmail(int ID){
		
		String sql = "select email from usuarios where idgrupo=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			
			ResultSet rs = stmt.executeQuery();		
			rs.next();
			String retorno = rs.getString(1);
			
			rs.close();
			stmt.close();
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	public String procuraTelefone(int ID){
		
		String sql = "select telefone from usuarios where idgrupo=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			
			ResultSet rs = stmt.executeQuery();		
			rs.next();
			String retorno = rs.getString(1);
			
			rs.close();
			stmt.close();
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	public boolean procuraMotorista(int ID){
		
		String sql = "select motorista from usuarios where idgrupo=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			
			ResultSet rs = stmt.executeQuery();		
			rs.next();
			boolean retorno = rs.getBoolean(1);
			
			rs.close();
			stmt.close();
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
			
	}
	
	public void mudaNome(int ID, String novoNome){
		
		String sql = "update usuarios set nome=? where idusuario=?";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,novoNome);
			stmt.setInt(2,ID);
			
			stmt.execute();
			stmt.close();	
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void mudaTelefone(int ID,String novoTelefone){
		
		String sql = "update usuarios set telefone=? where idusuario=?";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, novoTelefone);
			stmt.setInt(2,ID);
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Set<Usuario> buscaEhMotorista(){
		
		String sql = "select * from usuario where motorista=true";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			Set<Usuario> retorno = new HashSet<Usuario>();
			
			while(rs.next()){
				
				Usuario usuario = new Usuario(rs.getString(1),rs.getString(2),rs.getString(3));
				retorno.add(usuario);
				
			}
			
			rs.close();
			stmt.close();
			return retorno;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public Set<Usuario> buscaNaoMotorista(){
		
		String sql = "select * from usuarios where motorista=false";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			Set<Usuario> retorno = new HashSet<Usuario>();
			
			while(rs.next()){
				
				Usuario usuario = new Usuario(rs.getString(2),rs.getString(3),rs.getString(4));
				retorno.add(usuario);
				
			}
			rs.close();
			stmt.close();
			return retorno;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	

}
