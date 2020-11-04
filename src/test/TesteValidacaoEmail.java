package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import main.util.ValidacaoEmail;

@TestMethodOrder(OrderAnnotation.class)
public class TesteValidacaoEmail {

	@Test
	@Order(1)
	@Tag("success")
	@DisplayName("Teste Verificar Email Válido")
	void testeVerificarEmail() {
		
		String email = "julia@gmail.com";
		
		assertTrue(new ValidacaoEmail().emailValido(email));		
	}
	
	
	@Test
	@Order(2)
	@Tag("success")
	@DisplayName("Teste Verificar Email Inválido")
	void testeVerificarEmailInvalido() {
		
		String email = "julia";
		
		assertFalse(new ValidacaoEmail().emailValido(email));		
	}
	
	
	@Test
	@Order(3)
	@Tag("failure")
	@DisplayName("Teste Verificar Email Válido Falha")
	void testeVerificarEmailValidoFalha() {
		
		String email = "julia@gmail.com";
		
		assertFalse(new ValidacaoEmail().emailValido(email));		
	}
	
	@Test
	@Order(4)
	@Tag("failure")
	@DisplayName("Teste Verificar Email Inválido Falha")
	void testeVerificarEmailInvalidoFalha() {
		
		String email = "julia";
		
		assertTrue(new ValidacaoEmail().emailValido(email));		
	}
	

}
