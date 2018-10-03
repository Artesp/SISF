package Pages;

import Core.BasePage;
import Core.BaseTest;
import org.openqa.selenium.By;

import static Core.DriverFactory.getDriver;

public class ConsRotinaPage extends BasePage {

    public void grupoSubgrupo(){
        getDriver().findElementByXPath("//android.widget.LinearLayout[@index='2']/android.widget.LinearLayout[@index='0']/android.widget.Spinner[@index='1']").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='Conservação de Rotina']").click();
    }

    public void preencherSecaoPrazo(String tipo, String grupo, String atividade){
        //Combo Tipo
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/atv_spn_tipo").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='"+tipo+"']").click();
        //Combo Grupo
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/atv_spn_grupo").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='"+grupo+"']").click();
        //Combo Atividade
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/atv_spn_atividade").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='"+atividade+"']").click();

        Boolean dataHabilitada = dataEstaHabilitada(By.id("br.gov.sp.artesp.sisf.mobile:comp/dt_data"));

        if (dataHabilitada){
            //Data para reparo
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/dt_button").click();
            new BaseTest().modalData_ScroollUpDia();
        }

    }

    public boolean dataEstaHabilitada(By by){
        boolean dataHabilitada = getDriver().findElement(by).isEnabled();
        if(dataHabilitada){
            return true;
        }
        return false;
    }

    public void preencherSecaoTrecho(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txt_editText").sendKeys(gerarTextoPraTeste());
    }

    public void preencherSecaoObservacao(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText").sendKeys(geraTexto());
    }

    public String geraTexto(){
       return gerarTextoPraTeste();
    }

    public void clicarBotaoAddRetorno(){
        botaoAddRetorno();
    }

    public void preencherRodoviaConservacao(){
        preencherRodovia();
    }

    public void fotosEmLote(int qtdFotos){
        capturarFotosGaleria_LOTE(qtdFotos);
    }
}
