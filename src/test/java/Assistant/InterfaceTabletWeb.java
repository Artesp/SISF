package Assistant;

import Core.BaseTestWeb;
import Pages.FiscalizacoesEnviadasPage;
import Pages.LoginWebPage;
import Pages.ModulosWebPage;

public class InterfaceTabletWeb extends BaseTestWeb {

    ObjetosParaFiscalizacao obj = new ObjetosParaFiscalizacao();
    LoginWebPage pageLoginWeb = new LoginWebPage();
    ModulosWebPage modulosWeb = new ModulosWebPage();
    FiscalizacoesEnviadasPage fiscEnviadasPage = new FiscalizacoesEnviadasPage();


    public void marcarRetorno(){
        abrirNavegador();
        pageLoginWeb.logarSisfWeb(obj.usuario, obj.senha);
        pageLoginWeb.entrar();

        modulosWeb.clicarBotaoFiscalizacaoEnviadas();

        fiscEnviadasPage.clicarBotaoFiltrar();
        esperaJanelaCarregar(3000);

        fiscEnviadasPage.clicarBotaoMarcarRetorno();
        esperaJanelaCarregar(3000);

        fecharNavegador();

    }
}
