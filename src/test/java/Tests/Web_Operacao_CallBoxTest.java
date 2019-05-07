package Tests;

import Assistant.PathsAssistant;
import Assistant.Questionario_Operacao_CallBoxAssistant;
import Core.BasePageWeb;
import Core.BaseTestWeb;
import Pages.ModulosWebPage;
import Pages.Web_Operacao_CallBoxPage;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.util.Assert.isTrue;

public class Web_Operacao_CallBoxTest extends BaseTestWeb {

    private Web_Operacao_CallBoxPage page = new Web_Operacao_CallBoxPage();
    private ModulosWebPage modulosWebPage = new ModulosWebPage();

    @Test //"MTM_ID 3803: X - Consultar Fiscalização - Call Box"
    public void consultaFiscalizacao_CallBox(){
        //1 - Acessar o módulo de fiscalizações Enviadas
        // Esperado: O sistema apresenta a grid de resultados e filtros para pesquisa de fiscalizações
        page.logarWeb();
        modulosWebPage.clicarBotaoFiscalizacaoEnviadas();
        String textoElemento = page.obterTextoElemento("//*[@class='fonte_titulo']");
        isTrue(!textoElemento.isEmpty(), "Deve retornar o texto da tela de consulta na Web");
        //2 - Filtrar para que seja retornado as fiscalizações de Call Box e clicar em consultar.
        //Esperado: O sistema retornas todas as fiscalizações de Call Box de acordo com o filtro realizado
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        //3 - Clicar no botão Detalhar de uma das fiscalizações da Grid
        //Esperado: O sistema exibe a tela de consulta da fiscalização de Call Box
        page.botaoFiltrar();
        textoElemento = page.obterTextoElemento("//*[@class='fonte_titulo_resultado']");
        isTrue(!textoElemento.isEmpty(), "Deve retornar 'Resultado de Consulta'");
        //4 - Clicar no botão Editar de uma das fiscalizações da Grid
        // Esperado: O sistema exibe a tela de edição da fiscalização de Call Box.
        page.botaoEditar();
        textoElemento = page.obterTextoElemento("//*[@class='fonte_atividade']");
        assertEquals("- Alterar Fiscalização", textoElemento);
        page.botaoVoltar();
        page.botaoSimConfirmacao();
        //5 - Clicar no botão Detalhar de uma das fiscalizações da Grid
        //Esperado: O sistema exibe a tela de detalhamento da fiscalização de Call Box.
        page.botaoDetalhar();
        textoElemento = page.obterTextoElemento("//*[@class='fonte_atividade']");
        assertEquals("- Detalhar Fiscalização", textoElemento);
    }

    @Test //"MTM_ID 3804: RVN1 - Verificar alteração dos campos Padrões"
    public void verificarAlteracao_CamposPadrões(){
        //1 - Acessar a tela de Edição da fiscalização e verificar alteração dos campos:
        //      Código da Fsicalização, Tipo, Grupo, Data, Hora, Data de Envio, Hora de Envio,
        //      Concessionária, Lote, Inicial, Mts Final, Mts Inicial, Local, Rodovia (SP), Rodovia,
        //      Sentido, Extensão , Empresa e Responsável Técnico.
        // Esperado: O sistema não deve permitir a edição dos campos padrões descritos no passo.
        preparaCenario();
        page.botaoEditar();
        String[] listaPathsCampos = geraListaCamposNaoEditaveis();
        boolean existeCampoPadraoEditavel = page.verificaCamposNaoEditaveis(listaPathsCampos);
        isTrue(!existeCampoPadraoEditavel, "Não deve existir campos padrões editaveis na tela de alteração");
    }

    @Test //"MTM_ID 3805: RVN2 - Verificar edição dos itens de Call Box"
    public void verificarAlteracao_CamposNaoEditaveis(){
        //1 - Na tela de alteração da fiscalização, na lista de call box, alterar o campo Registro Artesp (RA)
        // Esperado: O sistema não deve permitir a alteração do campo descrito.
        //2 - Na tela de alteração da fiscalização, na lista de call box, alterar o campo Inicial
        // Esperado: O sistema não deve permitir a alteração do campo descrito.
        //3 - Na tela de alteração da fiscalização, na lista de call box, alterar o campo +Mts
        // Esperado: O sistema não deve permitir a alteração do campo descrito.
        //4 - Na tela de alteração da fiscalização, na lista de call box, alterar o campo Status Equipamento
        // Esperado: O sistema não deve permitir a alteração do campo descrito.
        boolean campoEditavel = true;
        preparaCenario();
        page.botaoEditar();
        String[] listaCampos = camposNaoEditaveisQuestionario();
        campoEditavel = page.verificaCamposNaoEditaveis(listaCampos);
        isTrue(!campoEditavel, "Campos RA, Inical, Inicial, +Mts e Status Equipamento não devem ser editáveis.");
    }

    /* //Testes não elegíveis à automação
    @Test //"MTM_ID 3806: Tabela 2 - Verificar campos da seção Call Box - Registro ARTESP (RA)"
    @Ignore // Teste já realizado no script MTM_ID 3805
    public void verificarAlteraco_RAConteudoInvalido(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e preencher o campo alvo com conteúdo inválido.
        //Esperado: O sisteam exibe o campo com informação cadastrada no Tablet, não permitindo sua alteração. Campo Obrigatório.
        //2 - Alterar para branco o campo alvo
        //Esperado: O sistema não deve permitir alteração do campo alvo.
    }

    @Test //"MTM_ID 3814: Tabela 2 - Verificar campos da seção Call Box - Inicial"
    @Ignore // Teste já realizado no script MTM_ID 3805
    public void verificarAlteraco_InicialConteudoInvalido(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e preencher o campo alvo com conteúdo inválido.
        // Esperado: O sisteam exibe o campo com informação cadastrada no Tablet, não permitindo sua alteração. Campo Obrigatório.
        //2 - Alterar para branco o campo alvo
        // Esperado: O sistema não deve permitir alteração do campo alvo.

    }

    @Test //"MTM_ID 3815: Tabela 2 - Verificar campos da seção Call Box - +Mts"
    @Ignore // Teste já realizado no script MTM_ID 3805
    public void verificarAlteraco_MtsConteudoInvalido(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e preencher o campo alvo com conteúdo inválido.
        //Esperado:  O sisteam exibe o campo com informação cadastrada no Tablet, não permitindo sua alteração. Campo Obrigatório.
        //2 - Alterar para branco o campo alvo
        //Esperado: O sistema não deve permitir alteração do campo alvo.

    }

    @Test //"MTM_ID 3816: Tabela 2 - Verificar campos da seção Call Box - Status Equipamento"
    @Ignore // Teste já realizado no script MTM_ID 3805
    public void verificarAlteraco_StatusEquipamentoConteudoInvalido(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e preencher o campo alvo com conteúdo inválido.
        //Esperado: O sisteam exibe o campo com informação cadastrada no Tablet, não permitindo sua alteração. Campo Obrigatório.
        //2 - Alterar para branco o campo alvo
        //Esperado: O sistema não deve permitir alteração do campo alvo.

    }
    *///Testes não elegíveis à automação

    @Test //"MTM_ID 3817: Tabela 2 - Verificar campos da seção Call Box - Dados de Transmissão"
    public void verificarQuestionario_Dados_De_Transmissao(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e verificar o Radio Button já preenchido com informação vinda do Tablet.
        //Esperado: O sistema exibe o campo com a opção selecionada de acordo com o cadastro realizado no tablet.
        //2 - Verificar radio buttons exibidos.
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado e Inexistente
        preparaCenario();
        page.botaoEditar();
        boolean elemento = false;
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_ADEQUADO));
        isTrue(elemento, "Radio Adequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_INADEQUADO));
        isTrue(elemento, "Radio Inadequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_INEXISTENTE));
        isTrue(elemento, "Radio Inexistente deve ser exibido no questionário de CallBox");
        //3 - Alterar para uma das opções não selecionadas (Radio Button)
        //Esperado: O sistema deve permitir a alteração da opção.
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_INADEQUADO));
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3817: Tabela 2 - Verificar campos da seção Call Box - Dados de Transmissão"
    public void verificarQuestionario_Dados_De_Transmissao_Nao_Conformidades(){
        //4 - Verificar fiscalização com a opção Inadequado, pré selecionado do tablet.
        // Esperado: O sistema deve exibir a fiscalização com os CheckBox:
        //  - Sem Sinal
        //  - Não Conecta
        //  - CCO Não Ouve
        //  - Ruído
        //  - Áudio Baixo
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_INADEQUADO));

        String[] naoConformidades = naoConformidades_DadosDeTransmissao();
        for (int i = 0; i < naoConformidades.length; i ++){
            isTrue(page.verificaElementoExiste(By.xpath(naoConformidades[i])),"Não conformidades devem ser exibidas quando selecionada a opção Inadequado do questionário");
        }
        //5 - Não selecionar nenhum checkbox e salvar a alteração.
        //Esperado: As opções de checkbox não são obrigatórias
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
        //6 - Alterar a seleção de um checkbox já preenchido no tablet.
        //Esperado: O sistema permite a alteração de seleção dos checkbox.
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        page.botaoFiltrar();
        page.botaoEditar();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_INADEQUADO));
        esperaJanelaCarregar(1000);
        for (int i = 0; i < naoConformidades.length; i ++){
            page.selecionarNaoConformidades(By.xpath(naoConformidades[i]));
        }
        /*
        page.selecionarNaoConformidades(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_SEM_SINAL));
        page.selecionarNaoConformidades(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_NAO_CONECTA));
        page.selecionarNaoConformidades(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_CCO_NAO_OUVE));
        page.selecionarNaoConformidades(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_RUIDO));
        page.selecionarNaoConformidades(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_AUDIO_BAIXO));
         */
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3817: Tabela 2 - Verificar campos da seção Call Box - Dados de Transmissão"
    public void verificarQuestionario_Dados_De_Transmissao_Inexistente(){
        //7 - Alterar uma fiscalização com o radio Button Inadequado pré selecionado no tablet, para Adequado ou Inexistente.
        //Esperado: O sistema não deve exibir os Checkbox referente à opção Inadequado.
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_INEXISTENTE));

        String[] naoConformidades = naoConformidades_DadosDeTransmissao();
        for (int i = 0; i < naoConformidades.length; i++){
            esperaJanelaCarregar(3000);
            boolean elemento = page.verificaElementoExiste(By.xpath(naoConformidades[i]));
            isTrue(!elemento, "Itens de não conformidade não devem ser exibidos com a opção Inexistente");
        }
    }

    @Test //"MTM_ID 3818: Tabela 2 - Verificar campos da seção Call Box - Dados de Conservação"
    public void verificarQuestionario_Dados_De_Conservacao(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e verificar o Radio Button já preenchido com informação vinda do Tablet.
        //Esperado: O sistema exibe o campo com a opção selecionada de acordo com o cadastro realizado no tablet.
        //2 - Verificar radio buttons exibidos.
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado e Inexistente
        preparaCenario();
        page.botaoEditar();
        boolean elemento = false;
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_ADEQUADO));
        isTrue(elemento, "Radio Adequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_INADEQUADO));
        isTrue(elemento, "Radio Inadequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_INEXISTENTE));
        isTrue(elemento, "Radio Inexistente deve ser exibido no questionário de CallBox");
        //3 - Alterar para uma das opções não selecionadas (Radio Button)
        //Esperado: O sistema deve permitir a alteração da opção.
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_INADEQUADO));
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3818: Tabela 2 - Verificar campos da seção Call Box - Dados de Conservação"
    public void verificarQuestionario_Dados_De_Conservacao_Nao_Conformidades(){
        //4 - Verificar fiscalização com a opção Inadequado, pré selecionado do tablet.
        //Esperado: O sistema deve exibir a fiscalização com os CheckBox:
        //      - Ausência de Gabinete
        //      - Totem Sujo
        //      - Botão de Chamada
        //      - Elementos de Fixação
        //      - Telas e Tampas Danificadas ou Quebradas
        //      - Pontos de Ferrugem
        //      - Falta de Aparelho
        //      - Pichados
        //      - Falta de Drenagem no Local
        //      - Entorno Sujo
        //      - Mal Conservado
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_INADEQUADO));

        String[] naoConformidades = naoConformidades_DadosDeConservacao();
        for (int i = 0; i < naoConformidades.length; i ++){
            isTrue(page.verificaElementoExiste(By.xpath(naoConformidades[i])),"Não conformidades devem ser exibidas quando selecionada a opção Inadequado do questionário");
        }
        //5 - Não selecionar nenhum checkbox e salvar a alteração.
        //Esperado: As opções de checkbox não são obrigatórias
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
        //6 - Alterar a seleção de um checkbox já preenchido no tablet.
        //Esperado: O sistema permite a alteração de seleção dos checkbox.
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        page.botaoFiltrar();
        page.botaoEditar();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_INADEQUADO));
        esperaJanelaCarregar(1000);
        for (int i = 0; i < naoConformidades.length; i ++){
            page.selecionarNaoConformidades(By.xpath(naoConformidades[i]));
        }
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3818: Tabela 2 - Verificar campos da seção Call Box - Dados de Conservação"
    public void verificarQuestionario_Dados_De_Conservacao_Inexistente(){
        //7 - Alterar uma fiscalização com o radio Button Inadequado pré selecionado no tablet, para Adequado ou Inexistente.
        //Esperado: O sistema não deve exibir os Checkbox referente à opção Inadequado.
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_INEXISTENTE));
        String[] naoConformidades = naoConformidades_DadosDeConservacao();

        for (int i = 0; i < naoConformidades.length; i++){
            esperaJanelaCarregar(3000);
            boolean elemento = page.verificaElementoExiste(By.xpath(naoConformidades[i]));
            isTrue(!elemento, "Itens de não conformidade não devem ser exibidos com a opção Inexistente");
        }

    }

    @Test //"MTM_ID 3819: Tabela 2 - Verificar campos da seção Call Box - Dados de Segurança"
    public void verificarQuestionario_Dados_De_Seguranca(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e verificar o Radio Button já preenchido com informação vinda do Tablet.
        //Esperado: O sistema exibe o campo com a opção selecionada de acordo com o cadastro realizado no tablet.
        //2 - Verificar radio buttons exibidos.
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado e Inexistente
        preparaCenario();
        page.botaoEditar();
        boolean elemento = false;
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_ADEQUADO));
        isTrue(elemento, "Radio Adequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_INADEQUADO));
        isTrue(elemento, "Radio Inadequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_INEXISTENTE));
        isTrue(elemento, "Radio Inexistente deve ser exibido no questionário de CallBox");
        //3 - Alterar para uma das opções não selecionadas (Radio Button)
        //Esperado: O sistema deve permitir a alteração da opção.
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_INADEQUADO));
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3819: Tabela 2 - Verificar campos da seção Call Box - Dados de Segurança"
    public void verificarQuestionario_Dados_De_Seguranca_Nao_Conformidades(){
        //4 - Verificar fiscalização com a opção Inadequado, pré selecionado do tablet.
        //Esperado: O sistema deve exibir a fiscalização com os CheckBox:
        //  - Barreira e Guarda Corpos de Proteção Ausentes
        //  - Mau Estado de Conservação
        //  - Danificados
        //  - Ausência de Sinalização de Segurança
        //  - Ausência de Defensa Metálica ou Barreira
        //  - Ausência de Muro de Arrimo
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_INADEQUADO));

        String[] naoConformidades = naoConformidades_DadosDeSeguranca();
        for (int i = 0; i < naoConformidades.length; i ++){
            isTrue(page.verificaElementoExiste(By.xpath(naoConformidades[i])),"Não conformidades devem ser exibidas quando selecionada a opção Inadequado do questionário");
        }
        //5 - Não selecionar nenhum checkbox e salvar a alteração.
        //Esperado: As opções de checkbox não são obrigatórias
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
        //6 - Alterar a seleção de um checkbox já preenchido no tablet.
        //Esperado: O sistema permite a alteração de seleção dos checkbox.
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        page.botaoFiltrar();
        page.botaoEditar();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_INADEQUADO));
        esperaJanelaCarregar(1000);
        for (int i = 0; i < naoConformidades.length; i ++){
            page.selecionarNaoConformidades(By.xpath(naoConformidades[i]));
        }
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3819: Tabela 2 - Verificar campos da seção Call Box - Dados de Segurança"
    public void verificarQuestionario_Dados_De_Seguranca_Inexistente(){
        //7 - Alterar uma fiscalização com o radio Button Inadequado pré selecionado no tablet, para Adequado ou Inexistente.
        //Esperado: O sistema não deve exibir os Checkbox referente à opção Inadequado.
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_INEXISTENTE));
        String[] naoConformidades = naoConformidades_DadosDeConservacao();

        for (int i = 0; i < naoConformidades.length; i++){
            esperaJanelaCarregar(3000);
            boolean elemento = page.verificaElementoExiste(By.xpath(naoConformidades[i]));
            isTrue(!elemento, "Itens de não conformidade não devem ser exibidos com a opção Inexistente");
        }
    }

    @Test //"MTM_ID 3820: Tabela 2 - Verificar campos da seção Call Box - Dados de Instalação"
    public void verificarQuestionario_Dados_De_Instalacao(){
        //1 - Acessar a Tela de Edição da fiscalização de Call Box e verificar o Radio Button já preenchido com informação vinda do Tablet.
        //Esperado: O sistema exibe o campo com a opção selecionada de acordo com o cadastro realizado no tablet.
        //2 - Verificar radio buttons exibidos.
        //Esperado: O sistema deve exibir as opções Adequado, Inadequado e Inexistente
        preparaCenario();
        page.botaoEditar();
        boolean elemento = false;
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_ADEQUADO));
        isTrue(elemento, "Radio Adequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_INADEQUADO));
        isTrue(elemento, "Radio Inadequado deve ser exibido no questionário de CallBox");
        elemento = false;
        elemento = page.verificaElementoExiste(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_INEXISTENTE));
        isTrue(elemento, "Radio Inexistente deve ser exibido no questionário de CallBox");
        //3 - Alterar para uma das opções não selecionadas (Radio Button)
        //Esperado: O sistema deve permitir a alteração da opção.
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_INADEQUADO));
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3820: Tabela 2 - Verificar campos da seção Call Box - Dados de Instalação"
    public void verificarQuestionario_Dados_De_Instalacao_Nao_Conformidades(){
        //4 - Verificar fiscalização com a opção Inadequado, pré selecionado do tablet.
        //Esperaodo: O sistema deve exibir a fiscalização com os CheckBox:
        //      - Inativo
        //      - Desativado
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_INADEQUADO));

        String[] naoConformidades = naoConformidades_DadosDeInstalacao();
        for (int i = 0; i < naoConformidades.length; i ++){
            isTrue(page.verificaElementoExiste(By.xpath(naoConformidades[i])),"Não conformidades devem ser exibidas quando selecionada a opção Inadequado do questionário");
        }
        //5 - Não selecionar nenhum checkbox e salvar a alteração.
        //Esperado: As opções de checkbox não são obrigatórias
        page.botaoSalvar();
        String msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
        //6 - Alterar a seleção de um checkbox já preenchido no tablet.
        //Esperado: O sistema permite a alteração de seleção dos checkbox.
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        page.botaoFiltrar();
        page.botaoEditar();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_INADEQUADO));
        esperaJanelaCarregar(1000);
        for (int i = 0; i < naoConformidades.length; i ++){
            page.selecionarNaoConformidades(By.xpath(naoConformidades[i]));
        }
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO));
        page.botaoSalvar();
        msgSucesso = page.obterTextoElemento("//*[@class='msgINFO']");
        assertEquals("Registro salvo com sucesso.", msgSucesso);
    }

    @Test //"MTM_ID 3820: Tabela 2 - Verificar campos da seção Call Box - Dados de Instalação"
    public void verificarQuestionario_Dados_De_Instalacao_Inexistente(){
        //7 - Alterar uma fiscalização com o radio Button Inadequado pré selecionado no tablet, para Adequado ou Inexistente.
        //Esperado: O sistema não deve exibir os Checkbox referente à opção Inadequado.
        preparaCenario();
        page.botaoEditar();
        preparaFiscalizacaoParaTeste();
        page.rolarTelaAteElemento(By.xpath(PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA));
        page.selecionarRadioButton(By.xpath(Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_INEXISTENTE));
        String[] naoConformidades = naoConformidades_DadosDeInstalacao();

        for (int i = 0; i < naoConformidades.length; i++){
            esperaJanelaCarregar(3000);
            boolean elemento = page.verificaElementoExiste(By.xpath(naoConformidades[i]));
            isTrue(!elemento, "Itens de não conformidade não devem ser exibidos com a opção Inexistente");
        }
    }


    private String[] camposNaoEditaveisQuestionario() {
        String[] paths = new String[4];
        paths[0] = PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA;
        paths[1] = PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_KM_INICIAL;
        paths[2] = PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_KM_INICIAL_METROS;
        paths[3] = PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_STATUS_EQUIPAMENTO;
        return paths;
    }

    private String[] geraListaCamposNaoEditaveis() {
        String[] paths = new String[24];
        paths[0]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_CODIGO_FISCALIZACAO;
        paths[1]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_TIPO;
        paths[2]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_GRUPO;
        paths[3]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_DATA;
        paths[4]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_HORA;
        paths[5]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_DATA_ENVIO;
        paths[6]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_HORA_ENVIO;
        paths[7]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_CONCESSIONARIA;
        paths[8]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_LOTE;
        paths[9]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_INICIAL;
        paths[10]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_INICAL_METROS;
        paths[11]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_FINAL;
        paths[12]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_FINAL_METROS;
        paths[13]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_LOCAL;
        paths[14]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_SP;
        paths[15]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA;
        paths[16]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_INICAL;
        paths[17]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_INICAL_METROS;
        paths[18]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_FINAL_METROS;
        paths[19]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_FINAL;
        paths[20]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_SENTIDO;
        paths[21]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_EXTENSAO;
        paths[22]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_EAF;
        paths[23]= PathsAssistant.WEB_CAMPOS_NAO_EDITAVEIS_ID_RESPONSAVEL_TECNICO;
        return paths;
    }

    private String[] naoConformidades_DadosDeConservacao(){
        String[] naoConformidades = new String[11];
        naoConformidades[0] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_AUSENCIA_DE_GABINETE;
        naoConformidades[1] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_TOTEM_SUJO;
        naoConformidades[2] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_BOTAO_DE_CHAMADA;
        naoConformidades[3] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_ELEMENTOS_DE_FIXACAO;
        naoConformidades[4] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_TELAS_E_TAMPAS_DANIFICADAS_QUEBRADAS;
        naoConformidades[5] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_PONTOS_DE_FERRUGEM;
        naoConformidades[6] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_FALTA_DE_APARELHO;
        naoConformidades[7] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_PICHADOS;
        naoConformidades[8] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_FALTA_DE_DRENAGEM_NO_LOCAL;
        naoConformidades[9] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_ENTORNO_SUJO;
        naoConformidades[10] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_MAL_CONSERVADO;
        return naoConformidades;
    }

    private String[] naoConformidades_DadosDeTransmissao(){
        String[] naoConformidades = new String[5];
        naoConformidades[0] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_SEM_SINAL;
        naoConformidades[1] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_NAO_CONECTA;
        naoConformidades[2] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_CCO_NAO_OUVE;
        naoConformidades[3] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_RUIDO;
        naoConformidades[4] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_TRANSMISSAO_AUDIO_BAIXO;
        return naoConformidades;
    }

    private String[] naoConformidades_DadosDeSeguranca() {
        String[] naoConformidades = new String[6];
        naoConformidades[0] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_BARREIRA_E_GUARDA_COPOS;
        naoConformidades[1] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_MAU_ESTADO_DE_CONSERVACAO;
        naoConformidades[2] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_DANIFICADOS;
        naoConformidades[3] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_AUSENCIA_DE_SINALIZACAO_SEGURANCA;
        naoConformidades[4] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_AUSENCIA_DE_DENFENSA_METALICA;
        naoConformidades[5] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_AUSENCIA_DE_MURO_DE_ARRIMO;
        return naoConformidades;
    }

    private String[] naoConformidades_DadosDeInstalacao(){
        String[] naoConformidades = new String[2];
        naoConformidades[0] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_INATIVO;
        naoConformidades[1] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_DESATIVADO;
        return naoConformidades;
    }

    private void preparaCenario(){
        page.logarWeb();
        modulosWebPage.clicarBotaoFiscalizacaoEnviadas();
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        page.botaoFiltrar();
    }

    private void preparaFiscalizacaoParaTeste(){
        String[] listaDadosAdequados = new String[4];
        listaDadosAdequados[0] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DA_TRANSMISSAO_ADEQUADO;
        listaDadosAdequados[1] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_CONSERVACAO_ADEQUADO;
        listaDadosAdequados[2] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_SEGURANCA_ADEQUADO;
        listaDadosAdequados[3] = Questionario_Operacao_CallBoxAssistant.WEB_XPATH_DADOS_DE_INSTALACAO_ADEQUADO;
        for (int i = 0; i < listaDadosAdequados.length; i++) {
            page.selecionarRadioButton(By.xpath(listaDadosAdequados[i]));
        }
        page.botaoSalvar();
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        page.botaoFiltrar();
        page.botaoEditar();
    }


}
