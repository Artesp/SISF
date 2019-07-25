package Core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static AndroidDriver driver;

//    private static AppiumDriverLocalService service;
//    private static AppiumServiceBuilder builder;

    public static AndroidDriver getDriver(){
        if (driver == null) {
            createDriver();
            //service.start();
        }
        return driver;
    }

    private static void createDriver() {
        DesiredCapabilities capacidade = new DesiredCapabilities();
        capacidade.setCapability("platformName", "Android");
        capacidade.setCapability("deviceName", "Android Emulator");
        capacidade.setCapability("noReset", "true");
        capacidade.setCapability("appPackage", "br.gov.sp.artesp.sisf.mobile");
        capacidade.setCapability("appActivity", "br.gov.sp.artesp.sisf.mobile.LoginActivity");
        capacidade.setCapability("unicodeKeyboard", true);
        capacidade.setCapability("resetKeyboard", true);
        capacidade.setCapability("newCommandTimeout", 300);

        //Build the AppiumService
//        builder = new AppiumServiceBuilder();
//        builder.withIPAddress("127.0.0.1");
//        builder.usingPort(4723);
//        builder.withCapabilities(capacidade);
//        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
//
//        //Start server with the builder
//        service = AppiumDriverLocalService.buildService(builder);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
            //driver = new AndroidDriver(new URL("http://10.23.42.141:4723/wd/hub"), capacidade);
            //driver = new AndroidDriver(service.getUrl(), capacidade);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //driver.hideKeyboard();
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

    }

    public static void killDriver(){
        if (driver != null) {
            driver.quit();
            driver=null;
            //service.stop();
        }
    }
}
