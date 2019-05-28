package SISF_WEB_RelatoriosPages;

import Assistant.PathsAssistant;
import Core.BasePageWeb;
import org.openqa.selenium.By;

public class Web_Relatorios_ConservacaoPage extends BasePageWeb {


    public void selecionaValorCombo(String comboID, String tipoConservacao){
        clicarOpcaoListaWeb(comboID, tipoConservacao);
    }

    public boolean campoExisteNaTela(String textoCampo){
        boolean existente = campoExiste(By.xpath("//*[contains(@id,'orderByLink')]"));
        return existente;
    }

    public void clicarBotaoGerarRelatorio(){
        clicarBotaoPorID("btnRelatorio");
    }

    public void clicarBotaoExportarPDF(){
        clicarBotaoPorID("botaoPdf");
    }

    public String obterTextoDoElemento(String pathElemento){
        String texto = obterTextoPorElemento(By.xpath(pathElemento));
        return texto;
    }

    public boolean rolarATelaAteExportarPDF(){
        try {
            rolarTelaAteElementoSerEncontrado(By.xpath(PathsAssistant.WEB_BOTAO_EXPORTAR_PDF));
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
