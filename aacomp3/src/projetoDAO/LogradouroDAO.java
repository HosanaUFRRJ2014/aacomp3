package projetoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogradouroDAO {	
	
	private Connection conexao;
	
	public LogradouroDAO() throws ClassNotFoundException{
		
		this.conexao = new ConnectionFactory().getConnection();
		
	}
	
	
	public void adicionaLogradouro(String cep, int numero, String estado, String cidade, String distrito, String endereco ){
		
			String sql = "insert into logradouro" + 
					"(cep,numero,estado,cidade,distrito,endereco)"+
					"values(?,?,?,?,?,?)";
		try{
			//pegar a quantidade de vagas atual da carona
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			
			stmt.setString(1, cep);
			stmt.setInt(2, numero);
			stmt.setString(3, estado);
			stmt.setString(4, cidade);
			stmt.setString(5, distrito);
			stmt.setString(6, endereco);
					
			stmt.execute();
			stmt.close();
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
			
	}
	
	// verifica se há logradouro com mesmo CEP e número no banco para evitar redundancia de logradouros
	public boolean verificaLogradouro(String cep,int numero){
		
		String sql = "select cep from logradouro where cep=?";
		String sql2 = "select numero from logradouro where numero=?";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
			
			stmt.setString(1,cep);
			stmt2.setInt(1, numero);
			
			ResultSet rs = stmt.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();
			
			if(rs.next()==false || rs2.next()==false){				
				return false;
				
			}else{				
				
				String aux = rs.getString(1);
				int aux2 = rs2.getInt(1);
				
				if(aux.equals(cep) && aux2==numero){
							
					return true;
				}
			}
			
			rs.close();
			rs2.close();
			stmt.close();
			stmt2.close();
			
			return false;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
				
	}
	
	public int recuperaID(String cep, int numero){
		String sql = "select idlogradouro from logradouro where (cep,numero)=(?,?)";
	try{
		//pegar a quantidade de vagas atual da carona
		PreparedStatement stmt = this.conexao.prepareStatement(sql);			
		
		stmt.setString(1, cep);
		stmt.setInt(2, numero);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		int retorno = rs.getInt(1);
				
		stmt.execute();
		stmt.close();
		
		return retorno;
		
	}catch(SQLException e){
		throw new RuntimeException(e);
	}
	}
	
}
