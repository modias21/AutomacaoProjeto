package acoesGenericas;

import ConnetionWeb.DriveWeb;
import com.google.common.io.Files;
import io.qameta.allure.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.*;
import org.openqa.selenium.*;

import java.io.*;
import java.util.*;

public class BasePageWeb {

	private static String NAVEGADOR = "chrome";

	private WebDriver driverWeb = DriveWeb.getDriverWeb(NAVEGADOR);
	private JavascriptExecutor driveJavaScript = (JavascriptExecutor) driverWeb;
	private StepResult stepResult = new StepResult();
	private String id;

	public void esperar(long tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clicar(By by) {
		driverWeb.findElement(by).click();
	}

	public void clicarMascara(By by){
		driverWeb.findElement(by).click();
	}

	public String obterTexto(By by) {
		String texto = driverWeb.findElement(by).getText();
		return texto;
	}

	public void escrever(By by, String texto) {
		clicar(by);
		driverWeb.findElement(by).clear();
		driverWeb.findElement(by).sendKeys(texto);
	}

	public boolean existeElemeto(By by) {
		List<WebElement> elemento = driverWeb.findElements(by);
		return elemento.size() > 0;
	}

	public void encontar(By by) {
		driveJavaScript.executeScript("arguments[0].scrollIntoView(false);", driverWeb.findElement(by));
	}

	//Verifica se RadioButton está marcado ou não retornando True e false
	public boolean selecionado(By by) {
		return driverWeb.findElement(by).isSelected();
	}

	public void marcarCheckBox(By by) {
		if(!selecionado(by)){
			clicar(by);
		}
	}

	public void desmarcarCheckBox(By by) {
		if(selecionado(by)){
			clicar(by);
		}
	}

	//usada para Tirar os prints no final de cada senário de teste
	@Attachment(type = "image/png")
	public byte[] SchreenShot() {
		try {
			File screen = ((TakesScreenshot) driverWeb).getScreenshotAs(OutputType.FILE);
			System.out.println("SchreenShot");
			return Files.toByteArray(screen);
		} catch (IOException e) {
			return  null;
		}
	}

	//Foi preciso criar os seguintes metodos abaixo "startStep" e "stopStep",
	//pois o sistema não estava conseguindo gerar os STEP's quando possui "Try catch"
	public String startStep(String name){
		stepResult.setName(name);
		stepResult.setStatus(Status.PASSED);
		id = UUID.randomUUID().toString();
		Allure.getLifecycle().startStep(id, stepResult);
		return id;
	}
	public void stopStep(Status status, String uuid){
		stepResult.setStatus(status);
		Allure.getLifecycle().updateStep(uuid, stepResult -> stepResult.setStatus(status));
		Allure.getLifecycle().stopStep(uuid);
	}
	public void stopStep(){
		Allure.getLifecycle().stopStep(id);
	}
}
