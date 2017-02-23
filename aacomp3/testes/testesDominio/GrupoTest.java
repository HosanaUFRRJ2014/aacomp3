package testesDominio;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import dominio.Grupo;
import dominio.Usuario;
import excecoes.JaExisteException;
import junit.framework.TestCase;
import projetoDAO.GrupoDAO;

public class GrupoTest extends TestCase{

     

	/**
	 * @throws Exception 
	 * @throws ClassNotFoundException ******************************************/

	@Test
	public void testArmazenar() throws ClassNotFoundException, Exception {
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","thi802139");
		String nome = "Nome do Grupo de Teste"; 
		String descricao = "Descricao de Teste"; 
		String regras = "Regras de teste";
		int limite = 6;
		
		Grupo grupo = new Grupo(null,nome, descricao,regras,limite);
		grupo.armazenar();
		
		GrupoDAO grupodao = new GrupoDAO(); 
		
        String sql = "select count(*) from grupos where nome=" + "\'" + nome + "\'";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);			
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
			
			stmt.execute();
			
			assertEquals(retorno.size(),1);
			
		//	stmt.executeUpdate("delete * from grupos where nome=" + "\'" + nome + "\'"); //apagar dado extra
			
			rs.close();
			
			stmt.close();
			
			
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
		
	}

	@Test
	public void testAlterar() throws SQLException, ClassNotFoundException {
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","thi802139");
		
		String nome = "Nome do Grupo de Teste"; 
		String descricao = "Descricao de Teste"; 
		String regras = "Regras de teste";
		int limite = 6;
		
		
		String novoNome = "Nome do Grupo de Teste"; 
		String novaDescricao = "Descricao de Teste"; 
		int novoLimite = 7;
		
		Grupo grupo = new Grupo(null,nome, descricao,regras,limite);
		grupo.alterar(novoNome, novaDescricao, novoLimite);
		GrupoDAO grupodao = new GrupoDAO(); 
		
        String sql = "select count(*) from grupos where nome=" + "\'" + novoNome + "\'";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);			
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
			
			stmt.execute();
			
			assertEquals(retorno.size(),1);
			
	//		stmt.executeUpdate("delete * from grupos where nome=" + "\'" + nome + "\'"); //apagar dado extra
			
			rs.close();
			
			stmt.close();
			
			
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}

	@Test
	public void testRecuperaID() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","thi802139");
		
		String nome = "Nome do Grupo de Teste"; 
		String descricao = "Descricao de Teste"; 
		String regras = "Regras de teste";
		int limite = 6;
		
		
		String novoNome = "Nome do Grupo de Teste 2"; 
		String novaDescricao = "Descricao de Teste 2"; 
		int novoLimite = 7;
		
		Grupo grupo = new Grupo(null,nome, descricao,regras,limite);
		grupo.alterar(novoNome, novaDescricao, novoLimite);
		GrupoDAO grupodao = new GrupoDAO(); 
		
        String sql = "select idgrupo from grupos where nome=" +nome + "and" + "descricao=" + descricao;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);			
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
			
			stmt.execute();
			
			assertEquals(retorno.size(),1);
			
	//		stmt.executeUpdate("delete * from grupos where nome=" + "\'" + nome + "\'"); //apagar dado extra
			
			rs.close();
			
			stmt.close();
			
			
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}
	

	@Test
	public void testAdicionarUsuario() throws Exception {
        
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperaUsuarios() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","thi802139");
		
		String nome = "Nome do Grupo de Teste"; 
		String descricao = "Descricao de Teste"; 
		String regras = "Regras de teste";
		int limite = 6;
		
		int id = 1;
		
	//	Usuario novoUsuario = new Usuario("Nome usuário","user@user","22559892");
		
		Grupo grupo = new Grupo(null,nome, descricao,regras,limite);
		grupo.recuperaUsuarios(id);
		GrupoDAO grupodao = new GrupoDAO(); 
		
        String sql = "select emailusuario from participa where idgrupo=" + id;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);			
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
			
			stmt.execute();
			
			assertNotNull(retorno.size());
			
	//		stmt.executeUpdate("delete * from grupos where nome=" + "\'" + nome + "\'"); //apagar dado extra
			
			rs.close();
			
			stmt.close();
			
			
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}		
	}

	@Test
	public void testRecuperaGrupo() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testGetId() {
		
		Grupo grupo = new Grupo();
		grupo.setId(8);
		
		assertEquals(grupo.getId(),8);
	}


	
	/********************************************/

	@Test
	public void testGetNome() {
		Grupo grupo = new Grupo();
		grupo.setNome("MeuGrupo");
		
		assertEquals(grupo.getNome(),"MeuGrupo");
	}

	@Test
	public void testSetNome() {
		Grupo grupo = new Grupo();
		grupo.setNome("MeuGrupo");
		
		assertNotNull(grupo.getNome());
	}


	@Test
	public void testIsAtivo() {
		Grupo grupo = new Grupo();
		
		
		assertTrue(grupo.isAtivo());
	}

	@Test
	public void testGetUsuarios() {
		Grupo grupo = new Grupo();
		grupo.setNome("MeuGrupo");
		
		assertNotNull(grupo.getUsuarios());
	}

}
