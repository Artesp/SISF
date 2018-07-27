package Core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

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

    /*
    public void FecharSistemaSISF()
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_cancel").Click();
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_ok").Click();
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_sair").Click();
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_ok").Click();
    }

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

    public void BotaoAddFiscalizacao()
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_add").Click();
    }

    public void BotaoAddFiscalizacaoRetorno()
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:comp/lstfsc_add_retorno").Click();
    }

    public void Salvar()
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:comp/fschdr_starred").Click();
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_save").Click();
    }

    public void Enviar(int milisegundos)
    {
        getDriver().FindElementById("br.gov.sp.artesp.sisf.mobile:id/btn_enviar").Click();
        EsperaCarregar(milisegundos);
    }

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

    /*
    public string ObterTextoElemento(By by)
    {
        string texto = getDriver().FindElement(by).Text;
        return texto;
    }

    public bool ExisteObjetoNaTela(By by)
    {
        var existeElemento = getDriver().FindElement(by);
        if (existeElemento == null)
        {
            return false;
        }
        return true;
    }

    public void ModalData_ScroollUpDia()
    {

        var size = getDriver().FindElementById("android:id/pickers").Size;

        int startX = size.Width * 2;
        int startY = (size.Width * 3) / 2;
        int endX = startX - 2;
        int endY = startY - ((startY - size.Width) / 2);

        // getDriver().Swipe(startX, startY, endX, endY, 1000);

        getDriver().FindElementById("android:id/button1").Click();
    }
    */


}
