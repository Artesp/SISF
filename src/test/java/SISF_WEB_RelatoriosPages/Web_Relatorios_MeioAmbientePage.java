package SISF_WEB_RelatoriosPages;

import Assistant.PathsAssistant;
import Core.BasePageWeb;
import org.openqa.selenium.By;

public class Web_Relatorios_MeioAmbientePage extends BasePageWeb {

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
        clicarBotaoPorXPath(PathsAssistant.WEB_BOTAO_EXPORTAR_PDF);
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

    public void datePickerSelect(String path){
        clicarDatePickerWeb(By.xpath(path));
    }

    public void opcaoMesDatePicker(String mes){
        clicarOpcaoMesWeb(mes);
    }

    public void opcaoDataDatePicker(String data){
        clicarOpcaoDataWeb(data);
    }

    public boolean campoEstaHabilitado(By by){
        boolean campoHabilidato = camposNaoEditaveis(by);
        return campoHabilidato;
    }

}
