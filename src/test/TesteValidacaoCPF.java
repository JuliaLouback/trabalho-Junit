package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.util.ValidacaoCPF;

public class TesteValidacaoCPF {

	@Test
	@Order(1)
	@DisplayName("Teste Validação CPF")
	public void testeValidacaoCPF() {
		
		boolean resultado = new ValidacaoCPF().validaCpf("973.273.480-93");
		
		assertTrue(resultado);
		
	}

}
