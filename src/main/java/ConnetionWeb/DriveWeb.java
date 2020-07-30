package ConnetionWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriveWeb {

	private static WebDriver driverWeb;
	public static ChromeOptions chromeOptions = new ChromeOptions();

	public static WebDriver getDriverWeb(String navegador) {

		if (driverWeb == null){
			switch (navegador.toLowerCase()) {
				case "chrome":
					CreateDriverWebChrome();
					break;
			}
		}
		return driverWeb;
	}

	public static void CreateDriverWebChrome() {
		File arquivo = new File("src\\main\\driverNavegador\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", arquivo.getAbsolutePath());
		//chromeOptions.addArguments("--headless");//rodar sem abrir navegador
		chromeOptions.addArguments("--window-size=1366,768");//Rodar com a tela com a resolução informada
		driverWeb = new ChromeDriver(chromeOptions);
		buscarURL();
	}

	public static void buscarURL(){
		driverWeb.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driverWeb.get("https://www.google.com/");
		driverWeb.manage().window().maximize();
	}

	public void fecharNavegador(){
		driverWeb.quit();
	}
}