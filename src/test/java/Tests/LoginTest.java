package Tests;

import Core.BaseTest;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage page = new LoginPage();

    @Test
    public void realizaLoginSisf(){
        page.realizaLogin();
    }

}
