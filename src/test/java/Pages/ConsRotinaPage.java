package Pages;

import Core.BasePage;
import Core.BaseTest;

import static Core.DriverFactory.getDriver;

public class ConsRotinaPage extends BasePage {

    public void grupoSubgrupo(){
        getDriver().findElementByXPath("//android.widget.LinearLayout[@index='2']/android.widget.LinearLayout[@index='0']/android.widget.Spinner[@index='1']").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='Conservação de Rotina']").click();
    }

    public void preencherSecaoPrazo(){
        //Combo Tipo
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/atv_spn_tipo").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='Pavimento']").click();
        //Combo Grupo
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/atv_spn_grupo").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='Revestimento Primário']").click();
        //Combo Atividade
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/atv_spn_atividade").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='Reconformação de vias secundárias']").click();
        //Data para reparo
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/dt_button").click();
        new BaseTest().ModalData_ScroollUpDia();
    }

    public void preencherSecaoTrecho(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txt_editText").sendKeys(gerarTextoPraTeste());
    }

    public void preencherSecaoObservacao(){
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:comp/txtarea_editText").sendKeys(gerarTextoPraTeste());
    }

    public String gerarTextoPraTeste(){
        String textoTeste = "Lorem ipsum etiam inceptos habitasse mi phasellus ipsum dictumst dolor ut eu maecenas magna ullamcorper, donec adipiscing cubilia nunc curae commodo feugiat aenean quam in magna turpis elementum. libero lacus auctor cursus nulla sociosqu porta himenaeos libero tempor pretium hac, nullam habitasse dictum nec eget platea ad placerat at volutpat. malesuada feugiat primis sit dui sagittis, donec cursus aenean mi at, vestibulum aptent ante libero.";
        return textoTeste;
    }
}
