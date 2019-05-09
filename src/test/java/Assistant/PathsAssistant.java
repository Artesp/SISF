package Assistant;

public class PathsAssistant {

    //Seleciona Grupo
    public static final String XPATH_SELECIONA_GRUPO_CLICK = "//android.widget.LinearLayout[@index='2']/android.widget.LinearLayout[@index='0']/android.widget.Spinner[@index='1']";

    //WEB - Botao Salvar Alteração de Fiscalização
    public static final String WEB_XPATH_BOTAO_SALVAR_FISCALIZACAO = "//button[@alt='Salvar']";

    //Conservação Emergencial
    public static final String XPATH_GRUPO_CONSERVACAO_EMERGENCIAL = "//android.widget.CheckedTextView[@text='Conservação Emergencial']";
    public static final String XPATH_DATA_PREVISTA_INICIO = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:id/vnt_data_inicio']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='0']/*[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/dt_button']";
    public static final String XPATH_DATA_PREVISTA_TERMINO = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:id/vnt_data_termino']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='0']/*[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/dt_button']";
    public static final String XPATH_DATA_EFETIVA_INICIO = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:id/vnt_data_efetiva']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='0']/*[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/dt_button']";

    public static final String ID_CAMPO_EVENTO = "br.gov.sp.artesp.sisf.mobile:comp/txt_editText";
    public static final String ID_BOTAO_REC_MOBILIZADOS = "br.gov.sp.artesp.sisf.mobile:comp/tgl_button";
    public static final String ID_CAMPO_REC_MOBILIZADOS = "br.gov.sp.artesp.sisf.mobile:comp/txtspl_editText";
    public static final String ID_BOTAO_SINALIZACAO_LOCAL = "br.gov.sp.artesp.sisf.mobile:comp/tgl_button";
    public static final String ID_CAMPO_SINALIZACAO_LOCAL = "br.gov.sp.artesp.sisf.mobile:comp/txtspl_editText";
    public static final String ID_CAMPO_ANALISE_PRELIMINAR = "br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText";
    public static final String ID_CAMPO_PREV_MED_CORRETIVAS= "br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText";

    //Conservação de Rotina
    public static final String XPATH_GRUPO_CONSERVACAO_ROTINA = "//android.widget.CheckedTextView[@text='Conservação de Rotina']";

    //Operação - WIFI
    public static final String ID_WIFI_INTENSIDADE_DE_SINAL = "br.gov.sp.artesp.sisf.mobile:id/intensidade_sinal";
    public static final String ID_WIFI_DURACAO_CHAMADA = "br.gov.sp.artesp.sisf.mobile:id/duracao_chamada";

    //Operação - Call Box
    public static final String XPATH_GRUPO_CALLBOX = "//*[@text='Call Box']";
    public static final String ID_CALLBOX_BOTAO_ADD_CALLBOX = "br.gov.sp.artesp.sisf.mobile:id/lstcbn_btn_add";
    public static final String ID_CALLBOX_BOTAO_LUPA_RA = "br.gov.sp.artesp.sisf.mobile:id/lstcbn_btn_search";
    public static final String ID_CALLBOX_LISTA_EQUIPAMENTOS = "br.gov.sp.artesp.sisf.mobile:id/listaEqtos";
    public static final String ID_CALLBOX_CHECKBOX_RA = "br.gov.sp.artesp.sisf.mobile:id/selecionado";
    public static final String ID_CALLBOX_TELA_EQUIPAMENTOS_BOTAO_OK = "android:id/button1";
    public static final String ID_CALLBOX_TELA_ADICIONA_CALLBOX_BOTAO_OK = "android:id/button1";

    //Mensagem de sucesso: 'Fiscalizacao ID: 1 (<Código Web>) Enviado com sucesso!'
    public static final String XPATH_MSG_ENVIO_COM_SUCESSO = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:id/error_msg_list']/android.widget.TextView[@index='0']";

    //Grupo da fiscalização na grid depois de salvar.
    public static final String ID_GRUPO_FISCALIZACAO = "br.gov.sp.artesp.sisf.mobile:id/tvGrupoFiscalizacao";
    public static final String ID_RETORNO_PREENCHIDO = "br.gov.sp.artesp.sisf.mobile:id/tvRegistro";
    public static final String ID_CODIGO_WEB = "br.gov.sp.artesp.sisf.mobile:id/tvCodControle";

    /*
     *
     *--XPATHS WEB ELEMENTS SISF--
     *
    */

    //--Operação: CALL BOX--
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_CODIGO_FISCALIZACAO = "//input[contains(@id, 'codigoFiscalizacao')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_TIPO = "//input[contains(@id, 'tipo')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_GRUPO = "//input[contains(@id, 'grupo')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_DATA = "//input[contains(@id, 'data')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_HORA = "//input[contains(@id, 'hora')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_DATA_ENVIO = "//input[contains(@id, 'dataEnvio')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_HORA_ENVIO = "//input[contains(@id, 'horaEnvio')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_CONCESSIONARIA = "//input[contains(@id, 'textoConcessionaria')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_LOTE = "//input[contains(@id, 'lote')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_INICIAL = "//input[contains(@id, 'inicial')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_INICAL_METROS="//input[contains(@id, 'inicialMetros')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_FINAL = "//input[contains(@id, 'campoFinal')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_FINAL_METROS = "//input[contains(@id, 'finalMetros')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_LOCAL = "//input[contains(@id, 'local')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_EAF = "//input[contains(@id, 'EAF')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_ID_RESPONSAVEL_TECNICO = "//input[contains(@id, 'responsavelTecnico')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_SP = "//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:textoRodoviaSP:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA = "//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:textoRodovia:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_INICAL = "//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:inicial:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_INICAL_METROS = "//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:inicialMetros:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_FINAL_METROS = "//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:finalMetros:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_FINAL = "//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:campoFinal:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_SENTIDO = "//select[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:sentido:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_RODOVIA_EXTENSAO = "//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:extensao:campoEntrada')]";

    //QUESTIONÁRIO: RA, Km inicial, Km inicial mais metros e Status equipamento.
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_RA = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:localizacaoComponentePanel:ra:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_KM_INICIAL = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:localizacaoComponentePanel:kmInicial:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_KM_INICIAL_METROS = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:localizacaoComponentePanel:kmInicialMaisMetros:campoEntrada')]";
    public static final String WEB_CAMPOS_NAO_EDITAVEIS_NAME_QUESTIONARIO_STATUS_EQUIPAMENTO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:localizacaoComponentePanel:statusEquipamento:campoEntrada')]";







}
