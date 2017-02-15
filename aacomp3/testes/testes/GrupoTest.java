package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.Grupo;
import junit.framework.TestCase;

public class GrupoTest extends TestCase{

     

	/********************************************/

	@Test
	public void testArmazenar() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterar() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperaID() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionarUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperaUsuarios() {
		fail("Not yet implemented");
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

	@Test
	public void testSetId() {
		fail("Not yet implemented");
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
	public void testGetDescricao() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDescricao() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLimMinAvaliacoesRuins() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLimMinAvaliacoesRuins() {
		fail("Not yet implemented");
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

	@Test
	public void testSetUsuarios() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRegras() {
		fail("Not yet implemented");
	}

	@Test
	public void testDesativar() {
		fail("Not yet implemented");
	}

}
