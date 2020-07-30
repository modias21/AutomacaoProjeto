package acoesGenericas;

import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AcoesWeb extends BasePageWeb {

	@Step("Clicar")
	public void clicar(String id_ou_xpath, String codigo_do_elemento) {

		if ("id".equals(id_ou_xpath.toLowerCase())) {
			encontar(By.id(codigo_do_elemento));
			System.out.println("Clique em: " + obterTexto(By.id(codigo_do_elemento)));
			clicar(By.id(codigo_do_elemento));

		} else if ("xpath".equals(id_ou_xpath.toLowerCase())) {
			encontar(By.xpath(codigo_do_elemento));
			System.out.println("Clique em: " + obterTexto(By.xpath(codigo_do_elemento)));
			clicar(By.xpath(codigo_do_elemento));
		}
	}

	@Step("Escrever")
	public void escrever(String id_ou_xpath, String codigo_do_elemento,  String texto) {

		if ("id".equals(id_ou_xpath.toLowerCase())) {
			encontar(By.id(codigo_do_elemento));
			escrever(By.id(codigo_do_elemento), texto);

		} else if ("xpath".equals(id_ou_xpath.toLowerCase())) {
			encontar(By.xpath(codigo_do_elemento));
			escrever(By.xpath(codigo_do_elemento), texto);
		}
		System.out.println("Texto escrito: " + texto);
	}

	@Step("Esperando")
	public void esperarTempo(String id_ou_xpath, String codigo_do_elemento) {

		if ("id".equals(id_ou_xpath.toLowerCase())) {
			while(existeElemeto(By.id(codigo_do_elemento))){
				esperar(4000);
				System.out.println("Esperando!!!");
			}

		} else if ("xpath".equals(id_ou_xpath.toLowerCase())) {
			while(existeElemeto(By.xpath(codigo_do_elemento))){
				esperar(4000);
				System.out.println("Esperando!!!");
			}
		}

	}

	// O Step é interno
	public void validarTexto(String id_xpath, String codigo_do_elemento, String texto) {
		String idStep = new String();

		if ("id".equals(id_xpath.toLowerCase())) {
			try{
				idStep = startStep("Validar Texto");
				Assert.assertEquals(texto, obterTexto(By.id(codigo_do_elemento)));
				stopStep();

			}catch(AssertionError ex){
				SchreenShot();
				stopStep(Status.FAILED, idStep);
				throw ex;
			}

			System.out.println(texto + " <============> " +  obterTexto(By.id(codigo_do_elemento)));

		} else if ("xpath".equals(id_xpath.toLowerCase())) {

			try{
				idStep = startStep("Validar Texto");
				Assert.assertEquals(texto, obterTexto(By.xpath(codigo_do_elemento)));
				stopStep();

			}catch(AssertionError ex){
				SchreenShot();
				stopStep(Status.FAILED, idStep);
				throw ex;
			}

			System.out.println(texto + " <============> " +  obterTexto(By.xpath(codigo_do_elemento)));
		}
	}


	@Step("Marcar Checkbox")
	public void checkBox(String id_ou_xpath, String codigo_do_elemento, String marcar_desmarcar) {

		if("id".equals(id_ou_xpath.toLowerCase())){
			if ("marcar".equals(marcar_desmarcar.toLowerCase())) {
				marcarCheckBox(By.id(codigo_do_elemento));
				System.out.println("Combobox Marcada!!!");

			} else if ("desmarcar".equals(marcar_desmarcar.toLowerCase())) {
				desmarcarCheckBox(By.id(codigo_do_elemento));
				System.out.println("Combobox Desmarcada!!!");
			} else {
				System.out.println("Está escrito errado, correto é 'marcar' ou 'desmarca'");
			}
		}else if("xpath".equals(id_ou_xpath.toLowerCase())){
			if ("marcar".equals(marcar_desmarcar.toLowerCase())) {
				marcarCheckBox(By.xpath(codigo_do_elemento));
				System.out.println("Combobox Marcada!!!");

			} else if ("desmarcar".equals(marcar_desmarcar.toLowerCase())) {
				desmarcarCheckBox(By.xpath(codigo_do_elemento));
				System.out.println("Combobox Desmarcada!!!");
			} else {
				System.out.println("Está escrito errado, correto é 'marcar' ou 'desmarca'");
			}
		}

	}

	//o step é interno
	public void elementoExiste( String id_ou_xpath, String codigo_do_elemento) {
		String idStep = new String();

		if ("id".equals(id_ou_xpath.toLowerCase())) {
			try{
				idStep = startStep("Verificar se objeto existe");
				Assert.assertTrue(existeElemeto(By.id(codigo_do_elemento)));
				stopStep();

			}catch(AssertionError ex){
				SchreenShot();
				stopStep(Status.FAILED, idStep);
				throw ex;
			}
			
		} else if ("xpath".equals(id_ou_xpath.toLowerCase())) {

			try{
				idStep = startStep("Verificar se objeto existe");
				Assert.assertTrue(existeElemeto(By.xpath(codigo_do_elemento)));
				stopStep();

			}catch(AssertionError ex){
				SchreenShot();
				stopStep(Status.FAILED, idStep);
				throw ex;
			}
		}
		System.out.println("Objeto ( "+ codigo_do_elemento +" ) encontrado");
	}

	@Step("Evidências")
	public void tirarPrint(){
		SchreenShot();
	}
}
