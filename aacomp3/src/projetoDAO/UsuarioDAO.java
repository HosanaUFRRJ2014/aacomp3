package projetoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	
	public String procuraNome(String email){
		
		String sql = "select nome from usuarios where email=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,email);
			
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
	
	
	public String procuraTelefone(String email){
		
		String sql = "select telefone from usuarios where email=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,email);
			
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
	
	//busca no banco se o usuario é motorista ou não 
	public boolean procuraMotorista(String email){
		
		String sql = "select motorista from usuarios where email=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, email);
			
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
	
	public void mudaInformacoes(String email, String novoNome, String novoTelefone){
		
		String sql = "update usuarios set (nome,telefone)=(?,?) where email=?";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,novoNome);
			stmt.setString(2, novoTelefone);
			stmt.setString(3,email);
			
			stmt.execute();
			stmt.close();	
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}	
	
	public void mudaMotorista(String email){
		
		String sql = "update usuarios set motorista=true where email=?";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,email);
			
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
	
	public boolean verificaEmail(String email){
		
		String sql = "select email from usuarios where email like ?";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()==false){
				return false;
			}
			String aux = rs.getString(1);
			if(aux.equals(email)){
				return true;
			}
			rs.close();
			stmt.close();
			
			return false;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
				
		
	}
	
	public ArrayList<String> recuperaPorEmail(String email){
		
		String sql = "select * from usuarios where email=?";
		try{			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			ArrayList<String> retorno = new ArrayList<String>();				
			
			//retorno[0] = nome
			retorno.add(rs.getString(1));
			//retorno[1] = email
			retorno.add(rs.getString(2));
			//retorno[2] = telefone
			retorno.add(rs.getString(3));
			
			Boolean motorista = rs.getBoolean(4);
			
			//retorno[3] = motorista
			retorno.add(motorista.toString());
			
			rs.close();
			stmt.close();
			
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	

}
