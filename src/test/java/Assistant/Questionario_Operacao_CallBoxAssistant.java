package Assistant;

public class Questionario_Operacao_CallBoxAssistant {

    /*
    *
    * QUESTIONÁRIO TABLET - CALL BOX
    *
    * */
    public static final String DADOS_DA_TRANSMISSAO = "br.gov.sp.artesp.sisf.mobile:id/item_questionario1";
    public static final String DADOS_TRANSMISSAO_SEM_SINAL = "Sem Sinal";
    public static final String DADOS_TRANSMISSAO_NAO_CONECTA = "Não Conecta";
    public static final String DADOS_TRANSMISSAO_CCO_NAO_OUVE= "CCO Não ouve";
    public static final String DADOS_TRANSMISSAO_RUIDO = "Ruído";
    public static final String DADOS_TRANSMISSAO_AUDIO_BAIXO = "Aúdio Baixo";

    public static final String DADOS_DE_CONSERVACAO = "br.gov.sp.artesp.sisf.mobile:id/item_questionario2";
    public static final String DADOS_DE_CONSERVACAO_AUSENCIA_DE_GABINETE = "Ausência de Gabinete";
    public static final String DADOS_DE_CONSERVACAO_TOTEM_SUJO = "Totem Sujo ";
    public static final String DADOS_DE_CONSERVACAO_BOTAO_DE_CHAMADA= "Botão de Chamada";
    public static final String DADOS_DE_CONSERVACAO_ELEMENTOS_DE_FIXACAO= "Elementos de Fixação";
    public static final String DADOS_DE_CONSERVACAO_TELAS_E_TAMPAS_DANIFICADAS_QUEBRADAS= "Telas e Tampas Danificadas ou Quebradas";
    public static final String DADOS_DE_CONSERVACAO_PONTOS_DE_FERRUGEM= "Pontos de Ferrugem";
    public static final String DADOS_DE_CONSERVACAO_FALTA_DE_APARELHO= "Falta de Aparelho";
    public static final String DADOS_DE_CONSERVACAO_PICHADOS= "Pichados";
    public static final String DADOS_DE_CONSERVACAO_FALTA_DE_DRENAGEM_NO_LOCAL= "Falta de Drenagem no Local";
    public static final String DADOS_DE_CONSERVACAO_ENTORNO_SUJO= "Entorno Sujo";
    public static final String DADOS_DE_CONSERVACAO_MAL_CONSERVADO= "Mal Conservado";

    public static final String DADOS_DE_SEGURANCA = "br.gov.sp.artesp.sisf.mobile:id/item_questionario3";
    public static final String DADOS_DE_SEGURANCA_BARREIRA_E_GUARDA_COPOS="Barreira e Guarda Corpos de Proteção Ausentes";
    public static final String DADOS_DE_SEGURANCA_MAU_ESTADO_DE_CONSERVACAO="Mau Estado de Conservação";
    public static final String DADOS_DE_SEGURANCA_DANIFICADOS="Danificados";
    public static final String DADOS_DE_SEGURANCA_AUSENCIA_DE_SINALIZACAO_SEGURANCA="Ausência de Sinalização de Segurança";
    public static final String DADOS_DE_SEGURANCA_AUSENCIA_DE_DENFENSA_METALICA="Ausência de Defensa Metálica ou Barreira ";
    public static final String DADOS_DE_SEGURANCA_AUSENCIA_DE_MURO_DE_ARRIMO="Ausência de Muro de Arrimo";

    public static final String DADOS_DE_INSTALACAO = "br.gov.sp.artesp.sisf.mobile:id/item_questionario4";
    public static final String DADOS_DE_INSTALACAO_INATIVO = "Inativo";
    public static final String DADOS_DE_INSTALACAO_DESATIVADO = "Desativado";


    /*
    *
    * QUESTIONÁRIO WEB - CALL BOX
    *
    * */

    public static final String WEB_XPATH_DADOS_DA_TRANSMISSAO_ADEQUADO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosId')]/following-sibling::label[text()='Adequado']";
    public static final String WEB_XPATH_DADOS_DA_TRANSMISSAO_INADEQUADO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosId')]/following-sibling::label[text()='Inadequado']";
    public static final String WEB_XPATH_DADOS_DA_TRANSMISSAO_INEXISTENTE = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosId')]/following-sibling::label[text()='Inexistente']";

    public static final String WEB_XPATH_DADOS_TRANSMISSAO_SEM_SINAL = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosCheckboxId')]//following-sibling::label[text()='Sem Sinal']";
    public static final String WEB_XPATH_DADOS_TRANSMISSAO_NAO_CONECTA = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosCheckboxId')]//following-sibling::label[text()='Não Conecta']";
    public static final String WEB_XPATH_DADOS_TRANSMISSAO_CCO_NAO_OUVE= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosCheckboxId')]//following-sibling::label[text()='CCO Não ouve']";
    public static final String WEB_XPATH_DADOS_TRANSMISSAO_RUIDO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosCheckboxId')]//following-sibling::label[text()='Ruído']";
    public static final String WEB_XPATH_DADOS_TRANSMISSAO_AUDIO_BAIXO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item1:dadosCheckboxId')]//following-sibling::label[text()='Aúdio Baixo']";

    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_ADEQUADO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosId')]/following-sibling::label[text()='Adequado']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_INADEQUADO  = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosId')]/following-sibling::label[text()='Inadequado']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_INEXISTENTE = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosId')]/following-sibling::label[text()='Inexistente']";

    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_AUSENCIA_DE_GABINETE = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Ausência de Gabinete']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_TOTEM_SUJO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Totem Sujo ']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_BOTAO_DE_CHAMADA= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Botão de Chamada']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_ELEMENTOS_DE_FIXACAO= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Elementos de Fixação']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_TELAS_E_TAMPAS_DANIFICADAS_QUEBRADAS= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Telas e Tampas Danificadas ou Quebradas']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_PONTOS_DE_FERRUGEM= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Pontos de Ferrugem']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_FALTA_DE_APARELHO= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Falta de Aparelho']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_PICHADOS= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Pichados']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_FALTA_DE_DRENAGEM_NO_LOCAL= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Falta de Drenagem no Local']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_ENTORNO_SUJO= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Entorno Sujo']";
    public static final String WEB_XPATH_DADOS_DE_CONSERVACAO_MAL_CONSERVADO= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item2:dadosCheckboxId')]//following-sibling::label[text()='Mal Conservado']";

    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_ADEQUADO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosId')]/following-sibling::label[text()='Adequado']";
    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_INADEQUADO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosId')]/following-sibling::label[text()='Inadequado']";
    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_INEXISTENTE = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosId')]/following-sibling::label[text()='Inexistente']";

    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_BARREIRA_E_GUARDA_COPOS="//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosCheckboxId')]//following-sibling::label[text()='Barreira e Guarda Corpos de Proteção Ausentes']";
    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_MAU_ESTADO_DE_CONSERVACAO="//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosCheckboxId')]//following-sibling::label[text()='Mau Estado de Conservação']";
    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_DANIFICADOS="//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosCheckboxId')]//following-sibling::label[text()='Danificados']";
    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_AUSENCIA_DE_SINALIZACAO_SEGURANCA="//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosCheckboxId')]//following-sibling::label[text()='Ausência de Sinalização de Segurança']";
    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_AUSENCIA_DE_DENFENSA_METALICA="//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosCheckboxId')]//following-sibling::label[text()='Ausência de Defensa Metálica ou Barreira ']";
    public static final String WEB_XPATH_DADOS_DE_SEGURANCA_AUSENCIA_DE_MURO_DE_ARRIMO="//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item3:dadosCheckboxId')]//following-sibling::label[text()='Ausência de Muro de Arrimo']";

    public static final String WEB_XPATH_DADOS_DE_INSTALACAO_ADEQUADO= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item4:dadosId')]/following-sibling::label[text()='Adequado']";
    public static final String WEB_XPATH_DADOS_DE_INSTALACAO_INADEQUADO="//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item4:dadosId')]/following-sibling::label[text()='Inadequado']";
    public static final String WEB_XPATH_DADOS_DE_INSTALACAO_INEXISTENTE= "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item4:dadosId')]/following-sibling::label[text()='Inexistente']";

    public static final String WEB_XPATH_DADOS_DE_INSTALACAO_INATIVO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item4:dadosCheckboxId')]//following-sibling::label[text()='Inativo']";
    public static final String WEB_XPATH_DADOS_DE_INSTALACAO_DESATIVADO = "//input[contains(@name, 'listaCallboxPanel:container:containerLista:forms:1:callboxPanel:item4:dadosCheckboxId')]//following-sibling::label[text()='Desativado']";
}
