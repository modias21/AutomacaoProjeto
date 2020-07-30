package LerXls;


import jxl.*;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LerXls {
	private ArrayList<String> listaColuna = new ArrayList<String>();
	private int linhas;
	private int colunas;
	int coluna = 3;

	//Realiza a leitura do arquivo por colunas
	public ArrayList<String> ler_planilha_completa(int linha_tabela, int coluna_tabela, String ler_arquivo_xls) throws IOException, BiffException {
		int abaPlanilha = 0;

		File arquivo = new File("RTA\\" + ler_arquivo_xls + ".xls");

		ArrayList<String> listaColuna = new ArrayList<String>();

		WorkbookSettings conf = new WorkbookSettings();
		conf.setSuppressWarnings(true);
		conf.setEncoding("ISO-8859-1");

		Workbook workbook = Workbook.getWorkbook(arquivo, conf);
		Sheet sheet = workbook.getSheet(abaPlanilha);

		linhas = sheet.getRows();
		colunas = sheet.getColumns();

		for (int i = 0; i < linhas; i++) {
			for (int j = coluna_tabela; j < colunas; j++) {
				if (i == linha_tabela) {
					Cell cell = sheet.getCell(j, i);
					String dado = cell.getContents();
					listaColuna.add(dado);
				}
			}
		}
		return listaColuna;
	}

	//Realizar a identificação de qual caso de teste será executado
	public ArrayList<String> identificar_caso_teste_rodar(int linha, String ler_arquivo) throws IOException, BiffException {
		int abaPlanilha = 0;
		File arquivo = new File("RTA\\" + ler_arquivo + ".xls");

		Workbook workbook = Workbook.getWorkbook(new File(arquivo.getAbsolutePath()));
		Sheet sheet = workbook.getSheet(abaPlanilha);

		linhas = sheet.getRows();
		colunas = sheet.getColumns();

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (j == linha) {
					Cell cell = sheet.getCell(j, i);
					String dado = cell.getContents();
					listaColuna.add(dado);
				}
			}
		}
		return listaColuna;
	}

	//Realiza a leitura da linha de cada Caso de teste
	public ArrayList<String> ler_linha_caso_teste(String NumeroCasoTeste, String ler_arquivo) throws BiffException, IOException {
		int tamanhoArray = identificar_caso_teste_rodar(0, ler_arquivo).size();
		int linhaCasoTeste = 0;
		for (int i = 0; i < tamanhoArray; i++) {
			if ((identificar_caso_teste_rodar(i, ler_arquivo).get(i)).equals(NumeroCasoTeste)) {
				linhaCasoTeste = i;
				break;
			}
		}
		ler_planilha_completa(0, 0, ler_arquivo).clear();

		return ler_planilha_completa(linhaCasoTeste, coluna, ler_arquivo);
	}

	//Realiza a leitura da linha que identifica ação que será realizada sendo eles Clique, Texto, combolist e etc...
	public ArrayList<String> ler_linha_acao(String ler_arquivo) throws BiffException, IOException {
		int linha = 5;
		return ler_planilha_completa(linha, coluna, ler_arquivo);
	}

	//Realiza a leitura da linha para informar se é ID ou XPATH, linha 8 da planilha
	public ArrayList<String> identificar_id_ou_xpath (String ler_arquivo) throws BiffException, IOException {
		int linha = 7;
		return ler_planilha_completa(linha, coluna, ler_arquivo);
	}

	//realiza a leitura da linha que possui o objeto que será usado para a ação sendo ele um XPATH ou ID, linha 9 da planilha
	public ArrayList<String> ler_linha_codigo(String ler_arquivo) throws BiffException, IOException {
		int linha = 8;
		return ler_planilha_completa(linha, coluna, ler_arquivo);
	}

	//Quando for X ele não realizada nem uma ação, esse metodo é feito para retornar Falso quando for X
	public boolean naox(String valString) {
		if ("x".equals(valString.toLowerCase())) {
			System.out.println("X");
			return false;
		} else
			return true;
	}
}