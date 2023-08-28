package br.com.resource.desafioFinalDao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	public static Connection getConexao() {
		Connection con = null;
		final String wan = "jdbc:mysql//localhost:3306/Teste";
		final String user = "root";
		final String pass = "root";
		try {
			con = DriverManager.getConnection(wan, user, pass);
			System.out.println("Conexao estabelecida !");
		} catch (Exception e) {
			System.err.println("Erro ao conectar "+ e.getMessage());
		}
		return con;
	}
}
