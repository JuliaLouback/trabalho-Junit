package main.entity;

import java.sql.Date;
import java.time.LocalDate;

import javafx.scene.control.Button;

public class Funcionario {
	
	private Integer Id;
	
	private String Cpf;
	
	private String Email;
	
	private String Nome;
	
	private String Cargo;
	
	private LocalDate Data_nascimento;
	
	private float Salario;

	public String getCpf() {
		return Cpf;
	}
	
	
	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public LocalDate getData_nascimento() {
		return Data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		Data_nascimento = data_nascimento;
	}

	public float getSalario() {
		return Salario;
	}

	public void setSalario(float salario) {
		Salario = salario;
	}

	
	
}
