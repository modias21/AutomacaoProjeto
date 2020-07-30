package acoesGenericas;

import LerXls.LerXls;
import jxl.read.biff.BiffException;

import java.io.IOException;

public class ExecutarCasosDeTeste {

	private LerXls executar = new LerXls();
	private AcoesWeb acao = new AcoesWeb();

	public void executarCasoDeTeste(String classe, String ler_arquivo) throws BiffException, IOException {

		int tamanho = executar.ler_linha_acao(ler_arquivo).size();

		for (int i = 0; i < tamanho; i++) {

			String linhaCasoTeste = executar.ler_linha_caso_teste(classe, ler_arquivo).get(i);
			String linhaAcao = executar.ler_linha_acao(ler_arquivo).get(i);
			String id_ou_xpath = executar.identificar_id_ou_xpath(ler_arquivo).get(i);
			String linha_do_codigo = executar.ler_linha_codigo(ler_arquivo).get(i);

			if (executar.naox(linhaCasoTeste)) {
				switch (linhaAcao) {
					case "escrever":
						acao.escrever(id_ou_xpath, linha_do_codigo , linhaCasoTeste);
						break;

					case "clicar":
						acao.clicar(id_ou_xpath, linha_do_codigo);
						break;

					case "validarTexto":
						acao.validarTexto(id_ou_xpath, linha_do_codigo, linhaCasoTeste);
						break;

					case "checkbox":
						acao.checkBox(id_ou_xpath, linha_do_codigo, linhaCasoTeste);
						break;

					case "existe":
						acao.elementoExiste(id_ou_xpath, linha_do_codigo);
						break;

					case "esperar":
						acao.esperarTempo(id_ou_xpath, linha_do_codigo);
						break;
				}
			}
		}
		acao.tirarPrint();
	}
}