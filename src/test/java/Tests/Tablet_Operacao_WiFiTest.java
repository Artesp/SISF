package Tests;

import static Assistant.Menu_Operacao_WiFiAssistant.*;
import static Assistant.MensagensPadrao.*;

import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import Pages.Tablet_Operacao_WiFiPage;
import Pages.Tablet_Operacao_WiFi_AddMedicaoPage;

import static Assistant.PathsAssistant.*;
import static Assistant.Questionario_Operacao_WiFiAssistant.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Tablet_Operacao_WiFiTest extends BaseTest {

    private ModulosPage modulo = new ModulosPage();
    private LoginPage loginPage = new LoginPage();
    private Tablet_Operacao_WiFiPage page = new Tablet_Operacao_WiFiPage();
    private Tablet_Operacao_WiFi_AddMedicaoPage medicaoPage = new Tablet_Operacao_WiFi_AddMedicaoPage();


    @Test //"MTM_ID 4497: FP3/FP6 - Verificar seção Wi-Fi - Botão Medir"
    public void verificarBotaoMedir_Operacao_WiFi(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        assertsBotoes_WiFi_Medicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        assertEquals("-127", obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/intensidade_sinal")));
    }

    @Test //"MTM_ID 4498: FP7/FP10 - Verificar seção Wi-Fi - Botão Relógio"
    public void verificarBotaoCronometro_Operacao_WiFi() {
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
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

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
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
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheMetros("000");
        //medicaoPage.selecionaSentido("S - Sul");
        medicaoPage.clicarBotaoOK();

        String expected = EXPECTEDS.MEDICAO_WIFI_ERRO_DE_VALIDACAO.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }

    @Test  //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
    public void verificarObrigatoriedade_Metros(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheKmInicial("13");
        //medicaoPage.selecionaSentido("S - Sul");
        medicaoPage.clicarBotaoOK();

        String expected = EXPECTEDS.MEDICAO_WIFI_ERRO_DE_VALIDACAO.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }

    @Test //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
    @Ignore
    public void verificarObrigatoriedade_Sentido(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        medicaoPage.clicarBotaoIntensidadeSinal();
        medicaoPage.clicarBotaoCronometro();
        esperaCarregar(2000);
        medicaoPage.clicarBotaoFinalizaCronometro();
        medicaoPage.preencheKmInicial("12");
        medicaoPage.preencheMetros("000");
        medicaoPage.clicarBotaoOK();

        String expected = EXPECTEDS.MEDICAO_WIFI_ERRO_DE_VALIDACAO.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }

    @Test //"MTM_ID 4499: FP11/Tabela 1 - Verificar obrigatoriedade, formato e regras dos campos."
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

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_OpcaoNao(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO.toString(), "Não");

        String []expected = new String [2];
        expected[0] = EXPECTEDS.QUESTIONARIO_HOUVE_CONEXAO_AUSENCIA_DE_SINAL.toString();
        expected[1] = EXPECTEDS.QUESTIONARIO_HOUVE_CONEXAO_SINAL_INTERMITENTE.toString();

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], obterTextoElemento(By.xpath("//*[@text='"+expected[i]+"']")));
        }
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_AusenciaDeSinal_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO.toString(), "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO_AUSENCIA_SINAL.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size()!= 0);
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_SinalIntermitente_Checked(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO.toString(), "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO_SINAL_INTERMITENTE.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size()!= 0);
    }

    @Test //"MTM_ID 4500: FP12/Tabela 2 - Questionário - Houve Conexão (Opção Sim e Não)"
    public void verificarQuestionario_HouveConexao_DuasOpcoes_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO.toString(), "Não");
        WebElement elemento1 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO_AUSENCIA_SINAL.toString());
        WebElement elemento2 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO_SINAL_INTERMITENTE.toString());

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
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO.toString(), "Não");

        String []expected = new String [2];
        expected[0] = EXPECTEDS.QUESTIONARIO_HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA.toString();
        expected[1] = EXPECTEDS.QUESTIONARIO_HOUVE_ATENDIMENTO_SEM_ATENDIMENTO_CCO.toString();

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], obterTextoElemento(By.xpath("//*[@text='"+expected[i]+"']")));
        }
    }

    @Test //"MTM_ID 4501: FP12/Tabela 2 - Questionário - Houve Atendimento (Opção Sim e Não)"
    public void verificarQuestionario_HouveAtendimento_NaoRealizaChamada_Checked(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO.toString(), "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4501: FP12/Tabela 2 - Questionário - Houve Atendimento (Opção Sim e Não)"
    public void verificarQuestionario_HouveAtendimento_SemAtendimentoCCO_Checked(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO.toString(), "Não");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO_SEM_ATEMDIMENTO_CCO.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4501: FP12/Tabela 2 - Questionário - Houve Atendimento (Opção Sim e Não)"
    public void verificarQuestionario_HouveAtendimento_DuasOpcoes_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO.toString(), "Não");
        WebElement elemento1 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA.toString());
        WebElement elemento2 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO_SEM_ATEMDIMENTO_CCO.toString());

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
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        page.clicarBotaoAddMedicao();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Péssima");

        String []expected = new String [4];
        expected[0] = EXPECTEDS.QUESTIONARIO_QUALIDADE_COMUNICACAO_AUDIO_BAIXO.toString();
        expected[1] = EXPECTEDS.QUESTIONARIO_QUALIDADE_COMUNICACAO_AUDIO_INTERMITENTE.toString();
        expected[2] = EXPECTEDS.QUESTIONARIO_QUALIDADE_COMUNICACAO_RUIDO_INTERFERENCIA.toString();
        expected[3] = EXPECTEDS.QUESTIONARIO_QUALIDADE_COMUNICACAO_ATENDENTE_NAO_ESCUTA.toString();

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], obterTextoElemento(By.xpath("//*[@text='"+expected[i]+"']")));
        }
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_AudioBaixo_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_AUDIO_BAIXO.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_AudioIntermitente_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_AUDIO_INTERMITENTE.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_RuidoInterferencia_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_RUIDO_INTERFERENCIA.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_AtendenteNaoEscuta_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Péssima");
        WebElement elemento = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_ATENDENTE_NAO_ESCUTA.toString());

        medicaoPage.clicarCheckBoxQuestionario(elemento);

        assertTrue(medicaoPage.verificaRespostaCheckBox(elemento));
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4502: FP12/Tabela 2 - Questionário - Qualidade da Comunicação."
    public void verificarQuestionario_QualidadeComunicacao_TodasAsOpcoes_Checked(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Péssima");
        WebElement elemento1 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_AUDIO_BAIXO.toString());
        WebElement elemento2 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_AUDIO_INTERMITENTE.toString());
        WebElement elemento3 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_RUIDO_INTERFERENCIA.toString());
        WebElement elemento4 = medicaoPage.obterCheckBoxQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO_ATENDENTE_NAO_ESCUTA.toString());

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

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.ENCERRAMENTO_DE_CHAMADA.toString(), "Voluntariamente");
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4503: FP12/Tabela 2 - Questionário - Encerramento da Chamada"
    public void verificarQuestionario_EncerramentoChamada_OpcaoInterrupcaoInesperada(){
        preparaCenario();
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();

        medicaoPage.respondeQuestionario(QUESTINARIO_WIFI.ENCERRAMENTO_DE_CHAMADA.toString(), "Interrupção inesperada");
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4504: FP12/Tabela 2 - Questionário - Itens em branco"
    public void verificarQuestionario_QuestionarioEmBranco(){
        preparaCenario();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());

        medicaoPage.preencherWiFi_SemQuestionario();
        medicaoPage.clicarBotaoOK();

        List<WebElement> contagem = page.listaMedicoes();
        assertTrue(contagem .size() > 0);
    }

    @Test //"MTM_ID 4505: FP13/RVN3 - Verificar número máximo de medições na seção Wi-Fi."
    public void inserirMedicao_EmLote_MenorQueLimite(){
        preparaCenario();
        preencherFiscalizacao_MedicaoEmLote(5);
        salvar();

        String expected = EXPECTEDS.WIFI.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo")));
        enviar(3000);
    }

    @Test //"MTM_ID 4505: FP13/RVN3 - Verificar número máximo de medições na seção Wi-Fi."
    @Ignore
    public void inserirMedicao_EmLote_MaiorQueLimite(){
        preparaCenario();
        preencherFiscalizacao_MedicaoEmLote(3);

        page.clicarBotaoAddMedicao();

        String expected = EXPECTEDS.MEDICAO_WIFI_LIMITE_MAXIMO_PERMITIDO.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:id/text_view")));
    }

    @Test //"MTM_ID 4563: X - Criar fiscalização de Operação - WiFi")
    public void gerarFiscalizacao_Operacao_WiFi(){
        preparaCenario();
        preencherFiscalizacao();
        salvar();

        String expected = EXPECTEDS.WIFI.toString();
        assertEquals(expected, obterTextoElemento(By.id("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_grupo")));
        enviar(1000);
    }

    @Test //"MTM_ID 4596: FP2 - Verificar tela principal da fiscalização de Wi-Fi"
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

    private void preencherFiscalizacao_MedicaoEmLote(int qtdMed){
        navegarMenuPrincipal(MENU_WIFI.MENUSISF_RODOVIA.toString());
        page.preencherRodoviaWiFi();

        navegarMenuPrincipal(MENU_WIFI.MENUSISF_WIFI.toString());
        medicaoPage.preencherWiFiEmLote(qtdMed);

        navegarMenuPrincipal((MENU_WIFI.MENUSISF_OBS_FISCALIZACAO.toString()));
        page.preencherSecaoObservacao(page.gerarTextoParaTeste());

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
