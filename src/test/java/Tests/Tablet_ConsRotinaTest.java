package Tests;

import Assistant.InterfaceTabletWeb;
import Core.BaseTest;
import Pages.ConsRotinaPage;
import Pages.LoginPage;
import Pages.MenuConsRotinaPage;
import Pages.ModulosPage;
import org.junit.Test;
import org.openqa.selenium.By;

import static Assistant.MenuConsRotinaAssistant.*;
import static org.junit.Assert.*;

public class Tablet_ConsRotinaTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private ConsRotinaPage page = new ConsRotinaPage();
    private MenuConsRotinaPage menuPage = new MenuConsRotinaPage();


    @Test
    public void gerarFiscalizacao_ComSucesso(){
        prepararCenario();
        preencherDadosFiscalizacao();
        salvar();

        String fisc = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo"));
        assertEquals("Conservação de Rotina", fisc);

        enviar(3000);
    }

    @Test
    public void gerarFiscalizacao_ComRetorno(){
        prepararCenario();
        preencherDadosFiscalizacao();
        salvar();
        enviar(3000);

        fecharSistemaSISF();
        classCleanup();
        esperaCarregar(3000);
        new InterfaceTabletWeb().marcarRetorno();

        testInitialize();

    }


    private void prepararCenario(){
        loginPage.realizaLogin();
        modulo.moduloConservacao();
        botaoAddFiscalizacao();
        page.grupoSubgrupo();
    }

    private void preencherDadosFiscalizacao(){
        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_RODOVIA.toString());
        page.preencherRodovia();

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_PRAZO.toString());
        page.preencherSecaoPrazo();

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_TRECHO.toString());
        page.preencherSecaoTrecho();

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_OBS_FISCALIZACAO.toString());
        page.preencherSecaoObservacao();

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_GALERIA.toString());
        page.capturarFotosGaleria_LOTE(4);

    }

    private void preencherDadosFiscalizacaoRetorno(){

    }
}
