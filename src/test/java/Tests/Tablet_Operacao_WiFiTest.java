package Tests;

import static Assistant.Menu_Operacao_WiFiAssistant.*;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_Operacao_WiFiPage;
import Pages.Tablet_Operacao_WiFi_AddMedicaoPage;
import org.junit.Test;

public class Tablet_Operacao_WiFiTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private Tablet_Operacao_WiFiPage page = new Tablet_Operacao_WiFiPage();
    private Tablet_Operacao_WiFi_AddMedicaoPage medicaoPage = new Tablet_Operacao_WiFi_AddMedicaoPage();

    @Test
    public void inserirMedicao_EmLote(){
        preparaCenario();
        preencherFiscalizacao();
        salvar();
    }



    private void preparaCenario() {
        loginPage.realizaLogin();
        modulo.moduloOperacao();
        botaoAddFiscalizacao();
        page.grupoSubgrupo();
    }

    private void preencherFiscalizacao(){
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        medicaoPage.preencherWiFiEmLote(51);

        navegarMenuPrincipal((MENU_WIFI.MENUSISF_OBS_FISCALIZACAO.toString()));
        page.gerarTextoParaTeste();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_GALERIA.toString());
        page.capturarImagem();
    }

}
