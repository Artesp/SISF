package Core;

import org.openqa.selenium.By;

import static Core.DriverFactoryWeb.*;

public class BasePageWeb {

    protected void selecionarModulosWeb(String xpath){
        getNav().findElementByXPath(xpath).click();
    }

    protected void clicarOpcaoListaWeb(String comboID, String comboValor){
        new BaseTestWeb().esperaJanelaCarregar(1000);
        getNav().findElementByXPath("//select[@id = '" + comboID + "']/option[contains(.,'"+ comboValor + "')]").click();
    }

    protected void clicarOpcaoMesWeb(String mesCombo, String mesValor){
        getNav().findElementByXPath("//select[@class = '" + mesCombo + "']/option[contains(.,'" + mesValor + "')]").click();
    }

    protected void clicarOpcaoDataWeb(String mesCombo, String mesValor){
        getNav().findElementByXPath("//select[@class='"+mesCombo+ "']/option[contains(.,'"+mesValor+"')]").click();
    }

    protected void clicarBotaoFiltrar(){
        getNav().findElementById("btnConsultar").click();
    }

    protected void clicarBotaoMarcarRetorno(){
        getNav().findElementByXPath("//button[contains(@id, 'marcarRetorno')]").click();
    }

    protected void clicarBotaoDetalhar(){
        getNav().findElementByXPath("//button[contains(@id, 'detalhar')]").click();
    }

    protected void clicarBotaoEditar(){
        getNav().findElementByXPath("//button[contains(@id, 'editar')]").click();
    }

    protected void clicarBotaoVoltar(){
        getNav().findElementByXPath("//button[contains(@id,'voltar')]").click();
    }

    protected void clicarBotaoSimConfirmacao(){
        getNav().findElementByXPath("//input[contains(@id, 'yesButton')]").click();
    }

    protected String obterTextoPorElemento(By by){
        return  getNav().findElement(by).getText();
    }

    protected boolean camposNaoEditaveis(By by){
        boolean bloqueado = getNav().findElement(by).isEnabled();
        return bloqueado;
    }

}
