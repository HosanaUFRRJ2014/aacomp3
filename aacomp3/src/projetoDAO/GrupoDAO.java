package projetoDAO;
import java.sql.*;
import java.util.ArrayList;

//import projetoEntidades.Grupo;


public class GrupoDAO {
	
	private Connection conexao;
	
	public GrupoDAO() throws ClassNotFoundException{
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public void adicionaGrupo(String nome, String descricao,String regras, int limite){
		
		String sql = "insert into grupos " + "(nome,descricao,regras,limite,ativo)" + "values (?,?,?,?,true)";
		
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
	
	public void alteraGrupo(int ID, String novoNome, String novaDescricao, int novoLimite){
		
		String sql = "update grupos set (nome,descricao,limite)=(?,?,?) where idgrupo=?";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setString(1,novoNome);
			stmt.setString(2,novaDescricao);
			stmt.setInt(3,novoLimite);
			stmt.setInt(4, ID);
			
			stmt.execute();
			stmt.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
		
		
		
	}
	
	public ArrayList<String> recuperaGrupo(int ID){
		
		String sql = "select * from grupos where idgrupo=?";
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1,ID);
			
			ResultSet rs = stmt.executeQuery();		
			rs.next();
			ArrayList<String> retorno = new ArrayList<String>();
			
			//retorno[0] = ID
			retorno.add(String.valueOf(rs.getInt(1)));
			
			//retorno[1] = nome
			retorno.add(rs.getString(2));
			
			//retorno[2] = descricao
			retorno.add(rs.getString(3));
			
			//retorno[3] = regras
			retorno.add(rs.getString(4));
			
			//retorno[4] = limite
			retorno.add(String.valueOf(rs.getInt(5)));
			
			//retorno[5] = ativo
			retorno.add(String.valueOf(rs.getBoolean(6)));
			
			rs.close();
			stmt.close();
			return retorno;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	public int recuperaID(String nome, String descricao){
		
		String sql = "select idgrupo from grupos where (nome,descricao)=(?,?)";
		
		try{
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,nome);
			stmt.setString(2,descricao);
			
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
	
	//se retornar false, não crie o grupo pois já há um grupo com mesmo nome e descricao no banco
	public boolean garanteIntegridade(String nome, String descricao){
		
		String sql = "select (nome,descricao) from grupos where (nome,descricao)=(?,?)";
		
		try{
			
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1,nome);
			stmt.setString(2,descricao);
			
			ResultSet rs = stmt.executeQuery();		
			
			if(rs.next()){				
				return false;
			}
			
			rs.close();
			stmt.close();
			return true;
			
		}catch(SQLException e){
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
		
		
		
		

	
	
		
			
	
		
	}
	
