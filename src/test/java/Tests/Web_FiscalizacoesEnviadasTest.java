package Tests;

import Assistant.ObjetosParaFiscalizacao;
import Core.BaseTestWeb;
import Pages.FiscalizacoesEnviadasPage;
import Pages.LoginWebPage;
import Pages.ModulosWebPage;
import org.junit.Test;

public class Web_FiscalizacoesEnviadasTest extends BaseTestWeb {

    private LoginWebPage loginPage = new LoginWebPage();
    private ObjetosParaFiscalizacao obj = new ObjetosParaFiscalizacao();
    private ModulosWebPage modulosPage = new ModulosWebPage();
    private FiscalizacoesEnviadasPage fiscEnviadas = new FiscalizacoesEnviadasPage();

    @Test
    public void marcarRetorno_ComSucesso() {
        loginPage.logarSisfWeb(obj.usuario, obj.senha);
        loginPage.entrar();

        modulosPage.clicarBotaoFiscalizacaoEnviadas();
        esperaJanelaCarregar(1000);

        fiscEnviadas.clicarBotaoFiltrar();
        esperaJanelaCarregar(1000);

        fiscEnviadas.clicarBotaoMarcarRetorno();
        esperaJanelaCarregar(3000);
    }
}
