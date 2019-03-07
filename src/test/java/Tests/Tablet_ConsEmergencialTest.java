package Tests;

import Core.BaseTest;
import Pages.LoginPage;
import Pages.ModulosPage;
import org.junit.Test;

public class Tablet_ConsEmergencialTest extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private ModulosPage modulo = new ModulosPage();

    @Test
    private void gerarFiscalizacao_ComSucesso(){

        prepararCenario();

    }

    private void prepararCenario() {
        loginPage.realizaLogin();
        modulo.moduloConservacao();
        botaoAddFiscalizacao();

    }

}
