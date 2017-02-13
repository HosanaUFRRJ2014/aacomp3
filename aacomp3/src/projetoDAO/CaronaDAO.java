package projetoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class CaronaDAO {
	
	private Connection conexao;
	
	public CaronaDAO() throws ClassNotFoundException{
		
		this.conexao = new ConnectionFactory().getConnection();
		
	}
	
	public void adicionarCarona(String emailMotorista, Date dia, Time horarioSaida, int idParadaOrigem, int idParadaDestino, int idVeiculo) throws ClassNotFoundException{
		
		String sql = "insert into caronas" + 
					"(idusuario,dia,saida,idparadaorigem,idparadadestino,vagasrest,idveiculo,cancelado)"+
					"values(?,?,?,?,?,?,?,false)";
		try{
			//pegar a quantidade de vagas atual da carona
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setString(1,emailMotorista);
			stmt.setDate(2, dia);
			stmt.setTime(3, horarioSaida);
			stmt.setInt(4, idParadaOrigem);
			stmt.setInt(5, idParadaDestino);
			
			VeiculoDAO veiculo = new VeiculoDAO();
			
			stmt.setInt(6, veiculo.quantVagas(idVeiculo));
			stmt.setInt(7, idVeiculo);
					
			stmt.execute();
			stmt.close();
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	public void mudarVagas(int ID,int vagas){
		
		String sql = "update caronas set vagas=? where idcarona=?";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setInt(1,vagas);
			stmt.setInt(2,ID);
			
			stmt.close();
		
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	

}
