package Tests;

import Core.BaseTestWeb;
import Pages.LoginWebPage;
import org.junit.Test;

public class LoginWebTest extends BaseTestWeb {

    private LoginWebPage page = new LoginWebPage();

    String usuario = "automacao_sisf";
    String senha = "12345678";

    @Test
    public void realizarLogin_SisfWeb(){
        page.logarSisfWeb(usuario,senha);
        page.entrar();
    }

}
