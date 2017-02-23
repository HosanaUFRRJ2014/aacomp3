package testesExcecoes;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.Usuario;
import excecoes.CampoInvalidoException;

public class CampoInvalidoExceptionTest {

	@Test
	public void testCampoInvalidoException() {
		try
		{
			Usuario u = new Usuario("","fcsds","19812185");
			
			if(u.getNome().equals(""))
			   throw new CampoInvalidoException();		   
	//		fail("CampoInvalidoException deveria ter sido lançado");
	
		}
		catch(CampoInvalidoException e)
		{
			assertEquals(e.getMessage(), "Um campo inserido eh invalido!!");
		}
	}

}
