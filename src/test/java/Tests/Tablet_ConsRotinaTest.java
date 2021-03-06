package Tests;

import Assistant.MenuConsRotinaAssistant;
import Assistant.MenuConsRotinaRetornoAssistant;
import Assistant.PathsAssistant;
import Assistant.helperTabletWeb;
import Core.BaseTest;
import Pages.ConsRotinaPage;
import Pages.ConsRotinaRetornoPage;
import Pages.LoginPage;
import Pages.ModulosPage;
import org.junit.Test;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class Tablet_ConsRotinaTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private ConsRotinaPage page = new ConsRotinaPage();
    private ConsRotinaRetornoPage pageRetorno = new ConsRotinaRetornoPage();


    @Test
    public void gerarFiscalizacao_ComSucesso(){
        prepararCenario();
        preencherDadosFiscalizacao();
        salvar();

        String fisc = obterTextoElemento(By.id(PathsAssistant.ID_GRUPO_FISCALIZACAO));
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

            navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_RODOVIA);
            page.preencherRodoviaConservacao();

            navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_PRAZO);
            page.preencherSecaoPrazo("Pavimento", "Revestimento Primário", "Reconformação de vias secundárias");

            navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_TRECHO);
            page.preencherSecaoTrecho();

            navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_OBS_FISCALIZACAO);
            page.preencherSecaoObservacao();

            navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_GALERIA);
            page.capturaDeFotosRandomico(5);

            salvar();
//            String fisc = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo"));
//            assertEquals("Conservação de Rotina", fisc);
            String horaS = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
            //enviar(3000);
            System.out.println("Fiscalização Enviada: Nº " + contador + " " + horaS);

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
        new helperTabletWeb().marcarRetorno();

        testInitialize();
        preparaCenarioRetorno();
        preencherDadosFiscalizacaoRetorno();
        salvar();

        String fiscRetorno = obterTextoElemento(By.id(PathsAssistant.ID_RETORNO_PREENCHIDO));
        assertEquals("Retorno", fiscRetorno);

        enviar(3000);
    }

    @Test
    public void alterarFiscalizacao_ConservacaoDeRotina(){
        prepararCenario();
        preencherDadosFiscalizacao();
        gravar();
        editarFiscalizacao();
        navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_PRAZO);

        page.preencherSecaoPrazo("Estruturas","Estruturas","Pichações e vandalismo");

        salvar();

        enviar(3000);
    }

    @Test
    public void excluirFiscalizacao_ConservacaoDeRotina(){
        prepararCenario();
        preencherDadosFiscalizacao();
        salvar();
        deletarFiscalizacao();
        atualizarTelaConsultaSisf();
        esperaCarregar(1000);
        int numFisc = listarFiscalizacoes("Conservação de Rotina").length;
        assertTrue(numFisc == 0);
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
        page.clicarBotaoAtualizar();
        page.clicarBotaoAddRetorno();
    }

    private void preencherDadosFiscalizacao(){
        navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaConservacao();

        navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_PRAZO);
        page.preencherSecaoPrazo("Pavimento", "Revestimento Primário", "Reconformação de vias secundárias");

        navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_TRECHO);
        page.preencherSecaoTrecho();

        navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_OBS_FISCALIZACAO);
        page.preencherSecaoObservacao();

        navegarMenuPrincipal(MenuConsRotinaAssistant.MENUSISF_GALERIA);
        page.fotosEmLote(1);

    }

    private void preencherDadosFiscalizacaoRetorno(){
        navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_SITUACAO);
        pageRetorno.preencherSecaoSituacao();

        navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_PARECERCONCESSIONARIA);
        pageRetorno.preencherSecaoParecerConcessionaria();

        navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_OBSERVACAO);
        pageRetorno.preencherSecaoObservacao();

        navegarMenuPrincipal(MenuConsRotinaRetornoAssistant.MENUSISFRET_GALERIA);
        pageRetorno.preencherSecaoGaleria(2);
    }
}
