package Tests;

import Assistant.*;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_Operacao_CallBoxPage;
import Pages.Tablet_Operacao_CallBox_AddCallBoxPage;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.util.Assert.isTrue;

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

    @Test //"MTM_ID 3692: RVN3 - Verificar preenchimento automatico de campos da seção Rodovia"
    public void verificarKMInicialFinalRodovia_NaoDeveSerIgualKMCallBox(){
        //1 - Gerar uma fiscalização de call box com dados válidos, e selecionar um item de equipamento e preenchelo com um RA.
        // Retornar a seção Rodovia e verificar os campos Inical, +Mts, Final, +Mts e Sentido.
        //Esperado: O sistema não deve sobreescrever com os dados de localização do equipamento, os dados preenchidos manualmente da seção Rodovia.
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        page.clicarBotaoAddCallBox();
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);

        String KMInicialFinalCallBox = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/kmInicial"));
        String sentidoCallBox = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/sentido"));
        preencherQuestionarioCallBox();
        pageAddCallbox.clickOK_AdicionaCallBox();

        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_RODOVIA);
        String KMInicialRodovia = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_inicial_text"));
        String MTSInicialRodovia = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_mtsini_text"));
        String mesclaStringsRodovia = KMInicialRodovia + "+" + MTSInicialRodovia;
        String sentidoRodovia = obterTextoElemento(By.xpath("//android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/rod_sentido_spnspl']" +
                "/android.widget.LinearLayout[@index='0']" +
                "/android.widget.Spinner[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/spnspl_spinner']" +
                "/android.widget.TextView[@index='0']"));

        assertNotEquals(mesclaStringsRodovia, KMInicialFinalCallBox);
        assertNotEquals(sentidoRodovia, sentidoCallBox);






    }

    @Test //"MTM_ID 3696: Tabela 2 - Call Box - Verificar campos - Dados de Transmissão"
    public void verificarObrigatoriedade_Dados_de_Transmissao(){
        //1 - Gerar fiscalização de Call Box, e acessar a seção Call Box.
        //Esperado: O sistema exibe o botão para adicionar  um item de call box
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        isTrue(elementoExiste(By.id(PathsAssistant.ID_CALLBOX_BOTAO_ADD_CALLBOX)), "Tela deve conter o botão '+' para adicionar um call box");
        //2 - Selecionar o botão "+"
        //Esperado: O sistema exibe o campo alvo dos testes.
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO)),"Deve ser exibido o questionário Dados de Transmissão");
        //3 - Verificar obrigatoriedade do campo alvo Radio Button
        //Esperado: O campo Radio button é obrigatório
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();

        isTrue(elementoExiste(By.xpath("//*[@text='"+MensagensPadrao.QUESTIONARIO_CALLBOX_CAMPO_OBRIGATORIO+"']")),
                "Perguntas do questonário devem ser de preenchimento obrigatório.");

    }

    @Test //"MTM_ID 3696: Tabela 2 - Call Box - Verificar campos - Dados de Transmissão"
    public void verificarRespostasQuestionario_Dados_De_Transmissao(){
        //1 - Verificar opções do campo Radio Button
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado, Inexistente
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO)),"Deve ser exibido o questionário Dados de Transmissão");
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        //2 - Selecionar para o campo alvo Radio Button a opção Inadequado
        //Esperado: O sistema exibe as opções com checkbox Sem Sinal, Não Conecta, CCO Não Ouve, Ruído , Áudio Baixo.
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO,"Inadequado");
        String [] respostaQuestao = new String[5];
        respostaQuestao[0] = Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_SEM_SINAL;
        respostaQuestao[1] = Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_NAO_CONECTA;
        respostaQuestao[2] = Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_CCO_NAO_OUVE;
        respostaQuestao[3] = Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_RUIDO;
        respostaQuestao[4] = Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_AUDIO_BAIXO;
        for(int i = 0;i < respostaQuestao.length;i++) {
            isTrue(elementoExiste(By.xpath(pageAddCallbox.gerarPathParaRespostaQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO,
                    respostaQuestao[i]))), "O sistema deve exibir as respostas do questionário");
        }
        //3 - Selecionar várias opções exibidas
        //Esperado: O usuário pode selecionar múltiplas opções, as opções não são obrigatórias
        preencherCheckBox_Dados_Transmissao();
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();
        isTrue(elementoExiste(By.xpath("//*[@text='"+ObjetosParaFiscalizacao.RA+"']")), "Número de RA deve ser exibido na lista de callbox incluidos.");
        salvar();
        enviar(3000);
    }

    @Test //"MTM_ID 3794: Tabela 2 - Call Box - Verificar campos - Dados de Conservação"
    public void verificarObrigatoriedade_Dados_de_Conservacao(){
        //1 - Gerar fiscalização de Call Box, e acessar a seção Call Box.
        //Esperado: O sistema exibe o botão para adicionar  um item de call box
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        isTrue(elementoExiste(By.id(PathsAssistant.ID_CALLBOX_BOTAO_ADD_CALLBOX)), "Tela deve conter o botão '+' para adicionar um call box");
        //2 - Selecionar o botão "+"
        //Esperado: O sistema exibe o campo alvo dos testes.
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO)),"Deve ser exibido o questionário Dados de Conservação");
        //3 - Verificar obrigatoriedade do campo alvo Radio Button
        //Esperado: O campo Radio button é obrigatório
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();

        isTrue(elementoExiste(By.xpath("//*[@text='"+MensagensPadrao.QUESTIONARIO_CALLBOX_CAMPO_OBRIGATORIO+"']")),
                "Perguntas do questonário devem ser de preenchimento obrigatório.");
    }

    @Test //"MTM_ID 3794: Tabela 2 - Call Box - Verificar campos - Dados de Conservação"
    public void verificarRespostasQuestionario_Dados_De_Conservacao(){
        //1 - Verificar opções do campo Radio Button
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado, Inexistente
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO)),"Deve ser exibido o questionário Dados de Transmissão");
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        //2 - Selecionar para o campo alvo Radio Button a opção Inadequado
        //Esperado: O sistema exibe as opções com checkbox
        // Ausência de Gabinete
        //Totem Sujo
        //Botão de Chamada
        //Elementos de Fixação
        //Telas e Tampas Danificadas ou Quebradas
        //Pontos de Ferrugem
        //Falta de Aparelho
        //Pichados
        //Falta de Drenagem no Local
        //Entorno Sujo
        //Mal Conservado
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,"Inadequado");
        page.rolarTelaQuestionario_Up();
        String [] respostaQuestao = new String[11];
        respostaQuestao[0] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_AUSENCIA_DE_GABINETE;
        respostaQuestao[1] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_TOTEM_SUJO;
        respostaQuestao[2] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_BOTAO_DE_CHAMADA;
        respostaQuestao[3] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_ELEMENTOS_DE_FIXACAO;
        respostaQuestao[4] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_TELAS_E_TAMPAS_DANIFICADAS_QUEBRADAS;
        respostaQuestao[5] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_PONTOS_DE_FERRUGEM;
        respostaQuestao[6] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_FALTA_DE_APARELHO;
        respostaQuestao[7] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_PICHADOS;
        respostaQuestao[8] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_FALTA_DE_DRENAGEM_NO_LOCAL;
        respostaQuestao[9] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_ENTORNO_SUJO;
        respostaQuestao[10] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_MAL_CONSERVADO;

        for(int i = 0;i < respostaQuestao.length;i++) {
            isTrue(elementoExiste(By.xpath(pageAddCallbox.gerarPathParaRespostaQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                    respostaQuestao[i]))), "O sistema deve exibir as respostas do questionário");
        }
        //3 - Selecionar várias opções exibidas
        //Esperado: O usuário pode selecionar múltiplas opções, as opções não são obrigatórias
        preencherCheckBox_Dados_Conservacao();
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();
        isTrue(elementoExiste(By.xpath("//*[@text='"+ObjetosParaFiscalizacao.RA+"']")), "Número de RA deve ser exibido na lista de callbox incluidos.");
        salvar();
        enviar(3000);
    }

    @Test //"MTM_ID 3795: Tabela 2 - Call Box - Verificar campos - Dados de Segurança"
    public void verificarObrigatoriedade_Dados_de_Seguranca(){
        //1 - Gerar fiscalização de Call Box, e acessar a seção Call Box.
        //Esperado: O sistema exibe o botão para adicionar  um item de call box
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        isTrue(elementoExiste(By.id(PathsAssistant.ID_CALLBOX_BOTAO_ADD_CALLBOX)), "Tela deve conter o botão '+' para adicionar um call box");
        //2 - Selecionar o botão "+"
        //Esperado: O sistema exibe o campo alvo dos testes.
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA)),"Deve ser exibido o questionário Dados de Segurança");
        //3 - Verificar obrigatoriedade do campo alvo Radio Button
        //Esperado: O campo Radio button é obrigatório
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();

        isTrue(elementoExiste(By.xpath("//*[@text='"+MensagensPadrao.QUESTIONARIO_CALLBOX_CAMPO_OBRIGATORIO+"']")),
                "Perguntas do questonário devem ser de preenchimento obrigatório.");
    }

    @Test //"MTM_ID 3795: Tabela 2 - Call Box - Verificar campos - Dados de Segurança"
    public void verificarRespostasQuestionario_Dados_De_Seguranca(){
        //1 - Verificar opções do campo Radio Button
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado, Inexistente
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA)),"Deve ser exibido o questionário Dados de Segurança");
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        //2 - Selecionar para o campo alvo Radio Button a opção Inadequado
        //Esperado: O sistema exibe as opções com checkbox
        //Barreira e Guarda Corpos de Proteção Ausentes
        //Mau Estado de Conservação
        //Danificados
        //Ausência de Sinalização de Segurança
        //Ausência de Defensa Metálica ou Barreira
        //Ausência de Muro de Arrimo
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,"Inadequado");
        String [] respostaQuestao = new String[6];
        respostaQuestao[0] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_BARREIRA_E_GUARDA_COPOS;
        respostaQuestao[1] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_MAU_ESTADO_DE_CONSERVACAO;
        respostaQuestao[2] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_DANIFICADOS;
        respostaQuestao[3] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_AUSENCIA_DE_SINALIZACAO_SEGURANCA;
        respostaQuestao[4] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_AUSENCIA_DE_DENFENSA_METALICA;
        respostaQuestao[5] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_AUSENCIA_DE_MURO_DE_ARRIMO;
        for(int i = 0;i < respostaQuestao.length;i++) {
            isTrue(elementoExiste(By.xpath(pageAddCallbox.gerarPathParaRespostaQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,
                    respostaQuestao[i]))), "O sistema deve exibir as respostas do questionário");
        }
        //3 - Selecionar várias opções exibidas
        //Esperado: O usuário pode selecionar múltiplas opções, as opções não são obrigatórias
        preencherCheckBox_Dados_Seguranca();
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();
        isTrue(elementoExiste(By.xpath("//*[@text='"+ObjetosParaFiscalizacao.RA+"']")), "Número de RA deve ser exibido na lista de callbox incluidos.");
        salvar();
        enviar(3000);
    }

    @Test //"MTM_ID 3796: Tabela 2 - Call Box - Verificar campos - Dados de Instalação"
    public void verificarObrigatoriedade_Dados_de_Instalacao(){
        //1 - Gerar fiscalização de Call Box, e acessar a seção Call Box.
        //Esperado: O sistema exibe o botão para adicionar  um item de call box
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        isTrue(elementoExiste(By.id(PathsAssistant.ID_CALLBOX_BOTAO_ADD_CALLBOX)), "Tela deve conter o botão '+' para adicionar um call box");
        //2 - Selecionar o botão "+"
        //Esperado: O sistema exibe o campo alvo dos testes.
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO)),"Deve ser exibido o questionário Dados de Instalação");
        //3 - Verificar obrigatoriedade do campo alvo Radio Button
        //Esperado: O campo Radio button é obrigatório
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();

        isTrue(elementoExiste(By.xpath("//*[@text='"+MensagensPadrao.QUESTIONARIO_CALLBOX_CAMPO_OBRIGATORIO+"']")),
                "Perguntas do questonário devem ser de preenchimento obrigatório.");
    }

    @Test //"MTM_ID 3796: Tabela 2 - Call Box - Verificar campos - Dados de Instalação"
    public void verificarRespostasQuestionario_Dados_De_Instalacao(){
        //1 - Verificar opções do campo Radio Button
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado, Inexistente
        preprararCenario();
        preencherFiscalizacao();
        navegarMenuPrincipal(Menu_Operacao_CallBoxAssistant.MENUSISF_CALLBOX);
        page.clicarBotaoAddCallBox();
        isTrue(elementoExiste(By.id(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA)),"Deve ser exibido o questionário Dados de Segurança");
        pageAddCallbox.clickBotaoLupa();
        pageAddCallbox.selecionaEquipamentoRA(ObjetosParaFiscalizacao.RA);
        //2 - Selecionar para o campo alvo Radio Button a opção Inadequado
        //Inativo
        //Desativado
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO,"Inadequado");
        String [] respostaQuestao = new String[2];
        respostaQuestao[0] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO_INATIVO;
        respostaQuestao[1] = Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO_DESATIVADO;
        for(int i = 0;i < respostaQuestao.length;i++) {
            isTrue(elementoExiste(By.xpath(pageAddCallbox.gerarPathParaRespostaQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO,
                    respostaQuestao[i]))), "O sistema deve exibir as respostas do questionário");
        }
        //3 - Selecionar várias opções exibidas
        //Esperado: O usuário pode selecionar múltiplas opções, as opções não são obrigatórias
        preencherCheckBox_Dados_Instalacao();
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO, "Adequado");
        pageAddCallbox.respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA, "Adequado");
        pageAddCallbox.clickOK_AdicionaCallBox();
        isTrue(elementoExiste(By.xpath("//*[@text='"+ObjetosParaFiscalizacao.RA+"']")), "Número de RA deve ser exibido na lista de callbox incluidos.");
        salvar();
        enviar(3000);
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

    private void preencherCheckBox_Dados_Transmissao(){
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_SEM_SINAL);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_NAO_CONECTA);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_CCO_NAO_OUVE);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_RUIDO);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_TRANSMISSAO_AUDIO_BAIXO);
    }

    private void preencherCheckBox_Dados_Conservacao(){
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_AUSENCIA_DE_GABINETE);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_TOTEM_SUJO);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_BOTAO_DE_CHAMADA);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_ELEMENTOS_DE_FIXACAO);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_TELAS_E_TAMPAS_DANIFICADAS_QUEBRADAS);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_PONTOS_DE_FERRUGEM);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_FALTA_DE_APARELHO);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_PICHADOS);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_FALTA_DE_DRENAGEM_NO_LOCAL);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_ENTORNO_SUJO);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO_MAL_CONSERVADO);
    }

    private void preencherCheckBox_Dados_Seguranca(){
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_BARREIRA_E_GUARDA_COPOS);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_MAU_ESTADO_DE_CONSERVACAO);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_DANIFICADOS);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_AUSENCIA_DE_SINALIZACAO_SEGURANCA);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_AUSENCIA_DE_DENFENSA_METALICA);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA_AUSENCIA_DE_MURO_DE_ARRIMO);
    }

    private void preencherCheckBox_Dados_Instalacao(){
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO_INATIVO);
        pageAddCallbox.respondeQuestionarioInadequado(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO,
                Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO_DESATIVADO);

    }

}
