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
	
	public void adicionaVeiculo(String emailDono, String placa, String cor, String modelo) throws ClassNotFoundException{
		
		String sql = "insert into veiculos" + "(idusuario,placa,cor,modelo)" + "values(?,?,?,?)";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,emailDono);
			stmt.setString(2, placa);
			stmt.setString(3,cor);
			stmt.setString(4, modelo);
			
			stmt.execute();
			stmt.close();
			
			UsuarioDAO usu = new UsuarioDAO();
			usu.mudaMotorista(emailDono);
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}	
	}
	
	public String buscaDono(int ID){
		
		String sql = "select idusuario from veiculos where idveiculo=?";
		String sql2 = "select nome from usuarios where idusuario=?";
		
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
	
	public List<String> buscaInformacoes(int ID){
		
		String sql = "select * from veiculos where idveiculo=?";		
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			// cria Lista que ir� conter todas informa��es do carro
			List<String> retorno = new ArrayList<String>();
			
			//adiciona na Lista as informa��o da coluna 3(placa), 4(cor), 5(modelo)
			retorno.add(rs.getString(3));
			retorno.add(rs.getString(4));
			retorno.add(rs.getString(5));	
			
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


}
