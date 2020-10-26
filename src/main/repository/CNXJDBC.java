package main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CNXJDBC {
	
	public Connection conexaoBanco() {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=bd_funcionario;";
		
		try {
			return DriverManager.getConnection(connectionUrl,"sa", "");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
