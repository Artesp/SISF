package Assistant;

public class PathsAssistant {


    //Seleciona Grupo
    public static final String XPATH_SELECIONA_GRUPO_CLICK = "//android.widget.LinearLayout[@index='2']/android.widget.LinearLayout[@index='0']/android.widget.Spinner[@index='1']";

    //Conservação Emergencial
    public static final String XPATH_GRUPO_CONSERVACAO_EMERGENCIAL = "//android.widget.CheckedTextView[@text='Conservação Emergencial']";
    public static final String XPATH_DATA_PREVISTA_INICIO = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:id/vnt_data_inicio']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='0']/*[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/dt_button']";
    public static final String XPATH_DATA_PREVISTA_TERMINO = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:id/vnt_data_termino']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='0']/*[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/dt_button']";
    public static final String XPATH_DATA_EFETIVA_INICIO = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:id/vnt_data_efetiva']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='0']/*[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/dt_button']";

    public static final String ID_CAMPO_ENVENTO = "br.gov.sp.artesp.sisf.mobile:comp/txt_editText";
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





}
