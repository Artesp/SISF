package Core;

import Assistant.ObjetosParaFiscalizacao;

import static Core.DriverFactory.*;

public class BasePage {

    private ObjetosParaFiscalizacao obj = new ObjetosParaFiscalizacao();

    public void realizarLoginSISF(){
        String textoUsuario = getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/user").getText();
        String textoSenha = getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").getText();

        if ("Usuário".equalsIgnoreCase(textoUsuario) || !"automacao_sisf".equalsIgnoreCase(textoUsuario)){
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/user").clear();
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/user").sendKeys(obj.usuario);
        }

        if (!"".equalsIgnoreCase(textoSenha)){
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").clear();
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").sendKeys(obj.senha);
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/sign_in_button").click();
        }
        else{
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/password").sendKeys(obj.senha);
            getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/sign_in_button").click();
        }

        String actual = getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_prox").getText();
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_prox").click();
        new BaseTest().esperaCarregar(80000);
        getDriver().findElementById("br.gov.sp.artesp.sisf.mobile:id/btn_prox").click();
    }


}
