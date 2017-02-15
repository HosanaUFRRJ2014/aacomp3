package testesDominio;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import dominio.*;


public class UsuarioTest {

	@Test
	public void testArmazenar() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificaEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterar() {
		fail("Not yet implemented");
	}

	@Test
	public void testMontaUsuario() {
		fail("Not yet implemented");
	}

	/*********************************************************/
	@Test
	public void testAvaliar() {
		Grupo g = new Grupo();
		Usuario usuarioAvaliado = new Usuario();

		Avaliacao av = new Avaliacao(4);

		ArrayList <Avaliacao> avs = new ArrayList<Avaliacao>();
		avs.add(av);

		usuarioAvaliado.getGruposQueUsuarioEstaAtivo().add(g);

		usuarioAvaliado.getAvaliacoesPorGrupo().put(g, avs);

		ArrayList<Avaliacao> avaliacoes = usuarioAvaliado.getAvaliacoesPorGrupo().get(g);
		avaliacoes.add(av);

		assertEquals(avs.get(0).getEstrelas(),avaliacoes.get(0).getEstrelas());

	}

	@Test
	public void testCriarGrupoStringStringString() {

		Usuario u = new Usuario();

		Grupo grupo = new Grupo(u,"Grupo Teste","Descricão grupo", "Regras grupo");

		u.criarGrupo("Grupo Teste","Descricão grupo", "Regras grupo");

		assertEquals(u.getGruposQueUsuarioEstaAtivo().get(0), grupo);
	}

	@Test
	public void testCriarGrupoStringStringStringInt() {
		Usuario u = new Usuario();

		Grupo grupo = new Grupo(u,"Grupo Teste","Descricão grupo", "Regras grupo", 5);

		u.criarGrupo("Grupo Teste","Descricão grupo", "Regras grupo", 5);

		assertEquals(u.getGruposQueUsuarioEstaAtivo().get(0), grupo);
	}

	@Test
	public void testParticiparDoGrupo() {
		Usuario u = new Usuario();

		Grupo grupo = new Grupo(u,"Grupo Teste","Descricão grupo", "Regras grupo");

		u.participarDoGrupo(grupo);

		assertEquals(u.getGruposQueUsuarioEstaAtivo().size(), 1);
	}

	@Test
	public void testSairDoGrupo() {
		Usuario u = new Usuario();

		Grupo grupo = new Grupo(u,"Grupo Teste","Descricão grupo", "Regras grupo");

		u.criarGrupo("Grupo Teste","Descricão grupo", "Regras grupo");
		
		u.sairDoGrupo(grupo);

		assertEquals(u.getGruposQueUsuarioEstaAtivo().size(), 0);
	}

	@Test
	public void testConvidar() {
		fail("Not yet implemented");
	}

	@Test
	public void testVirarMotorista() {
		Usuario u = new Usuario("NomeUser","user@user.com","55555555");
		
		Veiculo veiculo = new Veiculo("W Motors","hgp-555","branco",4);
		Motorista m = u.virarMotorista(veiculo);
		

		assertEquals(u.getNome(), m.getNome());
		assertEquals(u.getEmail(), m.getEmail());
		assertEquals(u.getTelefone(), m.getTelefone());
	}

	@Test
	public void testEhMotorista() {
        Usuario u = new Usuario("NomeUser","user@user.com","55555555");
		
		Veiculo veiculo = new Veiculo("W Motors","hgp-555","branco",4);
		Motorista m = new Motorista("NomeMoto","moto@moto.com","45655555",veiculo);
		
		try {
			assertTrue(m.ehMotorista("moto@moto.com"));
			assertFalse(u.ehMotorista("user@user.com"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
