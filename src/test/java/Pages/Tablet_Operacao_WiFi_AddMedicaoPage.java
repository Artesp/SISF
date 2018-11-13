package Pages;

import static Assistant.Questionario_Operacao_WiFiAssistant.*;

import Assistant.Questionario_Operacao_WiFiAssistant;
import Core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;

import static Core.DriverFactory.getDriver;

public class Tablet_Operacao_WiFi_AddMedicaoPage extends BasePage {

    private Tablet_Operacao_WiFiPage pageWiFi = new Tablet_Operacao_WiFiPage();

    public void preencherWiFiEmLote(int qtdMed){

        int contador = 0;
        int incrementaKM = 100;

        do {
            pageWiFi.clicarBotaoAddMedicao();
            clicarBotaoIntensidadeSinal();
            clicarBotaoCronometro();
            clicarBotaoFinalizaCronometro();
            preencheKmInicial(String.valueOf(incrementaKM));
            preencheMetros("000");
            selecionaSentido("N - Norte");
            respondeQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO.toString(), "Sim");
            respondeQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO.toString(), "Sim");
            respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Bom");
            respondeQuestionario(QUESTINARIO_WIFI.ENCERRAMENTO_DE_CHAMADA.toString(), "Voluntariamente");
            clicarBotaoOK();
            contador++;
            incrementaKM += 3;
            System.out.println(contador + " KM: " + incrementaKM + "000");
        }while (contador < qtdMed);
    }

    public void preencherWiFi(){
        pageWiFi.clicarBotaoAddMedicao();
        clicarBotaoIntensidadeSinal();
        clicarBotaoCronometro();
        clicarBotaoFinalizaCronometro();
        preencheKmInicial("12");
        preencheMetros("000");
        selecionaSentido("N - Norte");
        respondeQuestionario(QUESTINARIO_WIFI.HOUVE_CONEXAO.toString(), "Sim");
        respondeQuestionario(QUESTINARIO_WIFI.HOUVE_ATENDIMENTO.toString(), "Sim");
        respondeQuestionario(QUESTINARIO_WIFI.QUALIDADE_DA_COMUNICACAO.toString(), "Bom");
        respondeQuestionario(QUESTINARIO_WIFI.ENCERRAMENTO_DE_CHAMADA.toString(), "Voluntariamente");
        clicarBotaoOK();
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
        opcao = tratarRespostaQuestionario(opcao);
        String path = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout["+questao+"]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton["+opcao+"]";
        getDriver().findElementByXPath(path).click();
        //getDriver().findElementByXPath(path).click();
    }

    private String tratarRespostaQuestionario(String opcao) {
        if (opcao!=null) {
            if (opcao.equalsIgnoreCase("Sim") || opcao.equalsIgnoreCase("Bom") || opcao.equalsIgnoreCase("Voluntariamente")) {
                return opcao = "1";
            } else {
                return opcao = "2";
            }
        }
        return opcao;
    }


}
