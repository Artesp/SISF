package Tests;

import Assistant.MensagensPadrao;
import Assistant.Menu_Operacao_CallBoxAssistant;
import Assistant.ObjetosParaFiscalizacao;
import Assistant.PathsAssistant;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_Operacao_CallBoxPage;
import Pages.Tablet_Operacao_CallBox_AddCallBoxPage;
import com.sun.org.apache.xpath.internal.operations.Equals;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.util.Assert;

import static org.junit.Assert.assertEquals;
import static org.springframework.util.Assert.*;

public class Tablet_Operacao_CallBoxTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private Tablet_Operacao_CallBoxPage page = new Tablet_Operacao_CallBoxPage();
    private Tablet_Operacao_CallBox_AddCallBoxPage pageAddCallbox = new Tablet_Operacao_CallBox_AddCallBoxPage();

    @Test //"MTM_ID 3682: Acesso à fiscalização de Operação - Call Box"
    public void acessar_fiscalizacao_callbox(){
        //1 - Acessar o Sisf no tablet  e selecionar o módulo de Operação.
        //Esperado: O sistema exibe a tela com botões de ação, dentre eles o de Incluir fiscalização
        //2 - Clicar em Incluir
        //Esperado: sistema deve exibir a tela de grupos de fiscalização.
        preprararCenario();
        //3 - Selecionar o Grupo Call Box
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        //Esperado: O sistema exibe a tela de preenchimento da fiscalização.
        isTrue(elementoExiste(By.id("br.gov.sp.artesp.sisf.mobile:id/lstcbn_btn_add")), "Formulário de preenchimento de call box deveria ser exibido");
    }

    @Test //"MTM_ID 3683: FA1 - Gerar fiscalização de Operação  - Call Box"
    public void gerarFiscalizacao_Operacao_CallBox(){

        //1 - No tablet, acessar o módulo de operação e incluir uma fiscalização alvo do teste.
        //Esperado: O sistema apresenta o formulário de preenchimento da fiscalização.
        preprararCenario();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaCallBox();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_OBSERVACAO);
        page.preencherObservacao(page.gerarTextoParaTeste());
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_GALERIA);
        page.preencherGaleria();
        //2 - Clicar no menu Call Box
        //Esperado: O sistema exibe a tela Call Box.
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        isTrue(elementoExiste(By.id(PathsAssistant.ID_CALLBOX_BOTAO_ADD_CALLBOX)), "Tela deve conter o botão '+' para adicionar um call box");
        //3 - Clicar no botão "+"
        //Esperado: O sistema exibe a tela de consulta de equipamentos por RA
        page.clicarBotaoAddCallBox();
        //4 - Clicar no botão Lupa
        //Esperado: O sistema exibe tela com lista de equipamentos recuperados da integração com o MITS.
        pageAddCallbox.clickBotaoLupa();
        isTrue(elementoExiste(By.id(PathsAssistant.ID_CALLBOX_LISTA_EQUIPAMENTOS)), "Deve exibir a tela de listagem de equipamentos recuperados do MITS");
        //5 - Selecionar um dos equipamentos e clicar em OK
        //Esperado: O sistema retorna para a tela de Call Box com o campo RA
        // exibindo o número de RA selecionado anteriormente, com os campos “Dados de Transmissão”, “Dados de Conservação”, “Dados de Segurança”, “Dados de Instalação”.
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        assertEquals(ObjetosParaFiscalizacao.RA, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/numRA")));
    }

    @Test //"MTM_ID 3684: RVN1 - Verificar preenchimento do Item de call box, sem preencher o anterior"
    @Ignore //Teste para esta regra é desnecessário. Novo componente implementado invalida esta regra!
    public void verificarPreechimentoDoItem(){

    }

    @Test //"MTM_ID 3685: RVN1 - Adicionar um ou mais itens de Call Box"
    public void adicionarCallBox_MaisDeUmEquipamento(){
        //1 - Gerar uma fiscalização com mais de um item de call box adicionados, com todos os campos preenchidos com dados válidos.
        //Esperado: O sistema deve validar as informações, gravar e disponibilizar para envio à web.
        preprararCenario();
        preencherFiscalizacao();

        String[] RA_Equipamentos = new String[3];
        RA_Equipamentos[0] = "010008002485";
        RA_Equipamentos[1] = "010008002621";
        RA_Equipamentos[2] = "010008002711";

        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);

        for (int i = 0; i < RA_Equipamentos.length; i++){
            page.clicarBotaoAddCallBox();
            pageAddCallbox.clickBotaoLupa();
            pageAddCallbox.selecionaEquipamentoRA(RA_Equipamentos[i]);

            preencherQuestionarioCallBox();
            pageAddCallbox.clickOK_AdicionaCallBox();

            isTrue(elementoExiste(By.xpath("//*[@text='"+RA_Equipamentos[i]+"']")), "Número de RA deve ser exibido na lista de callbox incluidos.");
        }

        salvar();
        enviar(3000);
    }

    @Test //"MTM_ID 3686: RVN2 - Verificar campos para cada item de call box adicionado."
    @Ignore //Funcionalidade já automatizada no script de teste MTM_ID 3685
    public void verificarQuestionario_ItensAdicionados(){

    }

    @Test //"MTM_ID 3687: RVN3 - Verificar ordenação da lista de Equipamentos."
    @Ignore //Funcionalidae de ordenação não é um teste elegível de automação.
    public void verificarOrdenacaoPorLocalizacao(){

    }

    @Test //"MTM_ID 3690: RVN3 - Verificar número de RA utilizado."
    public void adicionarCallBox_ComMesmoRA(){
        //1 - Adicionar mais de um item de equipamento na fiscalização de call box e verificar na lista do segundo call box adicionado,
        // se o número de RA utilizado no primeiro item é exibido na lista.
        //Esperado: O sistema não deve exibir na lista de equipamentos, o “RA” selecionado no item adicionado anteriormente.
        preprararCenario();
        preencherFiscalizacao();

        String[] RA_Equipamentos = new String[2];
        RA_Equipamentos[0] = "010008002485";
        RA_Equipamentos[1] = "010008002485";

        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);

        for (int i = 0; i < RA_Equipamentos.length; i++) {
            page.clicarBotaoAddCallBox();
            pageAddCallbox.clickBotaoLupa();
            pageAddCallbox.selecionaEquipamentoRA(RA_Equipamentos[i]);

            preencherQuestionarioCallBox();
            pageAddCallbox.clickOK_AdicionaCallBox();
        }
            isTrue(elementoExiste(By.xpath("//*[@text='"+MensagensPadrao.ADICIONAR_CALLBOX_RA_DUPLICADO+" "+RA_Equipamentos[0]+"']")),
                    "Número de RA deve ser exibido na lista de callbox incluidos.");

    }



    private void preencherQuestionarioCallBox() {
        pageAddCallbox.preencherSecaoCallBox();
    }

    private void preprararCenario() {
        loginPage.realizaLogin();
        modulo.moduloOperacao();
        botaoAddFiscalizacao();
        page.grupoSubgrupo();
    }

    private void preencherFiscalizacao(){
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaCallBox();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_OBSERVACAO);
        page.preencherObservacao(page.gerarTextoParaTeste());
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_GALERIA);
        page.preencherGaleria();
    }

}
