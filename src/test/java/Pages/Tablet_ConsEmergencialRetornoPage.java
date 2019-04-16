package Pages;

import Core.BasePage;

import static Core.DriverFactory.getDriver;

public class Tablet_ConsEmergencialRetornoPage extends BasePage {

    public void preencherSecaoSituacao(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/spnspl_spinner").click();
        getDriver().findElementByXPath("//*[@text='Executada']").click();
    }

    public void preencherSecaoParecerConcessionaria(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText").sendKeys(gerarTextoPraTeste());
    }

    public void preencherSecaoObservacao(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText").sendKeys(gerarTextoPraTeste());
    }

    public void preencherSecaoGaleria(int qtdFotos){
        capturarFotosGaleria_LOTE(qtdFotos);
    }

}
