package Pages;

import static Assistant.Questionario_Operacao_WiFiAssistant.*;

import Assistant.ObjetosParaFiscalizacao;
import Assistant.Questionario_Operacao_WiFiAssistant;
import Core.BasePage;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebElement;

import static Core.DriverFactory.getDriver;

public class Tablet_Operacao_WiFi_AddMedicaoPage extends BasePage {

    private Tablet_Operacao_WiFiPage pageWiFi = new Tablet_Operacao_WiFiPage();

    public void preencherWiFiEmLote(int qtdMed){

        int contador = 0;
        int incrementaKM = 14;

        do {
            pageWiFi.clicarBotaoAddMedicao();
            clicarBotaoIntensidadeSinal();
            clicarBotaoCronometro();
            clicarBotaoFinalizaCronometro();
            preencheKmInicial(String.valueOf(incrementaKM));
            preencheMetros("000");
            //selecionaSentido("N - Norte");
            respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO, "Sim");
            respondeQuestionario(Questionario_Operacao_WiFiAssistant.CONFIRMACAO_LOCAL_FISCAL, "Sim");
            respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO, "Sim");
            respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Bom");
            respondeQuestionario(Questionario_Operacao_WiFiAssistant.ENCERRAMENTO_DE_CHAMADA, "Voluntariamente");
            clicarBotaoOK();
            System.out.println(contador + " KM: " + incrementaKM + " 000 - Hora: " + retornaHora());
            contador++;
            incrementaKM += 3;
        }while (contador < qtdMed);
    }

    public void preencherWiFi(){
        pageWiFi.clicarBotaoAddMedicao();
        clicarBotaoIntensidadeSinal();
        clicarBotaoCronometro();
        clicarBotaoFinalizaCronometro();
        preencheKmInicial("13");
        preencheMetros("500");
        //selecionaSentido("N - Norte");
        respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_CONEXAO, "Sim");
        respondeQuestionario(Questionario_Operacao_WiFiAssistant.CONFIRMACAO_LOCAL_FISCAL, "Sim");
        respondeQuestionario(Questionario_Operacao_WiFiAssistant.HOUVE_ATENDIMENTO, "Sim");
        respondeQuestionario(Questionario_Operacao_WiFiAssistant.QUALIDADE_DA_COMUNICACAO, "Bom");
        respondeQuestionario(Questionario_Operacao_WiFiAssistant.ENCERRAMENTO_DE_CHAMADA, "Voluntariamente");
        clicarBotaoOK();
    }

    public void preencherWiFi_SemQuestionario(){
        pageWiFi.clicarBotaoAddMedicao();
        clicarBotaoIntensidadeSinal();
        clicarBotaoCronometro();
        clicarBotaoFinalizaCronometro();
        preencheKmInicial("13");
        preencheMetros("500");
        //selecionaSentido("N - Norte");
    }

    public void clicarBotaoIntensidadeSinal(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_intensidade_sinal").click();
        //getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_intensidade_sinal").click();
    }

    public void clicarBotaoCronometro(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_cronometro").click();
        //getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_cronometro").click();
    }

    public void clicarBotaoFinalizaCronometro(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/crono_finaliza_btn").click();
    }

    public void clicarBotaoOK(){
        getDriver().findElementById("android:id/button1").click();
    }

    public void clicarCheckBoxQuestionario(WebElement elemento){
        elemento.click();
    }

    public void preencheKmInicial(String valor){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/kmInicial").sendKeys(valor);
    }

    public void preencheMetros(String valor){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/mtsInicial").sendKeys(valor);
    }

    public void selecionaSentido(String sentido){
        //getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/spnspl_spinner").click();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/spnspl_spinner").click();
        getDriver().findElementByXPath("//*[@text='"+ sentido +"']").click();
    }

    public void respondeQuestionario(String questao, String opcao){
        String path = "//android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/qstfrm_content']" +
                "/android.widget.LinearLayout[@index='"+questao+"']" +
                "/android.widget.LinearLayout[@index='0']" +
                "/android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:id/itmqstfrm_layout']" +
                "/android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:id/itmqstfrm_radio']" +
                "/android.widget.LinearLayout[@index='0']" +
                "/android.widget.RadioGroup[@index='1']" +
                "/android.widget.RadioButton[@text='"+opcao+"']";

        getDriver().findElementByXPath(path).click();
    }

    public WebElement obterCheckBoxQuestionario(String index){
        String path = "//*[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/qstfrm_content']" +
                "/android.widget.LinearLayout[@index='"+index+"']" +
                "/android.widget.LinearLayout[@index='0']" +
                "/android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:id/itmqstfrm_layout']" +
                "/android.widget.CheckBox[@resource-id='br.gov.sp.artesp.sisf.mobile:id/item_checkBox']";
        WebElement elemento = getDriver().findElementByXPath(path);
        return elemento;
    }

    public boolean verificaRespostaCheckBox(WebElement elemento){
        String checked = elemento.getAttribute("checked");
        if (checked.equalsIgnoreCase("true")){
            return true;
        }
        return false;
    }


}

