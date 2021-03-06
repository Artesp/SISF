package Tests;

import Assistant.helperTabletWeb;
import Assistant.MenuConsEmergencialAssistant;
import Assistant.MenuConsEmergencialRetornoAssistant;
import Assistant.PathsAssistant;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_ConsEmergencialPage;
import Pages.Tablet_ConsEmergencialRetornoPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tablet_ConsEmergencialTest extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private ModulosPage modulo = new ModulosPage();
    private Tablet_ConsEmergencialPage page = new Tablet_ConsEmergencialPage();
    private Tablet_ConsEmergencialRetornoPage pageRetorno = new Tablet_ConsEmergencialRetornoPage();

    @Test
    public void gerarFiscalizacao_ComSucesso(){
        prepararCenario();
        preencherFiscalizacao();
        salvar();
        String[] fiscalizacoes = listarFiscalizacoes("Conservação Emergencial");
        assertEquals("Conservação Emergencial", fiscalizacoes[0]);
        enviar(2000);
    }

    @Test
    public void gerarFiscalizacao_ComRetorno(){

        prepararCenario();
        preencherFiscalizacao();
        salvar();
        enviar(1000);
        String[] codigoWeb = getCodigoWeb();
        String codigoAtualWeb = recuperarIDFiscalizacao(obterTextoElemento(By.xpath(PathsAssistant.XPATH_MSG_ENVIO_COM_SUCESSO_INDEX)));

        fecharSistemaSISF();
        classCleanup();
        esperaCarregar(1000);
        new helperTabletWeb().marcarRetorno();

        testInitialize();
        prepararCenarioRetorno();
        preencherFiscalizacaoRetorno();
        salvar();

        for (int i = 0;i<codigoWeb.length;i++){
            if (codigoAtualWeb.equalsIgnoreCase(codigoWeb[i])) {
            }
                assertEquals(codigoAtualWeb, codigoWeb[i]);
            }
        enviar(3000);
    }

    @Test
    public void alterarFiscalizacao_ComSucesso(){
        prepararCenario();
        preencherFiscalizacao();
        gravar();

        String fisc = obterTextoElemento(By.id(PathsAssistant.ID_GRUPO_FISCALIZACAO));
        assertEquals("Conservação Emergencial", fisc);

        editarFiscalizacao();

        alterarFiscalizacao();
        salvar();

        String fiscAlterada = obterTextoElemento(By.id(PathsAssistant.ID_GRUPO_FISCALIZACAO));
        assertEquals("Conservação Emergencial", fiscAlterada);

        enviar(1000);
    }



    private void preencherFiscalizacaoRetorno() {
        navegarMenuPrincipal(MenuConsEmergencialRetornoAssistant.MENUSISFRET_SITUACAO);
        pageRetorno.preencherSecaoSituacao();

        navegarMenuPrincipal(MenuConsEmergencialRetornoAssistant.MENUSISFRET_PARECERCONCESSIONARIA);
        pageRetorno.preencherSecaoParecerConcessionaria();

        navegarMenuPrincipal(MenuConsEmergencialRetornoAssistant.MENUSISFRET_OBSERVACAO);
        pageRetorno.preencherSecaoObservacao();

        navegarMenuPrincipal(MenuConsEmergencialRetornoAssistant.MENUSISFRET_GALERIA);
        pageRetorno.preencherSecaoGaleria(2);
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
        page.clicarBotaoAtualizar();
        page.clicarBotaoAddRetorno();
    }

    private void alterarFiscalizacao(){
        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_EVENTO);
        page.alterarCampos(PathsAssistant.ID_CAMPO_EVENTO, "Teste");

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_REC_MOBILIZADOS);
        page.alterarCampos(PathsAssistant.ID_CAMPO_REC_MOBILIZADOS, "Teste");

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_SINALIZACAO_LOCAL);
        page.alterarCampos(PathsAssistant.ID_CAMPO_SINALIZACAO_LOCAL, "Teste");

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_ANALISE_PRELIMINAR);
        page.alterarCampos(PathsAssistant.ID_CAMPO_ANALISE_PRELIMINAR, "Teste");

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_PROV_MED_CORRETIVAS);
        page.alterarCampos(PathsAssistant.ID_CAMPO_PREV_MED_CORRETIVAS, "Teste");

        navegarMenuPrincipal(MenuConsEmergencialAssistant.MENUSISF_GALERIA);
        page.capturarFoto();
    }

    private String[] getCodigoWeb(){
       String[] codigoWeb = listaCodigoWebSucesso();
       return codigoWeb;
    }

    private String codigoWeb(){
        String codigo = recuperarIDFiscalizacao(PathsAssistant.XPATH_MSG_ENVIO_COM_SUCESSO_INDEX);
        return codigo;
    }

}
