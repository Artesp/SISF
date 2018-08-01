package Pages;

import Core.BasePageWeb;

import static Core.DriverFactoryWeb.*;

public class LoginWebPage extends BasePageWeb {

    public void logarSisfWeb(String usuario, String senha){
        getNav().findElementById("email1").sendKeys(usuario);
        getNav().findElementById("campoSenha").sendKeys(senha);
    }

    public void entrar(){
        getNav().findElementById("btnLogar5").click();
    }

}
