package testesDominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.*;

public class MotoristaTest {

	@Test
	public void testAddVeiculo() {
		
		
		Veiculo ve = new Veiculo("Ferrari","hgp-555","vermelha",4);
		Motorista m = new Motorista();
		
		m.addVeiculo(ve);
		
		assertEquals(m.getVeiculos().size(),1);
		assertEquals(ve,m.getVeiculos().get(0));
		
	}

	@Test
	public void testCriarCarona() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterarVeiculoCarona() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancelarCarona() {
		fail("Not yet implemented");
	}


}
