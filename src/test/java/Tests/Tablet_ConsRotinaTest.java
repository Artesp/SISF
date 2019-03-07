package Tests;

import Assistant.InterfaceTabletWeb;
import Assistant.MenuConsRotinaAssistant;
import Assistant.MenuConsRotinaRetornoAssistant;
import Assistant.PathsAssistant;
import Core.BaseTest;
import Pages.*;
import org.junit.Test;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    public void gerarFiscalizacao_ComSucesso_EmLote(){

        int contador = 0;
        int numeroFiscalizacoes = 3;
        loginPage.realizaLogin();
        modulo.moduloConservacao();

        do {
            botaoAddFiscalizacao();
            page.grupoSubgrupo();

            menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_RODOVIA);
            page.preencherRodoviaConservacao();

            menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_PRAZO);
            page.preencherSecaoPrazo("Pavimento", "Revestimento Primário", "Reconformação de vias secundárias");

            menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_TRECHO);
            page.preencherSecaoTrecho();

            menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_OBS_FISCALIZACAO);
            page.preencherSecaoObservacao();

            menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_GALERIA);
            page.capturaDeFotosRandomico(5);

            salvar();
            String fisc = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo"));
            assertEquals("Conservação de Rotina", fisc);
            String horaS = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
            enviar(3000);
            System.out.println("Fiscalização Enviada: Nº " + contador + " " + page.retornaHora() );

            contador++;


        }while (contador <= numeroFiscalizacoes);

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
        menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_PRAZO);

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
        menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaConservacao();

        menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_PRAZO);
        page.preencherSecaoPrazo("Pavimento", "Revestimento Primário", "Reconformação de vias secundárias");

        menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_TRECHO);
        page.preencherSecaoTrecho();

        menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_OBS_FISCALIZACAO);
        page.preencherSecaoObservacao();

        menuPage.navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_GALERIA);
        page.fotosEmLote(4);

    }

    private void preencherDadosFiscalizacaoRetorno(){
        menuPageRetorno.navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_SITUACAO);
        pageRetorno.preencherSecaoSituacao();

        menuPageRetorno.navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_PARECERCONCESSIONARIA);
        pageRetorno.preencherSecaoParecerConcessionaria();

        menuPageRetorno.navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_OBSERVACAO);
        pageRetorno.preencherSecaoObservacao();

        menuPageRetorno.navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_GALERIA);
        pageRetorno.preencherSecaoGaleria(2);
    }
}
