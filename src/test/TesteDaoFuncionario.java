package test;

import static org.junit.Assert.*;

import java.awt.List;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import main.dao.DaoFuncionario;
import main.entity.Funcionario;
import main.repository.CNXJDBC;

@TestMethodOrder(OrderAnnotation.class)
public class TesteDaoFuncionario {

	@Test
	@Order(1)
	@DisplayName("Teste Conexão BD")
	public void testeConexaoBd() {

		Connection resultado = new CNXJDBC().conexaoBanco();
		
		assertNotNull(resultado);				
	}
	
	@Test
	@Order(2)
	@DisplayName("Teste Verificar Funcionário Cpf")
	public void testeVerificarFuncionario() {
		
		boolean resultado = new DaoFuncionario().verificarFuncionario("490.736.100-95");
		
		assertFalse(resultado);		
	}
	
	@Test
	@Order(3)
	@DisplayName("Teste Inserir Funcionário")
	public void testeInsertFuncionario() {
		
		LocalDate localdate = LocalDate.parse("2001-11-23");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Pedro Ernesto Alves");
		funcionario.setCpf("490.736.100-95");
		funcionario.setEmail("pedro@gmail.com");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(1000.98f);
		funcionario.setData_nascimento(localdate);
		
		/*if(!new DaoFuncionario().verificarFuncionario(funcionario.getCpf())) {
			boolean resultado = new DaoFuncionario().inserirFuncionario(funcionario);
			
			assertTrue(resultado);	
		}
		*/
		
		boolean resultado = new DaoFuncionario().inserirFuncionario(funcionario);
		
		assertTrue(resultado);	
			
	}
	
	@Test
	@Order(4)
	@DisplayName("Teste Editar Funcionário")
	public void testeEditarFuncionario() {
		
		LocalDate localdate = LocalDate.parse("2001-11-23");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Pedro Alves Louback");
		funcionario.setCpf("490.736.100-95");
		funcionario.setEmail("pedrog@gmail.com");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(1000.98f);
		funcionario.setData_nascimento(localdate);
		
		boolean resultado = new DaoFuncionario().alterarFuncionario(funcionario);
		
		assertTrue(resultado);	
			
	}
	
	@Test
	@Order(5)
	@DisplayName("Teste Listar Funcionário")
	public void testeListarFuncionario() {
		

		ArrayList<Funcionario> lista = new DaoFuncionario().listarFuncionario();
			
		assertNotEquals(0, lista.size());
	}

	@Test
	@Order(6)
	@DisplayName("Teste Deletar Funcionário")
	public void testeDeletarFuncionario() {
		
		String Cpf = "490.736.100-95";
		
		boolean resultado = new DaoFuncionario().excluirFuncionario(Cpf);
		
		assertTrue(resultado);	
			
	}

}
