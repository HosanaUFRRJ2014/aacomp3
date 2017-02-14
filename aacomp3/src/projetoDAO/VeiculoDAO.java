package projetoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
	
	private Connection conexao;
	
	public VeiculoDAO() throws ClassNotFoundException{
		
		this.conexao = new ConnectionFactory().getConnection();
		
	}
	
	public void adicionaVeiculo(String emailUsuario, String placa, String cor, String modelo,int vagas) throws ClassNotFoundException{
		
		String sql = "insert into veiculos" + "(emailusuario,placa,cor,modelo,vagas)" + "values(?,?,?,?,?)";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,emailUsuario);
			stmt.setString(2, placa);
			stmt.setString(3,cor);
			stmt.setString(4, modelo);
			stmt.setInt(5, vagas);
			
			stmt.execute();
			stmt.close();
			
			UsuarioDAO usu = new UsuarioDAO();
			usu.mudaMotorista(emailUsuario);
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}	
	}
	
	public int recuperaID(String modelo,String placa){
		String sql = "select idveiculo from veiculos where (placa,modelo)=(?,?)";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,placa);
			stmt.setString(2, modelo);			
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			int retorno = rs.getInt(1);
			
			stmt.close();	
			rs.close();
			
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}	
	}
	
	public String buscaDono(String email){
		
		String sql = "select emailusuario from veiculos where idveiculo=?";
		String sql2 = "select nome from usuarios where email=?";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
			//coloca em stmt2 o ID do usuario dono do carro
			stmt2.setInt(1,rs.getInt(1));
			rs.close();
			stmt.close();
			
			//rs2 recebe o Nome do usuario com o ID no carro
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();
			//retorno ser� o nome do usuario
			String retorno = rs2.getString(1);
			
			return retorno;
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public ArrayList<String> buscaInformacoes(int ID){
		
		String sql = "select * from veiculos where idveiculo=?";		
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			// cria Lista que ir� conter todas informa��es do carro
			ArrayList<String> retorno = new ArrayList<String>();
			
			//adiciona na Lista as informacoes do carro:, retorno[1] = emailDono...
			// retorno[0] = id
			retorno.add(String.valueOf(rs.getInt(1)));
			//retorno[1] = emailDono
			retorno.add(rs.getString(2));
			//retorno[2] = placa
			retorno.add(rs.getString(3));
			//retorno[3] = cor
			retorno.add(rs.getString(4));
			//retorno[4] = modelo
			retorno.add(rs.getString(5));	
			//retorno[5] = vagas
			retorno.add(String.valueOf(rs.getInt(6)));
			
			rs.close();
			stmt.close();
			
			return retorno;
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	
	public int quantVagas(int ID){
		
		String sql = "select vagas from veiculos where idveiculo=?";
				
		try{
					
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			// retorno ir� receber a quantidade de vagas do carro
			int retorno = rs.getInt(1);
					
			rs.close();
			stmt.close();
					
			return retorno;
					
					
		}catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	
	public ArrayList<String> veiculosDeUmDono(String emailDono){
		
		String sql = "select modelo from veiculos where emailusuario=?";
		String sql2= "select placa from veiculos where emailusuario=?";
		
		try{
					
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			PreparedStatement stmt2 = this.conexao.prepareStatement(sql2);
			
			stmt.setString(1,emailDono);
			stmt2.setString(1, emailDono);
			
			ResultSet rs = stmt.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();
			
			ArrayList<String> retorno = new ArrayList<String>();
			
			while(rs.next() && rs2.next()){
				
				retorno.add(rs.getString(1));
				retorno.add(rs2.getString(1));
				
			}
					
			rs.close();
			stmt.close();
					
			return retorno;
					
					
		}catch(SQLException e){
			throw new RuntimeException(e);
		}		
		
	}
	
	//muda cor do veiculo dado o ID do veiculo
	public void mudaCor(int ID, String novaCor){
		
		String sql = "update veiculos set cor=? where idveiculo=?";
		
		try{
					
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,novaCor);
			stmt.setInt(2, ID);
			
					
			stmt.execute();
			stmt.close();			
					
		}catch(SQLException e){
			throw new RuntimeException(e);
		}		
		
	}


}
