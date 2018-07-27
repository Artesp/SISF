package Pages;

import Core.BasePage;

import static Core.DriverFactory.getDriver;

public class ConsRotinaPage extends BasePage {

    public void grupoSubgrupo(){
        getDriver().findElementByXPath("//android.widget.LinearLayout[@index='2']/android.widget.LinearLayout[@index='0']/android.widget.Spinner[@index='1']").click();
        getDriver().findElementByXPath("//android.widget.CheckedTextView[@text='Conservação de Rotina']").click();
    }

    public void PreencherSecaoPrazo(){
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
    }

}
