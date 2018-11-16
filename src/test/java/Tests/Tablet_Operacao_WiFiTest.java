package Tests;

import static Assistant.Menu_Operacao_WiFiAssistant.*;
import static Assistant.MensagensPadrao.*;

import Assistant.PathsAssistant;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_Operacao_WiFiPage;
import Pages.Tablet_Operacao_WiFi_AddMedicaoPage;

import static Assistant.PathsAssistant.*;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.omg.CORBA.Object;
import org.openqa.selenium.By;

public class Tablet_Operacao_WiFiTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private Tablet_Operacao_WiFiPage page = new Tablet_Operacao_WiFiPage();
    private Tablet_Operacao_WiFi_AddMedicaoPage medicaoPage = new Tablet_Operacao_WiFi_AddMedicaoPage();

    @Test
    @DisplayName("MTM_ID 4505: FP13/RVN3 - Verificar número máximo de medições na seção Wi-Fi.")
    @Ignore
    public void inserirMedicao_EmLote(){
        preparaCenario();
        preencherFiscalizacao_MedicaoEmLote();
        salvar();

        String expected = EXPECTEDS.WIFI.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo")));
    }

    @Test
    @DisplayName("MTM_ID 4563: X - Criar fiscalização de Operação - WiFi")
    public void gerarFiscalizacao_Operacao_WiFi(){
        preparaCenario();
        preencherFiscalizacao();
        salvar();

        String expected = EXPECTEDS.WIFI.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo")));

        enviar(3000);
    }

    @Test
    @DisplayName("MTM_ID 4596: FP2 - Verificar tela principal da fiscalização de Wi-Fi")
    public void verificarMenuLateralFiscalizacao_Operacao_WiFi(){
        preparaCenario();

        String expecteds[] = new String [4];
        expecteds[0] = EXPECTEDS.RODOVIA.toString();
        expecteds[1] = EXPECTEDS.WIFI.toString();
        expecteds[2] = EXPECTEDS.OBS_FISCALIZACAO.toString();
        expecteds[3] = EXPECTEDS.GALERIA.toString();

        String indexMenu [] = new String [4];
        indexMenu[0] = MENU_WIFI.MENUSISF_RODOVIA.toString();
        indexMenu[1] = MENU_WIFI.MENUSISF_WIFI.toString();
        indexMenu[2] = MENU_WIFI.MENUSISF_OBS_FISCALIZACAO.toString();
        indexMenu[3] = MENU_WIFI.MENUSISF_GALERIA.toString();

        for(int i = 0; i < expecteds.length; i++){
            String index = indexMenu[i];
            String elemento = expecteds[i];
            String path = "//android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/fsc_content']/android.widget.Button[@index='" + index + "']";
            String nomeMenu = obterTextoElemento(By.xpath(path));
            assertEquals(elemento, nomeMenu);
        }

    }

    @Test
    @DisplayName("MTM_ID 4497: FP3/FP6 - Verificar seção Wi-Fi - Botão Medir")
    public void verificarBotaoMedir_Operacao_WiFi(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        assertsBotoes_WiFi_Medicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        assertEquals("-127", obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/intensidade_sinal")));
    }

    @Test
    @DisplayName("MTM_ID 4498: FP7/FP10 - Verificar seção Wi-Fi - Botão Relógio")
    public void verificarBotaoCronometro_Operacao_WiFi(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        assertsBotoes_WiFi_Medicao();

        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(1000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        assertEquals("00:00:01", obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/duracao_chamada")));
    }

    @Test
    @DisplayName("MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos.")
    public void verificarObrigatoriedade_IntensidadeDeSinal(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheKmInicial("100");
        medicaoPage.preencheMetros("000");
        medicaoPage.selecionaSentido("S - Sul");
        medicaoPage.clicarBotaoOK();

        String expected = EXPECTEDS.MEDICAO_WIFI_ERRO_DE_VALIDACAO.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }

    @Test
    @DisplayName("MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos.")
    public void verificarPreenchimentoManual_WiFi(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        String [] idCampos = new String[2];
        idCampos[0] = PATHS_SISF_TABLET.WIFI_INTENSIDADE_DE_SINAL.toString();
        idCampos[1] = PATHS_SISF_TABLET.WIFI_DURACAO_CHAMADA.toString();

        for (int i = 0; i < idCampos.length; i++ ) {
            boolean enableIsFalse = campoPreenchimentoBloqueado(By.id(idCampos[i]));
            assertFalse("Enable igual a True - Campo não pode ser preenchido manualmente!", enableIsFalse);
        }
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

    private void assertsBotoes_WiFi_Medicao(){
        boolean elemento = elementoExiste(By.id("br.gov.sp.artesp.sisf.mobile:id/lstcbn_btn_add"));
        assertTrue(elemento);
        page.clicarBotaoAddMedicao();
        elemento = elementoExiste(By.id("br.gov.sp.artesp.sisf.mobile:id/btn_intensidade_sinal"));
        assertTrue(elemento);
        elemento = elementoExiste(By.id("br.gov.sp.artesp.sisf.mobile:id/btn_cronometro"));
        assertTrue(elemento);
    }



}
