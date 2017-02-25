package projetoTDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ParadaDTO;

public class ParadaTDG {

	private Connection conexao;

	public ParadaTDG() throws ClassNotFoundException{

		this.conexao = new ConnectionFactory().getConnection();
	}

	//usuario adiciona 
	public void adicionarParada(String emailUsuario, int idLogradouro, int idCarona) throws ClassNotFoundException{
		//esse será um método bem complicado de se fazer... 

		// idUsuario neste caso faz referencia ao Caroneiro

		String sql = "insert into parada" + "(emailusuario,idlogradouro,idveiculo)" + "values(?,?,?)";

		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			

			stmt.setString(1, emailUsuario);
			stmt.setInt(2, idLogradouro);
			stmt.setInt(3, idCarona);					

			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}

	public int recuperaID(String emailUsuario,int idLogradouro){

		String sql = "select idparada from parada where (emailusuario,idlogradouro)=(?,?)";

		try {		

			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, emailUsuario);
			stmt.setInt(2, idLogradouro);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			int retorno = rs.getInt(1);

			rs.close();
			stmt.close();

			return retorno;

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//não entendo pq tenho que por isso... tem algo esquisito 
		return 0;
		
	}

	public ParadaDTO recuperadaParada(int ID){

		String sql = "select * from parada where idparada=?";

		try{

			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setInt(1, ID);

			ResultSet rs = stmt.executeQuery();
			ParadaDTO retorno = new ParadaDTO();
			rs.next();		


			retorno.setId(rs.getInt(1));
			retorno.setEmailUsuario(rs.getString(2));
			retorno.setIdLogradouro(rs.getInt(3));
			retorno.setIdCarona(rs.getInt(4));
			retorno.setTipo(rs.getString(5));			

			rs.close();
			stmt.close();

			return retorno;
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}

	public ArrayList<ParadaDTO> paradasDeUmaCarona(int idCarona){

		String sql = "select * from parada where idcarona=?";

		try{

			PreparedStatement stmt = this.conexao.prepareStatement(sql);			

			stmt.setInt(1, idCarona);

			ResultSet rs = stmt.executeQuery();
			ArrayList<ParadaDTO> retorno = new ArrayList<ParadaDTO>();

			while(rs.next()){
				ParadaDTO parada = new ParadaDTO();
				parada.setId(rs.getInt(1));
				parada.setEmailUsuario(rs.getString(2));
				parada.setIdLogradouro(rs.getInt(3));
				parada.setIdCarona(rs.getInt(4));
				parada.setTipo(rs.getString(5));

				retorno.add(parada);
			}
			rs.close();
			stmt.close();

			return retorno;
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		


	}

}
