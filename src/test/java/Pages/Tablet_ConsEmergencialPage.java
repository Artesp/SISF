package Pages;

import Assistant.PathsAssistant;
import Core.BasePage;
import Core.BaseTest;

import static Core.DriverFactory.getDriver;

public class Tablet_ConsEmergencialPage extends BasePage {

    public void grupoSubgrupo(){
        getDriver().findElementByXPath(PathsAssistant.XPATH_SELECIONA_GRUPO_CLICK).click();
        getDriver().findElementByXPath(PathsAssistant.XPATH_GRUPO_CONSERVACAO_EMERGENCIAL).click();
    }

    public void preencherRodoviaConservacao(){
        preencherRodovia();
    }

    public void preencherSecaoEvento(){
        getDriver().findElementById(PathsAssistant.ID_CAMPO_EVENTO).sendKeys(gerarTextoPraTeste());
        getDriver().findElementByXPath(PathsAssistant.XPATH_DATA_PREVISTA_INICIO).click();
        new BaseTest().modalData_ScroollUpDia();
        getDriver().findElementByXPath(PathsAssistant.XPATH_DATA_PREVISTA_TERMINO).click();
        new BaseTest().modalData_ScroollUpDia();
        getDriver().findElementByXPath(PathsAssistant.XPATH_DATA_EFETIVA_INICIO).click();
        new BaseTest().modalData_ScroollUpDia();
    }

    public void preencherRecursosMobilizados(){
        getDriver().findElementById(PathsAssistant.ID_BOTAO_REC_MOBILIZADOS).click();
        getDriver().findElementById(PathsAssistant.ID_CAMPO_REC_MOBILIZADOS).sendKeys(gerarTextoPraTeste());
    }

    public void preencherSinalizacaoLocal(){
        getDriver().findElementById(PathsAssistant.ID_BOTAO_SINALIZACAO_LOCAL).click();
        getDriver().findElementById(PathsAssistant.ID_CAMPO_SINALIZACAO_LOCAL).sendKeys(gerarTextoPraTeste());
    }

    public void preencherAnalisePreliminar(){
        getDriver().findElementById(PathsAssistant.ID_CAMPO_ANALISE_PRELIMINAR).sendKeys(gerarTextoPraTeste());
    }

    public void preencherProvMedidasCorretivas(){
        getDriver().findElementById(PathsAssistant.ID_CAMPO_PREV_MED_CORRETIVAS).sendKeys(gerarTextoPraTeste());
    }

    public void capturarFoto(){
        capturarFotosGaleria();
    }

    public void clicarBotaoAddRetorno(){
        botaoAddRetorno();
    }

    public void alterarCampos(String campo, String valor){
        getDriver().findElementById(campo).clear();
        getDriver().findElementById(campo).sendKeys(valor);
    }
}
