package projetoTDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.VeiculoDTO;

public class VeiculoTDG {
	
	private Connection conexao;
	
	public VeiculoTDG() throws ClassNotFoundException{
		
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
			
			UsuarioTDG usu = new UsuarioTDG();
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
	
	public VeiculoDTO buscaInformacoes(int ID){
		
		String sql = "select * from veiculos where idveiculo=?";		
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			VeiculoDTO retorno = new VeiculoDTO();
			
			
			// retorno[0] = id			
			retorno.setId(rs.getInt(1));
			//retorno[1] = emailDono			
			retorno.setMotorista(rs.getString(2));
			//retorno[2] = placa			
			retorno.setPlaca(rs.getString(3));
			//retorno[3] = cor
			retorno.setCor(rs.getString(4));
			//retorno[4] = modelo			
			retorno.setModelo(rs.getString(5));
			//retorno[5] = vagas			
			retorno.setNumeroVagas(rs.getInt(6));
			
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
	
	public ArrayList<VeiculoDTO> veiculosDeUmDono(String emailDono){
		
		String sql = "select * from veiculos where emailusuario=?";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,emailDono);		
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<VeiculoDTO> retorno = new ArrayList<VeiculoDTO>();
			
			
			while(rs.next()){
				VeiculoDTO mensageiro = new VeiculoDTO();
				
				mensageiro.setId(rs.getInt(1));
				mensageiro.setMotorista(rs.getString(2));
				mensageiro.setPlaca(rs.getString(3));
				mensageiro.setCor(rs.getString(4));
				mensageiro.setModelo(rs.getString(5));
				mensageiro.setNumeroVagas(rs.getInt(6));
				
				retorno.add(mensageiro);
			}
			stmt.close();			
			
			return retorno;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}		
		
	}


}
