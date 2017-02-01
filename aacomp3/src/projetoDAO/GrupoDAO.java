package projetoDAO;
import java.sql.*;

//import projetoEntidades.Grupo;


public class GrupoDAO {
	
	private Connection conexao;
	
	public GrupoDAO() throws ClassNotFoundException{
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public void adicionaGrupo(String nome, String descricao,String regras, int limite){
		
		String sql = "insert into grupos " + "(nome,descricao,regras,limite)" + "values (?,?,?,?)";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setString(1,nome);
			stmt.setString(2,descricao);
			stmt.setString(3,regras);
			stmt.setInt(4, limite);
			
			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	
	public String procuraNome(int ID){
		
		String sql = "select nome from grupos where idgrupo=?";
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
	
	public String procuraDescricao(int ID){
		
		String sql = "select descricao from grupos where idgrupo=?";
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

	public String procuraRegras(int ID){
	
	String sql = "select regras from grupos where idgrupo=?";
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
	
	public int procuraLimite(int ID){
		
		String sql = "select limite from grupos where idgrupo=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			
			ResultSet rs = stmt.executeQuery();		
			rs.next();
			int retorno = rs.getInt(1);
			
			rs.close();
			stmt.close();
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	public boolean estaAtivo(int ID){
		
		String sql = "select ativo from grupos where idgrupo=?";
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
		
		String sql = "update grupos set nome=? where idgrupo=?";
		
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
		
		public void desativaGrupo(int ID){
			
			String sql = "update grupos set ativo=false where idgrupo=?";
			
			try{
				PreparedStatement stmt = this.conexao.prepareStatement(sql);				
				stmt.setInt(1,ID);
				
				stmt.execute();
				stmt.close();	
				
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			
		}
		
		public void ativaGrupo(int ID){
			
			String sql = "update grupos set ativo=true where idgrupo=?";
			
			try{
				PreparedStatement stmt = this.conexao.prepareStatement(sql);				
				stmt.setInt(1,ID);
				
				stmt.execute();
				stmt.close();	
				
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			
		}
		
		
		
		

	
	/*
	public Grupo procuraGrupo(int id){
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement("select from grupos where id=?");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			Grupo retorno = new Grupo(rs.getInt("idgrupo"),rs.getString("nome"),rs.getString("descricao"),rs.getString("regras"),rs.getInt("limite"));
			rs.getInt("idgrupo");
			stmt.close();
			
			}
		
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
		
		return null;
		}
	*/
		
			
	
		
	}
	
