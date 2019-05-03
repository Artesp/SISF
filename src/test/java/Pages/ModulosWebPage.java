package Pages;

import Core.BasePageWeb;

import static Core.DriverFactoryWeb.*;

public class ModulosWebPage extends BasePageWeb{

    public void clicarBotaoFiscalizacaoEnviadas(){
        String path = "//*[@alt = 'Consultar Fiscalizações Enviadas']";
        //getNav().findElementByXPath(path).click();
        selecionarModulosWeb(path);
    }

    public void clicarBotaoRelatoriosDeFiscalizacao(){
        String path = "//*[@alt = 'Relatórios de Fiscalizações']";
        //getNav().findElementByXPath(path).click();
        selecionarModulosWeb(path);
    }
}
