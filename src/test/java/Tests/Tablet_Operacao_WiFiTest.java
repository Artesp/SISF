package Tests;

import static Assistant.Menu_Operacao_WiFiAssistant.*;
import static Assistant.MensagensPadrao.*;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_Operacao_WiFiPage;
import Pages.Tablet_Operacao_WiFi_AddMedicaoPage;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;

public class Tablet_Operacao_WiFiTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private Tablet_Operacao_WiFiPage page = new Tablet_Operacao_WiFiPage();
    private Tablet_Operacao_WiFi_AddMedicaoPage medicaoPage = new Tablet_Operacao_WiFi_AddMedicaoPage();

    @Test
    @DisplayName("MTM_ID 4505: FP13/RVN3 - Verificar número máximo de medições na seção Wi-Fi.")
    /*@Ignore*/
    public void inserirMedicao_EmLote(){
        preparaCenario();
        preencherFiscalizacao_MedicaoEmLote();
        salvar();

        String expected = EXPECTEDS.WIFI.toString();
        Assert.assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo")));
    }

    @Test
    @DisplayName("MTM_ID 4563: X - Criar fiscalização de Operação - WiFi")
    public void gerarFiscalizacao_Operacao_WiFi(){
        preparaCenario();
        preencherFiscalizacao();
        salvar();

        String expected = EXPECTEDS.WIFI.toString();
        Assert.assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo")));

        enviar(3000);
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
        medicaoPage.preencherWiFi();

        navegarMenuPrincipal((MENU_WIFI.MENUSISF_OBS_FISCALIZACAO.toString()));
        page.preencherSecaoObservacao(page.gerarTextoParaTeste());

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_GALERIA.toString());
        page.capturarImagem();
    }

    private void preencherFiscalizacao_MedicaoEmLote(){
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        medicaoPage.preencherWiFiEmLote(5);

        navegarMenuPrincipal((MENU_WIFI.MENUSISF_OBS_FISCALIZACAO.toString()));
        page.gerarTextoParaTeste();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_GALERIA.toString());
        page.capturarImagem();
    }



}
