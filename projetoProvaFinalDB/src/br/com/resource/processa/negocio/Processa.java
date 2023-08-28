/**
 * operacoes dataInicio - 1, dataFim - 1, subProduto 9, quantidade - 12, idPreco - 13
 * dadosMercado idPreco - 0, numPrazoDiascorridos - 1, valorPreco - 2
 */
package br.com.resource.processa.negocio;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.com.resource.projetoProvaFinalDB.modelo.Desafio;

/**
 * @author Ticar
 *
 */
public class Processa {
	/* dados arquivos csv */
	public static void processarArquivo() throws IOException {
		long tempoInicio = System.nanoTime();
		Desafio desafio = new Desafio();
		List<String> liOperacao = Files.readAllLines(Paths.get("Operacoes.csv"));
		List<String> liMercado = Files.readAllLines(Paths.get("DadosMercado.csv"));
		Map<String, BigDecimal> resultadoMap = new HashMap<String, BigDecimal>();

		System.out.println("Inicio do processo.");
		for (String operacao : liOperacao.subList(1, liOperacao.size())) {

			String[] colOperacao = operacao.split(";");
			LocalDate dtInicio = LocalDate.parse(colOperacao[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate dtFim = LocalDate.parse(colOperacao[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			long diferencaDias = ChronoUnit.DAYS.between(dtInicio, dtFim);
			String numSubProduto = colOperacao[9];
			BigDecimal qtd = new BigDecimal(Double.parseDouble(colOperacao[12].replaceAll(",", ".")));
			int idPrecoOp = Integer.parseInt(colOperacao[13]);

			for (String mercado : liMercado.subList(1, liMercado.size())) {
				String[] colMercado = mercado.split(";");
				int idPrecoMercado = Integer.parseInt(colMercado[0]);
				int nuPrazo = Integer.parseInt(colMercado[1]);
				BigDecimal valor = new BigDecimal(Double.parseDouble(colMercado[2].replaceAll(",", ".")));

				if (idPrecoOp == idPrecoMercado && diferencaDias == nuPrazo) {
					// String subProduto = numSubProduto;
					BigDecimal resultado = valor.multiply(qtd);

					if (resultadoMap.containsKey(numSubProduto)) {
						BigDecimal soma = resultadoMap.get(numSubProduto).add(resultado);
						resultadoMap.put(numSubProduto, soma);

					} else {
						resultadoMap.put(numSubProduto, resultado);
					}

					break;
				}
			}
			for (Entry<String, BigDecimal> entry: resultadoMap.entrySet()) {
				List<String> key = new ArrayList<String>();
				key.add(entry.getKey());
				List<BigDecimal> value = new ArrayList<BigDecimal>();
				value.add(entry.getValue());
				
			}

			long decorridosTempo = System.nanoTime() - tempoInicio;
			if (System.nanoTime() - tempoInicio >= decorridosTempo) {
				long tempoProcessamento = decorridosTempo;
				desafio.setTempoProcessamento(tempoProcessamento);
			}

		}
	
		// resultadoMap.forEach((k, v) -> System.out.println(k + "|" +
		// v.doubleValue()));
		System.out.println("Fim do processo");
	}
}// fim da classe
