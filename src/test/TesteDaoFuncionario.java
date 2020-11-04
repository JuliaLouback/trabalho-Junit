package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;

import main.dao.DaoFuncionario;
import main.entity.Funcionario;
import main.repository.CNXJDBC;
import main.util.ValidacaoCPF;

@TestMethodOrder(OrderAnnotation.class)
public class TesteDaoFuncionario {
	
	@Test
	@Order(1)
	@Tag("success")
	@DisplayName("Teste Conexão BD")
	@BeforeClass
	void testeConexaoBd() {

		Connection resultado = new CNXJDBC().conexaoBanco();
		
		assertNotNull(resultado);				
	}
	
	
	@Test
	@Order(2)
	@Tag("success")
	@DisplayName("Teste Verificar Funcionário Cpf")
	void testeVerificarFuncionario() {
		
		String Cpf = "490.736.100-95";
		
		boolean resultado = false;
		
		if(new ValidacaoCPF().validaCpf(Cpf)) {
			resultado = new DaoFuncionario().verificarFuncionario(Cpf);
		}
		 
		assertFalse(resultado);		
	}
	
	
	@Test
	@Order(3)
	@Tag("success")
	@Tag("failure")
	@DisplayName("Teste Inserir Funcionário")
	void testeInsertFuncionario() {
		
		LocalDate localdate = LocalDate.parse("2001-11-23");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Pedro Ernesto Alves");
		funcionario.setCpf("490.736.100-95");
		funcionario.setEmail("pedro@gmail.com");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(1000.98f);
		funcionario.setData_nascimento(localdate);
		
		if(!new DaoFuncionario().verificarFuncionario(funcionario.getCpf())) {
			boolean resultado = new DaoFuncionario().inserirFuncionario(funcionario);
			
			assertTrue(resultado);	
		}	
	}
	
	
	@Test
	@Order(4)
	@Tag("success")
	@DisplayName("Teste Editar Funcionário")
	void testeEditarFuncionario() {
		
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
	@Tag("success")
	@DisplayName("Teste Lista Não Vazia")
	void testeListarFuncionario() {
		
		ArrayList<Funcionario> lista = new DaoFuncionario().listarFuncionario();
			
		assertNotEquals(0, lista.size());
	}
	
	
	@Test
	@Order(6)
	@Tag("success")
	@DisplayName("Teste Listar Funcionário")
	void testeListarFuncionarioCpf() {
		
		String Cpf = "490.736.100-95";

		ArrayList<Funcionario> arrayLista = new DaoFuncionario().listarFuncionario();
		
		boolean funcionarioExiste = arrayLista.stream().anyMatch(funcionario -> Cpf.equals(funcionario.getCpf()));
	
		assertTrue(funcionarioExiste);
	}

	
	@Test
	@Order(7)
	@Tag("success")
	@DisplayName("Teste Verificar Funcionário Cpf Cadastrado")
	void testeVerificarFuncionarioJaCadastrado() {
		
		String Cpf = "490.736.100-95";
		
		boolean resultado = false;
		
		if(new ValidacaoCPF().validaCpf(Cpf)) {
			resultado = new DaoFuncionario().verificarFuncionario(Cpf);
		}
		 
		assertEquals(true, resultado);		
	}

	
	
	@Test
	@Order(8)
	@Tag("success")
	@DisplayName("Teste Editar Funcionário CPF Errado")
	void testeEditarFuncionarioCpfErrado() {
		
		LocalDate localdate = LocalDate.parse("2001-11-23");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Pedro Ernesto Alves");
		funcionario.setCpf("490.736.100-99");
		funcionario.setEmail("pedro@gmail.com");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(1000.98f);
		funcionario.setData_nascimento(localdate);
		
		assertFalse(new DaoFuncionario().alterarFuncionario(funcionario));	
	}

	
	@Test
	@Order(9)
	@Tag("success")
	@DisplayName("Teste Deletar Funcionário CPF Errado")
	void testeDeletarFuncionarioCpfErrado() {
		
		String Cpf = "490.736.100-99";
		
		boolean resultado = new DaoFuncionario().excluirFuncionario(Cpf);
		
		assertFalse(resultado);	
	}
	
	
	@Test
	@Order(10)
	@Tag("failure")
	@DisplayName("Teste Verificar Funcionário Cpf Cadastrado Falha")
	void testeVerificarFuncionarioErrado() {
		
		String Cpf = "490.736.100-95";
		
		boolean resultado = false;
		
		if(new ValidacaoCPF().validaCpf(Cpf)) {
			resultado = new DaoFuncionario().verificarFuncionario(Cpf);
		}
		 
		assertEquals(false, resultado);		
	}

	
	@Test
	@Order(11)
	@Tag("failure")
	@DisplayName("Teste Inserir Funcionário Duplicado")
	void testeInsertFuncionarioDuplicado() {
		
		LocalDate localdate = LocalDate.parse("2001-11-23");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Pedro Ernesto Alves");
		funcionario.setCpf("490.736.100-95");
		funcionario.setEmail("pedro@gmail.com");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(1000.98f);
		funcionario.setData_nascimento(localdate);
		
		assertThrows(SQLException.class, () -> { new DaoFuncionario().inserirFuncionario(funcionario);});	
	}
	
	
	@Test
	@Order(12)
	@Tag("failure")
	@DisplayName("Teste Deletar Funcionário CPF Falha")
	void testeDeletarFuncionarioCpfFalha() {
		
		String Cpf = "490.736.100-99";
		
		boolean resultado = new DaoFuncionario().excluirFuncionario(Cpf);
		
		assertTrue(resultado);	
	}
	
	
	@Test
	@Order(12)
	@Tag("failure")
	@DisplayName("Teste Editar Funcionário CPF Falha")
	void testeEditarFuncionarioCpfFalha() {
		
		LocalDate localdate = LocalDate.parse("2001-11-23");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Pedro Ernesto Alves");
		funcionario.setCpf("490.736.100-99");
		funcionario.setEmail("pedro@gmail.com");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(1000.98f);
		funcionario.setData_nascimento(localdate);
		
		assertTrue(new DaoFuncionario().alterarFuncionario(funcionario));	
	}
	
	
	@Test
	@Order(14)
	@Tag("success")
	@DisplayName("Teste Deletar Funcionário")
	void testeDeletarFuncionario() {
		
		String Cpf = "490.736.100-95";
		
		boolean resultado = new DaoFuncionario().excluirFuncionario(Cpf);
		
		assertTrue(resultado);	
	}


}

