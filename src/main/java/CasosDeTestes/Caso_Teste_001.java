package CasosDeTestes;

import acoesGenericas.ExecutarCasosDeTeste;
import io.qameta.allure.*;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;

import java.io.IOException;

@Feature("Visualizar video vem me levantar do ministério Discípulos!")
public class Caso_Teste_001 {
    private ExecutarCasosDeTeste exe = new ExecutarCasosDeTeste();

    @Test(description="Visualizar video vem me levantar do ministério Discípulos até o final e fechar o navegador!")
    @Description("Visualizar video vem me levantar do ministério Discípulos até o final e fechar o navegador!")
    public void casoDeTeste() throws BiffException, IOException {
        exe.executarCasoDeTeste(Caso_Teste_001.class.getSimpleName(), "RTA_Padrao");
    }
}