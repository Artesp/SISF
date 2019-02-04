package Tests;

import Core.BaseTest;
import Pages.LoginPage;
import org.junit.Test;

public class LoginTest extends BaseTest {

    private LoginPage page = new LoginPage();

    @Test
    public void realizaLoginSisf(){
        page.realizaLogin();
    }

}
