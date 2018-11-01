package Pages;

import Core.BasePage;

import static Core.DriverFactory.getDriver;

public class Tablet_Operacao_WiFiPage extends BasePage {

    public void grupoSubgrupo(){
        getDriver().findElementByXPath("//android.widget.LinearLayout[@index='2']/android.widget.LinearLayout[@index='0']/android.widget.Spinner[@index='1']").click();
        grupoSubGrupo_ScrollUp();
        getDriver().findElementByXPath("//*[@text='Wi-Fi']").click();
    }

    public void preencherRodoviaWiFi(){
        preencherRodovia();
    }

    public void clicarBotaoAddMedicao() {
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/lstcbn_btn_add").click();
    }

    public void preencherSecaoObservacao(String geraTexto){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText").sendKeys(geraTexto);
    }

    public String gerarTextoParaTeste(){
        String texto = gerarTextoPraTeste();
        return texto;
    }

    public void capturarImagem(){
        capturarFotosGaleria();
    }
}
