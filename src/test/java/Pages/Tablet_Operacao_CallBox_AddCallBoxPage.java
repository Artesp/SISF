package Pages;

import Assistant.PathsAssistant;
import Assistant.Questionario_Operacao_CallBoxAssistant;
import Core.BasePage;

import static Core.DriverFactory.getDriver;

public class Tablet_Operacao_CallBox_AddCallBoxPage extends BasePage {

    public void clickBotaoLupa(){
        getDriver().findElementById(PathsAssistant.ID_CALLBOX_BOTAO_LUPA_RA).click();
    }

    public void clickOK_AdicionaCallBox(){
        getDriver().findElementById(PathsAssistant.ID_CALLBOX_TELA_ADICIONA_CALLBOX_BOTAO_OK).click();
    }

    public void selecionaEquipamentoRA(String RA){
        getDriver().findElementById(PathsAssistant.ID_CALLBOX_LISTA_EQUIPAMENTOS).sendKeys(RA);
        getDriver().findElementById(PathsAssistant.ID_CALLBOX_CHECKBOX_RA).click();
        getDriver().findElementById(PathsAssistant.ID_CALLBOX_TELA_EQUIPAMENTOS_BOTAO_OK).click();
    }

    public void preencherSecaoCallBox(){
        respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DA_TRANSMISSAO, "Adequado");
        respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_CONSERVACAO, "Adequado");
        respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_SEGURANCA, "Adequado");
        respondeQuestionario(Questionario_Operacao_CallBoxAssistant.DADOS_DE_INSTALACAO, "Adequado");
    }

    public void respondeQuestionario(String questao, String opcao){
        String path = "//*[@resource-id='"+questao+"']" +
                "/android.widget.LinearLayout[@index='0']" +
                "/android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:id/radio']" +
                "/android.widget.LinearLayout[@index='0']" +
                "/android.widget.RadioGroup[@index='2']" +
                "/*[@text='"+opcao+"']";
        getDriver().findElementByXPath(path).click();
    }

}
