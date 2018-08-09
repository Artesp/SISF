package Pages;

import Core.BasePage;

import static Core.DriverFactory.getDriver;

public class ConsRotinaRetornoPage extends BasePage {

    public void preencherSecaoSituacao(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/spnspl_spinner").click();
        getDriver().findElementByXPath("//*[@text='Executada']").click();
    }

    public void preencherSecaoParecerConcessionaria(){
        gerarTextoPraTeste();
    }

    public void preencherSecaoObservacao(){
        gerarTextoPraTeste();
    }

    public void preencherSecaoGaleria(int qtdFotos){
        capturarFotosGaleria_LOTE(qtdFotos);
    }

}
