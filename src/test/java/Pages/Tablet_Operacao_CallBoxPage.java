package Pages;

import Assistant.PathsAssistant;
import Core.BasePage;
import org.openqa.selenium.By;

import static Core.DriverFactory.getDriver;

public class Tablet_Operacao_CallBoxPage extends BasePage {

    public void grupoSubgrupo(){
        getDriver().findElementByXPath(PathsAssistant.XPATH_SELECIONA_GRUPO_CLICK).click();
        getDriver().findElementByXPath(PathsAssistant.XPATH_GRUPO_CALLBOX).click();
    }

    public void preencherRodoviaCallBox(){
        preencherRodovia();
    }

    public void preencherObservacao(String texto){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText").sendKeys(texto);
    }

    public String gerarTextoParaTeste(){
        String texto = gerarTextoPraTeste();
        return texto;
    }

    public void preencherGaleria(){
        capturarFotosGaleria();
    }

    public void clicarBotaoAddCallBox(){
        getDriver().findElementById(PathsAssistant.ID_CALLBOX_BOTAO_ADD_CALLBOX).click();
    }

    public void rolarTelaQuestionario_Up(){
        scroolUp_Tela(By.id("br.gov.sp.artesp.sisf.mobile:id/scrollView1"));
    }

}
