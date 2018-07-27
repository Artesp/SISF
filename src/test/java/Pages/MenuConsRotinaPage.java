package Pages;

import static Core.DriverFactory.getDriver;

public class MenuConsRotinaPage {

    public void navegarMenuPrincipal(String index){
        getDriver().findElementByXPath("//android.widget.LinearLayout[@resource-id='br.gov.sp.artesp.sisf.mobile:comp/fsc_content']/android.widget.Button[@index='" + index + "']").click();
    }
}
