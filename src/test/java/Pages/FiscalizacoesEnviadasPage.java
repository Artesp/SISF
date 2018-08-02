package Pages;

import Core.BaseTestWeb;

import static Core.DriverFactoryWeb.getNav;

public class FiscalizacoesEnviadasPage extends BaseTestWeb {

    public void clicarBotaoFiltrar(){
        getNav().findElementById("btnConsultar").click();
    }

    public void clicarBotaoMarcarRetorno(){
        getNav().findElementByXPath("//button[contains(@id, 'marcarRetorno')]").click();
    }
}
