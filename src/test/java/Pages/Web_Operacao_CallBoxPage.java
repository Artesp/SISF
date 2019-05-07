package Pages;

import Assistant.ObjetosParaFiscalizacao;
import Core.BasePageWeb;
import org.openqa.selenium.By;

public class Web_Operacao_CallBoxPage extends BasePageWeb {

    private LoginWebPage loginPage = new LoginWebPage();

    public void logarWeb(){
        loginPage.logarSisfWeb(ObjetosParaFiscalizacao.usuario, ObjetosParaFiscalizacao.senha);
        loginPage.entrar();
    }

    public String obterTextoElemento(String path){
        String textoElemento = obterTextoPorElemento(By.xpath(path));
        return textoElemento;
    }

    public void selecionaTipoFiscalizacaoOperacao(){
        clicarOpcaoListaWeb("campoTipoFisc", "Operação");
    }

    public void selecionaGrupoFiscalizacaoCallBox(){
        clicarOpcaoListaWeb("campogrupoFisc", "Call Box");
    }

    public void botaoFiltrar(){
        clicarBotaoFiltrar();
    }

    public void botaoDetalhar(){
        clicarBotaoDetalhar();
    }

    public void botaoEditar(){
        clicarBotaoEditar();
    }

    public void botaoVoltar(){
        clicarBotaoVoltar();
    }

    public void botaoSalvar(){
        clicarBotaoSalvar();
    }

    public void selecionarRadioButton(By by){
        selecionaRadioButtonWeb(by);
    }

    public void botaoSimConfirmacao(){
        clicarBotaoSimConfirmacao();
    }

    public void selecionarNaoConformidades(By by){
        clicarEmElementoWeb(by);
    }

    public boolean verificaCamposNaoEditaveis(String[] paths){
        for (int i = 0; i < paths.length; i++){
            boolean campoEditavel = camposNaoEditaveis(By.xpath(paths[i]));
            if (campoEditavel){
                return true;
            }
        }
        return false;
    }

    public boolean verificaElementoExiste(By by){
        boolean elemento = campoExiste(by);
        return elemento;
    }

    public void rolarTelaAteElemento(By by){
        rolarTelaAteElementoSerEncontrado(by);
    }

}
