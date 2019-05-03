package Core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import java.util.concurrent.TimeUnit;

import static Core.DriverFactoryWeb.*;

public class BaseTestWeb {


    private String endereco = "http://extranet.hom.artesp.sp.gov.br:8080/sisf-web/visao/login/loginpage.html?0";

    @Before
    public void testInitialize(){
        abrirNavegador();
    }

    @After
    public void tearDown(){
        fecharNavegador();
    }

   /* @AfterClass
    public static void classCleanup(){
        killNav();
    }*/

    public void abrirNavegador(){

        getNav().manage().window().maximize();
        getNav().navigate().to(endereco);
    }

    public void fecharNavegador(){
        killNav();
    }

    public void esperaJanelaCarregar(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
