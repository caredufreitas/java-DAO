/**
 * 
 */
package br.com.resoucer.executa.executa;

import java.io.IOException;

import br.com.resource.processa.negocio.Processa;

/**
 * @author Ticar
 *
 */
public class Executa {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Processa.processarArquivo();
	}

}
