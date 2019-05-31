package Core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static Core.DriverFactoryWeb.*;

public class BasePageWeb {

    public static final String pastaRelatorios = "D:/SISF_RELATORIOS";

    protected void selecionarModulosWeb(String xpath){
        getNav().findElementByXPath(xpath).click();
    }

    protected void clicarOpcaoListaWeb(String comboID, String comboValor){
        new BaseTestWeb().esperaJanelaCarregar(1000);
        getNav().findElementByXPath("//select[@id = '" + comboID + "']/option[contains(.,'"+ comboValor + "')]").click();
    }

    protected void clicarOpcaoMesWeb(String mes){
        getNav().findElementByXPath("//select[@class ='ui-datepicker-month']/option[contains(.,'" +mes+ "')]").click();
    }

    protected void clicarOpcaoDataWeb(String data){
        getNav().findElementByLinkText(data).click();
    }

    protected void clicarDatePickerWeb(By by){
        getNav().findElement(by).click();
    }

    protected void clicarBotaoFiltrar(){
        getNav().findElementById("btnConsultar").click();
    }

    protected void clicarBotaoMarcarRetorno(){
        getNav().findElementByXPath("//button[contains(@id, 'marcarRetorno')]").click();
    }

    protected void clicarBotaoPorXPath(String XPath){
        getNav().findElementByXPath(XPath).click();
    }

    protected void clicarBotaoPorID(String ID){
        getNav().findElementById(ID).click();
    }

    protected void clicarBotaoDetalhar(){
        getNav().findElementByXPath("//button[contains(@id, 'detalhar')]").click();
    }

    protected void clicarBotaoEditar(){
        getNav().findElementByXPath("//button[contains(@id, 'editar')]").click();
    }

    protected void clicarBotaoVoltar(){
        getNav().findElementByXPath("//button[contains(@id,'voltar')]").click();
    }

    protected void clicarBotaoSalvar(){
        getNav().findElementByXPath("//button[@alt='Salvar']").click();
    }

    protected void clicarEmElementoWeb(By by){
        getNav().findElement(by).click();
    }

    protected void clicarBotaoSimConfirmacao(){
        getNav().findElementByXPath("//input[contains(@id, 'yesButton')]").click();
    }

    protected String obterTextoPorElemento(By by){
        return  getNav().findElement(by).getText();
    }

    protected boolean camposNaoEditaveis(By by){
        boolean bloqueado = getNav().findElement(by).isEnabled();
        return bloqueado;
    }

    protected boolean campoExiste(By by){
        try {
            WebElement elementoExiste = getNav().findElement(by);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    protected void rolarTelaAteElementoSerEncontrado(By by){
        WebElement element = getNav().findElement(by);
        JavascriptExecutor javascriptExecutor = getNav();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void selecionaRadioButtonWeb(By by){
        getNav().findElement(by).click();
    }

    protected String quebraString(String msgSucesso){
        String[] words = msgSucesso.split(" ");
        return words[3];
    }

}
