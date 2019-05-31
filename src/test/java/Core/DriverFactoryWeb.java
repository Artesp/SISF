package Core;

import Assistant.PathsAssistant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
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

        System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver.exe");

        //Definindo o local de Download de Arquivos.
        String downloadFilePath = BasePageWeb.pastaRelatorios;
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilePath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        options.addArguments("--js-flags=--expose-gc");
        options.addArguments("--enable-precise-memory-info");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("test-type=browser");
        options.addArguments("disable-infobars");
        options.setExperimentalOption("prefs", chromePrefs);

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
