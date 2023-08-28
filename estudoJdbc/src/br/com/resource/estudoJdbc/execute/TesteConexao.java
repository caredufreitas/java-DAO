package br.com.resource.estudoJdbc.execute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class TesteConexao {

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Teste", "postgres",
				"admin")) {
			// System.out.println("Conectado com sucesso! ");
			PreparedStatement pstm2 = conn.prepareStatement("INSERT INTO usuario (nome, senha, data_cadastro) "
															+ "VALUES (?, ?, ?)");//numero de colunas value ?-1, ?-2
			pstm2.setString(1, "Fabiao");
			pstm2.setString(2, "1234");
			pstm2.setObject(3, LocalDate.now());//somente o dia
			pstm2.execute();
			
			PreparedStatement pstm = conn.prepareStatement("SELECT id, nome, senha, data_cadastro FROM usuario");
			ResultSet rs = pstm.executeQuery();//traz todos os dados
			
			while (rs.next()) {
				System.out.println("CODIGO - " + rs.getInt("id") + 
									"	NOME - " + rs.getString("nome") + 
									"	SENHA - "+ rs.getString("senha") + 
									"	DATA - " + rs.getDate("data_cadastro").toLocalDate()+
									"\n");
			}
		} catch (Exception e) {
			System.err.println("Erro ao conectar. " + e.getMessage());
		}
	}

}
