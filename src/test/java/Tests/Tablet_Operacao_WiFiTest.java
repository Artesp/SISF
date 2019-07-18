package Tests;

import Assistant.MensagensPadrao;
import Assistant.Menu_Operacao_WiFiAssistant;
import Assistant.PathsAssistant;
import Assistant.Questionario_Operacao_WiFiAssistant;
import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_Operacao_WiFiPage;
import Pages.Tablet_Operacao_WiFi_AddMedicaoPage;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

public class Tablet_Operacao_WiFiTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private Tablet_Operacao_WiFiPage page = new Tablet_Operacao_WiFiPage();
    private Tablet_Operacao_WiFi_AddMedicaoPage medicaoPage = new Tablet_Operacao_WiFi_AddMedicaoPage();


    @Test //"MTM_ID 4497: FP3/FP6 - Verificar seção Wi-Fi - Botão Medir"
    public void verificarBotaoMedir_Operacao_WiFi(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        assertsBotoes_WiFi_Medicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        assertEquals("-127", obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/intensidade_sinal")));
    }

    @Test //"MTM_ID 4498: FP7/FP10 - Verificar seção Wi-Fi - Botão Relógio"
    public void verificarBotaoCronometro_Operacao_WiFi() {
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        assertsBotoes_WiFi_Medicao();

        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(1000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        String time = obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/duracao_chamada"));
        assertTrue(time != "00:00:00");
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)
    public void verificaQuestionario_HouveConexao_Sim_Nao(){
        preparaCenario();
        preencherFiscalizacao();
    }

    @Test //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
    public void verificarObrigatoriedade_IntensidadeDeSinal(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheKmInicial("100");
        medicaoPage.preencheMetros("000");
        //medicaoPage.selecionaSentido("S - Sul");
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);

    }

    @Test //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
    public void verificarObrigatoriedade_Km(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheMetros("000");
        //medicaoPage.selecionaSentido("S - Sul");
        medicaoPage.clicarBotaoOK();

        String expected = MensagensPadrao.MEDICAO_WIFI_ERRO_DE_VALIDACAO;
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }

    @Test  //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
    public void verificarObrigatoriedade_Metros(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheKmInicial("13");
        //medicaoPage.selecionaSentido("S - Sul");
        medicaoPage.clicarBotaoOK();

        String expected = MensagensPadrao.MEDICAO_WIFI_ERRO_DE_VALIDACAO;
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }

    /* Teste não Elegível para automação
    @Test //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
    @Ignore
    public void verificarObrigatoriedade_Sentido(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheKmInicial("12");
        medicaoPage.preencheMetros("000");
        medicaoPage.clicarBotaoOK();

        String expected = MensagensPadrao.MEDICAO_WIFI_ERRO_DE_VALIDACAO;
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }
     */

    @Test //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
    public void verificarPreenchimentoManual_WiFi(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        String [] idCampos = new String[2];
        idCampos[0] = PathsAssistant.ID_WIFI_INTENSIDADE_DE_SINAL;
        idCampos[1] = PathsAssistant.ID_WIFI_DURACAO_CHAMADA;

        for (int i = 0; i < idCampos.length; i++ ) {
            boolean enableIsFalse = campoPreenchimentoBloqueado(By.id(idCampos[i]));
            assertFalse("Enable igual a True - Campo não pode ser preenchido manualmente!", enableIsFalse);
        }
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_OpcaoNao(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO, "Não");

        String []expected = new String [2];
        expected[0] =MensagensPadrao.QUESTIONARIO_HOUVE_CONEXAO_AUSENCIA_DE_SINAL;
        expected[1] =MensagensPadrao.QUESTIONARIO_HOUVE_CONEXAO_SINAL_INTERMITENTE;

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], obterTextoElemento(By.xpath("//*[@text='"+expected[i]+"']")));
        }
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_AusenciaDeSinal_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO, "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO_AUSENCIA_SINAL);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size()!= 0);
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_SinalIntermitente_Checked(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO, "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO_SINAL_INTERMITENTE);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size()!= 0);
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_DuasOpcoes_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO, "Não");
        WebElement elemento1 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO_AUSENCIA_SINAL);
        WebElement elemento2 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO_SINAL_INTERMITENTE);

        medicaoPage.clicarCheckBoxQuestionario(elemento1);
        medicaoPage.clicarCheckBoxQuestionario(elemento2);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento1));
        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento2));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size()!= 0);
    }

    @Test //"MTM_ID 4501: FP12/Tabela 2 - Questionário - Houve Atendimento (Opção Sim e Não)"
    public void verificarQuestionario_HouveAtendimento_OpcaoNao(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO, "Não");

        String []expected = new String [2];
        expected[0] = MensagensPadrao.QUESTIONARIO_HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA;
        expected[1] = MensagensPadrao.QUESTIONARIO_HOUVE_ATENDIMENTO_SEM_ATENDIMENTO_CCO;

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], obterTextoElemento(By.xpath("//*[@text='"+expected[i]+"']")));
        }
    }

    @Test //"MTM_ID 4501: FP12/Tabela 2 - Questionário - Houve Atendimento (Opção Sim e Não)"
    public void verificarQuestionario_HouveAtendimento_NaoRealizaChamada_Checked(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO, "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4501: FP12/Tabela 2 - Questionário - Houve Atendimento (Opção Sim e Não)"
    public void verificarQuestionario_HouveAtendimento_SemAtendimentoCCO_Checked(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO, "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO_SEM_ATEMDIMENTO_CCO);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4501: FP12/Tabela 2 - Questionário - Houve Atendimento (Opção Sim e Não)"
    public void verificarQuestionario_HouveAtendimento_DuasOpcoes_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO, "Não");
        WebElement elemento1 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA);
        WebElement elemento2 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO_SEM_ATEMDIMENTO_CCO);

        medicaoPage.clicarCheckBoxQuestionario(elemento1);
        medicaoPage.clicarCheckBoxQuestionario(elemento2);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento1));
        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento2));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_OpcaoPessima(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        page.clicarBotaoAddMedicao();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Péssima");

        String []expected = new String [4];
        expected[0] = MensagensPadrao.QUESTIONARIO_QUALIDADE_COMUNICACAO_AUDIO_BAIXO;
        expected[1] = MensagensPadrao.QUESTIONARIO_QUALIDADE_COMUNICACAO_AUDIO_INTERMITENTE;
        expected[2] = MensagensPadrao.QUESTIONARIO_QUALIDADE_COMUNICACAO_RUIDO_INTERFERENCIA;
        expected[3] = MensagensPadrao.QUESTIONARIO_QUALIDADE_COMUNICACAO_ATENDENTE_NAO_ESCUTA;

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], obterTextoElemento(By.xpath("//*[@text='"+expected[i]+"']")));
        }
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_AudioBaixo_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_AUDIO_BAIXO);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_AudioIntermitente_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_AUDIO_INTERMITENTE);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_RuidoInterferencia_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_RUIDO_INTERFERENCIA);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_AtendenteNaoEscuta_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_ATENDENTE_NAO_ESCUTA);

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_TodasAsOpcoes_Checked(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Péssima");
        WebElement elemento1 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_AUDIO_BAIXO);
        WebElement elemento2 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_AUDIO_INTERMITENTE);
        WebElement elemento3 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_RUIDO_INTERFERENCIA);
        WebElement elemento4 = medicaoPage.obterCheckBoxQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO_ATENDENTE_NAO_ESCUTA);

        medicaoPage.clicarCheckBoxQuestionario(elemento1);
        medicaoPage.clicarCheckBoxQuestionario(elemento2);
        medicaoPage.clicarCheckBoxQuestionario(elemento3);
        medicaoPage.clicarCheckBoxQuestionario(elemento4);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento1));
        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento2));
        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento3));
        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento4));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4503: FP12/Tabela 2 - Questionário - Encerramento da Chamada"
    public void verificarQuestionario_EncerramentoChamada_OpcaoVoluntariamente(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.ENCERRAMENTO_DE_CHAMADA, "Voluntariamente");
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4503: FP12/Tabela 2 - Questionário - Encerramento da Chamada"
    public void verificarQuestionario_EncerramentoChamada_OpcaoInterrupcaoInesperada(){
        preparaCenario();
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(Questionario_Operacao_WiFiAssistant.ENCERRAMENTO_DE_CHAMADA, "Interrupção inesperada");
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4504: FP12/Tabela 2 - Questionário - Itens em branco"
    public void verificarQuestionario_QuestionarioEmBranco(){
        preparaCenario();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);

        medicaoPage.preencherWiFi_SemQuestionario();
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4505: FP13/RVN3 - Verificar número máximo de medições na seção Wi-Fi."
    public void inserirMedicao_EmLote_MenorQueLimite(){
        preparaCenario();
        preencherFiscalizacao_MedicaoEmLote(3);
        salvar();

        String expected = MensagensPadrao.WIFI;
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/tvGrupoFiscalizacao")));
        enviar(3000);
    }

    @Test //"MTM_ID 4505: FP13/RVN3 - Verificar número máximo de medições na seção Wi-Fi."
    public void inserirMedicao_EmLote_IgualAoLimite(){
        preparaCenario();
        preencherFiscalizacao_MedicaoEmLote(50);
        salvar();

        String expected = MensagensPadrao.WIFI;
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/tvGrupoFiscalizacao")));
        enviar(3000);
    }

    /* Teste não Elegível para automação
    @Test //"MTM_ID 4505: FP13/RVN3 - Verificar número máximo de medições na seção Wi-Fi."
    @Ignore
    public void inserirMedicao_EmLote_MaiorQueLimite(){
        preparaCenario();
        preencherFiscalizacao_MedicaoEmLote(3);

        page.clicarBotaoAddMedicao();

        String expected = MensagensPadrao.MEDICAO_WIFI_LIMITE_MAXIMO_PERMITIDO;
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }
    */

    @Test //"MTM_ID 4563: X - Criar fiscalização de Operação - WiFi")
    public void gerarFiscalizacao_Operacao_WiFi(){
        preparaCenario();
        preencherFiscalizacao();
        salvar();

        String expected = MensagensPadrao.WIFI;
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/tvGrupoFiscalizacao")));
        enviar(1000);
    }

    @Test //"MTM_ID 4596: FP2 - Verificar tela principal da fiscalização de Wi-Fi"
    public void verificarMenuLateralFiscalizacao_Operacao_WiFi(){
        preparaCenario();

        String expecteds[] = new String [4];
        expecteds[0] = MensagensPadrao.RODOVIA;
        expecteds[1] = MensagensPadrao.WIFI;
        expecteds[2] = MensagensPadrao.OBS_FISCALIZACAO;
        expecteds[3] = MensagensPadrao.GALERIA;

        String indexMenu [] = new String [4];
        indexMenu[0] = Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA;
        indexMenu[1] = Menu_Operacao_WiFiAssistant.MENUSISF_WIFI;
        indexMenu[2] = Menu_Operacao_WiFiAssistant.MENUSISF_OBS_FISCALIZACAO;
        indexMenu[3] = Menu_Operacao_WiFiAssistant.MENUSISF_GALERIA;

        for(int i = 0; i < expecteds.length; i++){
            String index = indexMenu[i];
            String elemento = expecteds[i];
            String path = "//android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/fsc_content']/android.widget.Button[@index='" + index + "']";
            String nomeMenu = obterTextoElemento(By.xpath(path));
            assertEquals(elemento, nomeMenu);
        }
    }


    private void preparaCenario() {
        loginPage.realizaLogin();
        modulo.moduloOperacao();
        botaoAddFiscalizacao();
        page.grupoSubgrupo();
    }

    private void preencherFiscalizacao(){
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        medicaoPage.preencherWiFi();

        navegarMenuPrincipal((Menu_Operacao_WiFiAssistant.MENUSISF_OBS_FISCALIZACAO));
        page.preencherSecaoObservacao(page.gerarTextoParaTeste());

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_GALERIA);
        page.capturarImagem();
    }

    private void preencherFiscalizacao_MedicaoEmLote(int qtdMed){
        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_RODOVIA);
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_WIFI);
        medicaoPage.preencherWiFiEmLote(qtdMed);

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_OBS_FISCALIZACAO);
        page.preencherSecaoObservacao(page.gerarTextoParaTeste());

        navegarMenuPrincipal(Menu_Operacao_WiFiAssistant.MENUSISF_GALERIA);
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
