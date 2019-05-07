package Pages;

import Core.BasePageWeb;


public class ModulosWebPage extends BasePageWeb{

    public void clicarBotaoFiscalizacaoEnviadas(){
        String path = "//*[@alt = 'Consultar Fiscalizações Enviadas']";
        selecionarModulosWeb(path);
    }

    public void clicarBotaoRelatoriosDeFiscalizacao(){
        String path = "//*[@alt = 'Relatórios de Fiscalizações']";
        selecionarModulosWeb(path);
    }
}
