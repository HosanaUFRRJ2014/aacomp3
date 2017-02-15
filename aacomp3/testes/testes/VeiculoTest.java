package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dominio.Carona;
import dominio.Veiculo;

public class VeiculoTest {

	@Test
	public void testVeiculosDeUmDono() {
		fail("Not yet implemented");
	}

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
	public void testRecuperaCarro() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperaInfo() {
		fail("Not yet implemented");
	}
	/*********************************************/

	@Test
	public void testGetCor() {
		Veiculo v = new Veiculo("W Motors","hgp-5555","branco",4);
		assertEquals(v.getCor(),"branco");
	}

	@Test
	public void testSetCor() {
		Veiculo v = new Veiculo("W Motors","hgp-5555","branco",4);
		v.setCor("vermelho");
		assertEquals(v.getCor(),"vermelho");
	}

	@Test
	public void testGetModelo() {
		Veiculo v = new Veiculo("W Motors","hgp-5555","branco",4);
		assertEquals(v.getModelo(),"W Motors");
	}

	@Test
	public void testGetPlaca() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumeroVagas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMotorista() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCarona() {
		Veiculo v = new Veiculo("W Motors","hgp-5555","branco",4);
		v.addCarona(new Carona(v, null, null, null, null));
		assertEquals(v.getCaronas().size(),1);
	}

	@Test
	public void testGetCaronas() {
		Veiculo v = new Veiculo("W Motors","hgp-5555","branco",4);
		ArrayList<Carona> carona = new ArrayList<Carona>();
		carona = v.getCaronas();
		assertNotNull(v.getCaronas());
		assertEquals(v.getCaronas().size(),carona.size());
	}

	@Test
	public void testSetCaronas() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMotorista() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}

}
