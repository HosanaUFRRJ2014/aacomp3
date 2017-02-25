package testesDominio;

import java.sql.Connection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dominio.Grupo;
import dominio.Motorista;
import dominio.Usuario;
import dominio.Veiculo;
import junit.framework.TestCase;
import projetoTDG.UsuarioTDG;

public class UsuarioTest extends TestCase{

	private Connection conexao;


	@BeforeClass
	protected void getConnection() throws Exception {
		Class.forName("org.postgresql.Driver");
		conexao = new ConnectionFactory().getConnection();

	}


	@Test
	public void testVerificarEmailUsuario() throws Exception {
		Usuario u = new Usuario();

		boolean retorno = u.verificaEmail("hosannagomes@gmail.com");



		assertFalse(retorno);
	}

	@Test
	public void testCriarUsuario() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.addParameter("nome", "Hosana");
		request.addParameter("email", "thiago@gmail.com");
		request.addParameter("telefone", "988774455");

		Usuario u = new Usuario();

		u.criarUsuario(request, response);

		boolean sucesso = u.verificaEmail("thiago@gmail.com");

		assertTrue(sucesso);
	}

	@Test
	public void testAlterarUsuario() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.addParameter("nome", "Hosana2");
		request.addParameter("email", "thiago@gmail.com");
		request.addParameter("telefone", "988774455");

		Usuario u = new Usuario();

		u.alterarUsuario(request,response);

    	Usuario u2 = new Usuario();
		
		u2.montaUsuario("thiago@gmail.com");

		assertTrue("Hosana2",u2.getNome());
	}

	@Test
	public void testArmazenar() throws Exception {
		Usuario u = new Usuario("Usuario de Teste","teste@test.com","55555555");

		u.armazenar();

		assertTrue(u.verificaEmail("teste@test.com"));
	}

	@Test
	public void testAlterar() throws Exception {
		Usuario u = new Usuario("Usuario de Teste 2","teste@test.com","55555555");

		u.alterar("Usuario de Teste 2", "55555555");
		
		Usuario u2 = new Usuario();
		
		u2.montaUsuario("teste@test.com");
		
		assertEquals(u,u2);
		
		
	}

	@Test
	public void testParticiparDoGrupo() {
		Usuario u = new Usuario("User test", "user@gmail.test","88888888");
		Grupo grupo = new Grupo(new Usuario(), "GRupo teste","descri teste","regra test");
		u.participarDoGrupo(grupo);
		
		assertEquals(2,grupo.getUsuarios().size());
	}

	@Test
	public void testVirarMotorista() {
		Veiculo v = new Veiculo("VEiculo teste","tes-0000","testecolor",4);
		
		Usuario u = new Usuario("User test", "user@gmail.test","88888888");
		Motorista m = u.virarMotorista(v);
		
		assertNotNull(m);
		
	}

	@Test
	public void testEhMotorista() throws ClassNotFoundException {
		Usuario u = new Usuario();
		
		assertTrue(u.ehMotorista("user@gmail.test"));
		
		
		
	}



}
