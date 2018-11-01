package Tests;

import Assistant.InterfaceTabletWeb;
import Assistant.MenuConsRotinaRetornoAssistant;
import Core.BaseTest;
import Pages.*;
import org.junit.Test;
import org.openqa.selenium.By;

import static Assistant.MenuConsRotinaAssistant.*;
import static Assistant.MenuConsRotinaRetornoAssistant.*;
import static org.junit.Assert.*;

public class Tablet_ConsRotinaTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private ConsRotinaPage page = new ConsRotinaPage();
    private ConsRotinaRetornoPage pageRetorno = new ConsRotinaRetornoPage();
    private MenuConsRotinaPage menuPage = new MenuConsRotinaPage();
    private MenuConsRotinaRetornoPage menuPageRetorno = new MenuConsRotinaRetornoPage();


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
        preparaCenarioRetorno();
        preencherDadosFiscalizacaoRetorno();
        salvar();

        String fiscRetorno = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_retorno_lbl"));
        assertEquals("(RETORNO)", fiscRetorno);

        enviar(3000);
    }

    @Test
    public void alterarFiscalizacao_ConservacaoDeRotina(){
        prepararCenario();
        preencherDadosFiscalizacao();
        gravar();

        selecionaFiscalizacao();
        botaoEditarFiscalizacao();
        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_PRAZO.toString());

        page.preencherSecaoPrazo("Estruturas","Estruturas","Pichações e vandalismo");

        salvar();

        enviar(3000);
    }

    @Test
    public void excluirFiscalizacao_ConservacaoDeRotina(){
        prepararCenario();
        preencherDadosFiscalizacao();
        salvar();
        selecionaFiscalizacao();
        deletarFiscalizacao();
        atualizarTelaConsultaSisf();
        esperaCarregar(1000);
        int numFisc = listarFiscalizacoes();
        //assertTrue(numFisc == 0);
    }






    private void prepararCenario(){
        loginPage.realizaLogin();
        modulo.moduloConservacao();
        botaoAddFiscalizacao();
        page.grupoSubgrupo();
    }

    private void preparaCenarioRetorno(){
        loginPage.realizaLogin();
        modulo.moduloConservacao();
        page.clicarBotaoAddRetorno();
    }

    private void preencherDadosFiscalizacao(){
        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaConservacao();

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_PRAZO.toString());
        page.preencherSecaoPrazo("Pavimento", "Revestimento Primário", "Reconformação de vias secundárias");

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_TRECHO.toString());
        page.preencherSecaoTrecho();

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_OBS_FISCALIZACAO.toString());
        page.preencherSecaoObservacao();

        menuPage.navegarMenuPrincipal(MENU_CONSROTINA.MENUSISF_GALERIA.toString());
        page.fotosEmLote(4);

    }

    private void preencherDadosFiscalizacaoRetorno(){
        menuPageRetorno.navegarMenuPrincipal(MENU_CONSROTINARETORNO.MENUSISFRET_SITUACAO.toString());
        pageRetorno.preencherSecaoSituacao();

        menuPageRetorno.navegarMenuPrincipal(MENU_CONSROTINARETORNO.MENUSISFRET_PARECERCONCESSIONARIA.toString());
        pageRetorno.preencherSecaoParecerConcessionaria();

        menuPageRetorno.navegarMenuPrincipal(MENU_CONSROTINARETORNO.MENUSISFRET_OBSERVACAO.toString());
        pageRetorno.preencherSecaoObservacao();

        menuPageRetorno.navegarMenuPrincipal(MENU_CONSROTINARETORNO.MENUSISFRET_GALERIA.toString());
        pageRetorno.preencherSecaoGaleria(2);
    }
}
