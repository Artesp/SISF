package Core;

import static Core.DriverFactoryWeb.*;

public class BasePageWeb {

    public void selecionarModulosWeb(String xpath){
        getNav().findElementByXPath(xpath).click();
    }

    public void clicarOpcaoListaWeb(String comboID, String comboValor){
        new BaseTestWeb().esperaJanelaCarregar(3000);
        getNav().findElementByXPath("//select[@id = '" + comboID + "']/option[contains(.,'"+ comboValor + "')]").click();
    }

    public void clicarOpcaoMesWeb(String mesCombo, String mesValor){
        getNav().findElementByXPath("//select[@class = '" + mesCombo + "']/option[contains(.,'" + mesValor + "')]").click();
    }

    public void clicarOpcaoDataWeb(String mesCombo, String mesValor){
        getNav().findElementByXPath("//select[@class='"+mesCombo+ "']/option[contains(.,'"+mesValor+"')]").click();
    }

}
