package projetoTDG;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class CaronaTDG {
	
	private Connection conexao;
	
	public CaronaTDG() throws ClassNotFoundException{
		
		this.conexao = new ConnectionFactory().getConnection();
		
	}
	
	public void adicionarCarona(String emailMotorista,int idGrupo, Date dia, Time horarioSaida, int idLogradouroOrigem, int idLogradouroDestino,int vagas, int idVeiculo) throws ClassNotFoundException{
		
		String sql = "insert into caronas" + 
					"(emailmotorista,idgrupo,dia,saida,idlogradouroorigem,idlogradourodestino,vagasrest,idveiculo,cancelado)"+
					"values(?,?,?,?,?,?,?,?,false)";
		try{
			//pegar a quantidade de vagas atual da carona
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setString(1, emailMotorista);
			stmt.setInt(2, idGrupo);
			stmt.setDate(3, dia);
			stmt.setTime(4, horarioSaida);
			stmt.setInt(5,idLogradouroOrigem);
			stmt.setInt(6, idLogradouroDestino);
			stmt.setInt(7, vagas);
			stmt.setInt(8, idVeiculo);
			
					
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
