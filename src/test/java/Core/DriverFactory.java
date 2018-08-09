package Core;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver(){
        if (driver == null) {
            createDriver();
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
        capacidade.setCapability("newCommandTimeout", 300);

        try {
            driver = new AndroidDriver(new URL("http://127.0.01:4723/wd/hub"), capacidade);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    public static void killDriver(){
        if (driver != null) {
            driver.quit();
            driver=null;
        }
    }
}
