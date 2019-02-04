package Tests;

import Assistant.ObjetosParaFiscalizacao;
import Core.BaseTestWeb;
import Pages.LoginWebPage;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginWebTest extends BaseTestWeb {

    private LoginWebPage page = new LoginWebPage();
    private ObjetosParaFiscalizacao obj = new ObjetosParaFiscalizacao();

    @Test
    public void realizarLogin_SisfWeb(){
        page.logarSisfWeb(obj.usuario,obj.senha);
        page.entrar();

        assertEquals("automacao_sisf ( automacao_sisf )", page.textoPorElemento());
    }

}
