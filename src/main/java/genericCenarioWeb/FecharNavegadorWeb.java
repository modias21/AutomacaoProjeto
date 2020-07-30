package genericCenarioWeb;

import acoesGenericas.BasePageWeb;
import ConnetionWeb.DriveWeb;
import io.qameta.allure.Description;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Fechar navegador")
public class FecharNavegadorWeb extends BasePageWeb {

	private DriveWeb driverWeb = new DriveWeb();

	@Test
	@Description("Automação está sendo finalidada")
	@Story("Está sendo finalizado o navagador")
	public void EncerrarNavegadorWeb() {
		driverWeb.fecharNavegador();
	}
}