package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;

import static org.junit.Assert.*;


import main.util.ValidacaoCPF;

@TestMethodOrder(OrderAnnotation.class)
public class TesteValidacaoCPF {

	@Test
	@Order(1)
	@Tag("success")
	@DisplayName("Teste Valida CPF")
	void testeValidacaoCPF() {
		
		boolean resultado = new ValidacaoCPF().validaCpf("973.273.480-93");
		
		assertTrue(resultado);
		
	}
	
	@Test
	@Order(2)
	@Tag("success")
	@DisplayName("Teste Valida CPF Inválido")
	void testeValidacaoCPFInvalido() {
		
		boolean resultado = new ValidacaoCPF().validaCpf("111.111.111-11");
		
		assertEquals(false,resultado);
		
	}
	
	@Test
	@Order(3)
	@Tag("failure")
	@DisplayName("Teste Valida CPF Válido Falha")
	void testeValidacaoFalhaAcertoCPF() {
		
		boolean resultado = new ValidacaoCPF().validaCpf("490.736.100-95");
		
		assertEquals(false,resultado);
		
	}
	
	@Test
	@Order(4)
	@Tag("failure")
	@DisplayName("Teste Valida CPF Inválido Falha")
	void testeValidacaoErroCPF() {
		
		boolean resultado = new ValidacaoCPF().validaCpf("111.111.111-11");
		
		assertEquals(true,resultado);
		
	}

}
