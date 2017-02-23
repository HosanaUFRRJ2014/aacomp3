package testesDominio;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;

import org.junit.Test;

import dominio.*;


public class CaronaTest {

//	@Test
//	public void testCarona() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testArmazena() {
		fail("Not yet implemented");
	}
	/*****************************************************/


	@Test
	public void testAlterarVeiculo() {
		Veiculo v1 = new Veiculo("W Motors","hgp-5555","branco",4);
		Veiculo v2 = new Veiculo("Ferrari","hgp-5543","vermelha",4);
		Veiculo v3 = new Veiculo("Mini-Vã","hgp-5544","prata",11);
		
		Carona c = new Carona(v1, null, null, null, null);
		
		boolean valor = c.alterarVeiculo(v3);
		
		assertFalse(valor);
		
		valor = c.alterarVeiculo(v2);
		
		assertTrue(valor);
	}

	@Test
	public void testAlterar() {
		fail("Not yet implemented");
	}

}
