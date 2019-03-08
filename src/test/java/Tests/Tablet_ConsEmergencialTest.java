package Tests;

import Assistant.InterfaceTabletWeb;
import Assistant.MenuConsEmergencialAssistant;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_ConsEmergencialPage;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class Tablet_ConsEmergencialTest extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private ModulosPage modulo = new ModulosPage();
    private Tablet_ConsEmergencialPage page = new Tablet_ConsEmergencialPage();

    @Test
    public void gerarFiscalizacao_ComSucesso(){
        prepararCenario();
        preencherFiscalizacao();
        salvar();
        String fisc = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo"));
        assertEquals("Conservação Emergencial", fisc);
        enviar(2000);
    }

    @Test
    public void gerarFiscalizacao_ComRetorno(){
        prepararCenario();
        preencherFiscalizacao();
        salvar();
        enviar(1000);

        fecharSistemaSISF();
        classCleanup();
        esperaCarregar(1000);
        new InterfaceTabletWeb().marcarRetorno();

        testInitialize();

    }

    private void preencherFiscalizacao() {
        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaConservacao();

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_EVENTO);
        page.preencherSecaoEvento();

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_REC_MOBILIZADOS);
        page.preencherRecursosMobilizados();

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_SINALIZACAO_LOCAL);
        page.preencherSinalizacaoLocal();

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_ANALISE_PRELIMINAR);
        page.preencherAnalisePreliminar();

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_PROV_MED_CORRETIVAS);
        page.preencherProvMedidasCorretivas();

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_GALERIA);
        page.capturarFoto();
    }

    private void prepararCenario() {
        loginPage.realizaLogin();
        modulo.moduloConservacao();
        botaoAddFiscalizacao();
        page.grupoSubgrupo();
    }

    private void prepararCenarioRetorno(){
        loginPage.realizaLogin();
        modulo.moduloConservacao();

    }

}
