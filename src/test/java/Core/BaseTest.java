package Core;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;

import static Core.DriverFactory.*;

public class BaseTest {

    @Before
    public void testInitialize(){
        if (getDriver() == null) {
            getDriver();
        }
        else{
            getDriver().launchApp();
        }
    }

    @AfterClass
    public static void classCleanup(){
        killDriver();
    }

    @After
    public void tearDown(){
        getDriver().closeApp();
    }


    public void fecharSistemaSISF() {
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_cancel").click();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_ok").click();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_sair").click();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_ok").click();
    }

/*
    public void CancelarFiscalizacao()
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_cancel").Click();
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_ok").Click();
    }

    public void Menu_ScrollUp()
    {
        Size menu = getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:comp/fsc_content").Size;

        int startY = (int)(menu.Height * 0.90);
        int endY = (int)(menu.Height * 0.20);
        int startX = menu.Width / 2;

        //getDriver().Swipe(startX, startY, startX, endY, 1000);
    }
*/
    public void botaoAddFiscalizacao(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_add").click();
    }

    /*

    public void BotaoAddFiscalizacaoRetorno()
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_add_retorno").Click();
    }

*/
    public void salvar(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/fschdr_starred").click();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_save").click();
    }

    public void enviar(int milisegundos){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_enviar").click();
        new BaseTest().esperaCarregar(milisegundos);
    }

    /*
    public void AtualizarTelaConsultaSisf()
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_refresh").Click();
    }

*/

    public void esperaCarregar(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String obterTextoElemento(By by){
        String texto = getDriver().findElement(by).getText();
        return texto;
    }
/*
    public bool ExisteObjetoNaTela(By by)
    {
        var existeElemento = getDriver().FindElement(by);
        if (existeElemento == null)
        {
            return false;
        }
        return true;
    }
*/
    public void ModalData_ScroollUpDia(){

        Dimension size = getDriver().findElementById("android:id/pickers").getSize();

        int startX = size.width * 2;
        int startY = (size.width * 3) / 2;
        int endX = startX - 2;
        int endY = startY - ((startY - size.width) / 2);

        PointOption point = new PointOption();
        WaitOptions espera = new WaitOptions();

        new TouchAction(getDriver()).press(point.withCoordinates(startX, startY))
                .waitAction(espera.withDuration(Duration.ofMillis(500)))
                .moveTo(point.withCoordinates(endX, endY))
                .release()
                .perform();

        getDriver().findElementById("android:id/button1").click();
    }



}
