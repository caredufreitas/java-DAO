/**
 * gerar conexão com o bd
 */
package br.com.resource.conection.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Ticar
 *
 */
public class Conexao {

	private static Connection conexao;

	/* conexao ao db */
	public static Connection getConexao() {
	
		final String wan = "jdbc:postgresql://localhost:5432/Desafio";
		final String user = "postgres";
		final String pass = "admin";
		try {
			if(conexao == null) {
				conexao = DriverManager.getConnection(wan, user, pass);
			}else {
				return conexao;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return conexao;
	}

	/* fecha conexao */
	public static void fechaConexao() {
		try {
			conexao.close();
			conexao = null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}