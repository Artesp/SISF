package Core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class DriverFactoryWeb {

    private static ChromeDriver nav;

    public static ChromeDriver getNav(){
        if (nav == null){
            createNav();
        }
        return nav;
    }

    private static void createNav(){

        System.setProperty("webdriver.chrome.driver", "D:/Drivers/chromedriver_win32/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        options.addArguments("--js-flags=--expose-gc");
        options.addArguments("--enable-precise-memory-info");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("test-type=browser");
        options.addArguments("disable-infobars");

        nav = new ChromeDriver(options);
        nav.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void killNav(){
        if (nav != null) {
            nav.quit();
            nav = null;
        }
    }
}
