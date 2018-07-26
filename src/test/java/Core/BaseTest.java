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

}
