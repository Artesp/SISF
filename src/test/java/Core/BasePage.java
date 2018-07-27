package Core;

import Assistant.ObjetosParaFiscalizacao;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.Random;

import static Core.DriverFactory.*;

public class BasePage {

    private ObjetosParaFiscalizacao obj = new ObjetosParaFiscalizacao();

    public void realizarLoginSISF(){
        String textoUsuario = getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/user").getText();
        String textoSenha = getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").getText();

        if ("Usuário".equalsIgnoreCase(textoUsuario) || !"automacao_sisf".equalsIgnoreCase(textoUsuario)){
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/user").clear();
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/user").sendKeys(obj.usuario);
        }

        if (!"".equalsIgnoreCase(textoSenha)){
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").clear();
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").sendKeys(obj.senha);
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/sign_in_button").click();
        }
        else{
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").sendKeys(obj.senha);
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/sign_in_button").click();
        }

        String actual = getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_prox").getText();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_prox").click();
        new BaseTest().esperaCarregar(35000);
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_prox").click();
    }

    public void preencherRodovia(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/spnspl_spinner").click();
        getDriver().findElementByXPath("//android.widget.ListView[@index='0']/android.widget.CheckedTextView[@text='AUTOBAN']").click();

        String valorA = "//android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/rod_rodoviasp_rodovia']/android.widget.LinearLayout[@index='0']/android.widget.Spinner[@index='1']";
        getDriver().findElementByXPath(valorA).click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='SP 348              ']").click();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_inicial_text").sendKeys("14");
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_mtsini_text").sendKeys("000");
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_final_text").sendKeys("150");
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_mtsfim_text").sendKeys("000");

        String valorB = "//android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/rod_sentido_spnspl']/android.widget.LinearLayout[@index='0']//android.widget.Spinner[@index='1']";
        getDriver().findElementByXPath(valorB).click();

        scroolConcessionaria();

        getDriver().findElementByXPath("//*[@text='N - Norte']").click();

        String localExiste = getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_local_text").getText();
        if (!"".equalsIgnoreCase(localExiste))
        {
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/lczcfsclzc_local_text").sendKeys("Local Teste");
        }
    }

    public void capturarFotosGaleria(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_camera").click();
        getDriver().findElementById("com.android.camera:id/shutter_button").click();
        getDriver().findElementByXPath("//android.widget.TextView[@text='DONE']").click();
    }

    public void capturarFotosGaleria_LOTE(int qtdFotos){
        int contador = 0;
        do{
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_camera").click();
            getDriver().findElementById("com.android.camera:id/shutter_button").click();
            getDriver().findElementByXPath("//android.widget.TextView[@text='DONE']").click();
            new BaseTest().esperaCarregar(1000);
            contador++;
        } while (contador < qtdFotos);
    }

    public void CapturarFotosGaleria_LOTE_Randomico()
    {
        int contador = 0;
        int numeroLoop = numeroDeFotosRandomico(0, 10);
        do{
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_camera").click();
            getDriver().findElementById("com.android.camera:id/shutter_button").click();
            getDriver().findElementByXPath("//android.widget.TextView[@text='DONE']").click();
            new BaseTest().esperaCarregar(1000);
            contador++;
        } while (contador < numeroLoop);
    }

    private void scroolConcessionaria(){
        /*Dimension size = getDriver().manage().window().getSize();

        int x = size.width/2;

        int start_y = (int) (size.height*inicio);
        int end_y = (int) (size.height*fim);



        new BaseTest().esperaCarregar(2000);*/

        int startX = 454;
        int startY = 87;
        int endX = 448;
        int endY = 736;

        PointOption point = new PointOption();
        WaitOptions espera = new WaitOptions();

        new TouchAction(getDriver()).press(point.withCoordinates(startX, startY))
                .waitAction( espera.withDuration(Duration.ofMillis(500)))
                .moveTo(point.withCoordinates(endX, endY))
                .release()
                .perform();

        /*
        int startX = 454;
        int startY = 87;
        int endX = 448;
        int endY = 736;
        //getDriver().Swipe(startX, startY, endX, endY, 1000);
        */

    }

    private int numeroDeFotosRandomico(int min, int max){
        Random rdn = new Random();
        return rdn.nextInt(min-max);
    }

}
