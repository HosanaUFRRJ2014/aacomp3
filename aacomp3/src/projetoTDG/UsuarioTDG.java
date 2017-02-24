package projetoTDG;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dominio.Usuario;
import dto.UsuarioDTO;

public class UsuarioTDG {
	
	private Connection conexao;
	
	public UsuarioTDG() throws ClassNotFoundException{
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
	
	public boolean verificaEmail(String email){
		
		String sql = "select email from usuarios where email=?";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()==false){
				rs.close();				
				stmt.close();
				
				return false;
			}else{
				
				String aux = rs.getString(1);
				
				if(aux.equals(email)){
					rs.close();
					stmt.close();
					return true;
				}
			}
			
			rs.close();
			stmt.close();
			
			return false;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
				
		
	}
	
	public UsuarioDTO recuperaPorEmail(String email){
		
		String sql = "select * from usuarios where email=?";
		try{			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();			
				
			UsuarioDTO retorno = new UsuarioDTO();
			
			//retorno[0] = nome
			retorno.setNome(rs.getString(1));
			
			//retorno[1] = email
			retorno.setEmail(rs.getString(2));
			
			//retorno[2] = telefone
			retorno.setTelefone(rs.getString(3));
			
			
			rs.close();
			stmt.close();
			
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	

}
