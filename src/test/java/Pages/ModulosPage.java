package Pages;

import Core.BasePage;

import static Core.DriverFactory.getDriver;

public class ModulosPage extends BasePage {

    public void moduloConservacao(){
        getDriver().findElementByXPath("//android.widget.GridView[@resource-id='br.gov.sp.artesp.sisf.mobile:id/menu_grid_view']/android.widget.LinearLayout[@index='0']/android.widget.ImageView[@index='0']").click();
    }

    public void moduloAmpliacao(){
        getDriver().findElementByXPath("//android.widget.LinearLayout[@index='7']/android.widget.ImageView[@index='0']").click();
    }

    public void moduloOperacao(){
        getDriver().findElementByXPath("//android.widget.GridView[@resource-id='br.gov.sp.artesp.sisf.mobile:id/menu_grid_view']/android.widget.LinearLayout[@index='2']/android.widget.ImageView[@index='0']").click();
    }

}
