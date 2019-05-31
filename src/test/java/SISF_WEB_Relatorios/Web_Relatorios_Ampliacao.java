package SISF_WEB_Relatorios;

import Assistant.ObjetosParaFiscalizacao;
import Assistant.PathsAssistant;
import Core.BasePageWeb;
import Core.BaseTestWeb;
import Pages.LoginWebPage;
import Pages.ModulosWebPage;
import SISF_WEB_RelatoriosPages.Web_Relatorios_AmpliacaoPage;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "Web_Relatorios_Ampliacao.csv")
public class Web_Relatorios_Ampliacao extends BaseTestWeb {

    private ModulosWebPage webPageModulos = new ModulosWebPage();
    private LoginWebPage webLoginPage = new LoginWebPage();
    private Web_Relatorios_AmpliacaoPage pageRelatoriosAmpliacaoPage = new Web_Relatorios_AmpliacaoPage();
    long tamanhoArquivo;

    @Test
    public void verificarGridDeGrupos_Relatorios(
            @Param(name = "Tipo")String Tipo,
            @Param(name = "Grupo")String Grupo,
            @Param(name = "Subgrupo")String Subgrupo,
            @Param(name = "campo0")String campo0,
            @Param(name = "campo1")String campo1,
            @Param(name = "campo2")String campo2,
            @Param(name = "campo3")String campo3,
            @Param(name = "campo4")String campo4,
            @Param(name = "campo5")String campo5,
            @Param(name = "campo6")String campo6,
            @Param(name = "campo7")String campo7,
            @Param(name = "campo8")String campo8,
            @Param(name = "campo9")String campo9,
            @Param(name = "campo10")String campo10,
            @Param(name = "campo11")String campo11,
            @Param(name = "campo12")String campo12,
            @Param(name = "campo13")String campo13,
            @Param(name = "campo14")String campo14,
            @Param(name = "campo15")String campo15,
            @Param(name = "campo16")String campo16,
            @Param(name = "campo17")String campo17,
            @Param(name = "campo18")String campo18,
            @Param(name = "campo19")String campo19,
            @Param(name = "campo20")String campo20
    ){
        boolean arquivoExiste = false;
        preparaCenario();
        selecionaDataInicialFinalConstatacao();
        pageRelatoriosAmpliacaoPage.selecionaValorCombo("campoTipoFisc",Tipo);
        pageRelatoriosAmpliacaoPage.selecionaValorCombo("campogrupoFisc",Grupo);
        esperaJanelaCarregar(1000);
        if(pageRelatoriosAmpliacaoPage.campoEstaHabilitado(By.id("campoSubGrupo")))
            pageRelatoriosAmpliacaoPage.selecionaValorCombo("campoSubGrupo", Subgrupo);

        ArrayList<String> listaParametros = new ArrayList<>();

        int contador = 0;
        int indexListaParametros = 0;
        int indexXPATH = 1;

        do{
            listaParametros.add(campo0);
            listaParametros.add(campo1);
            listaParametros.add(campo2);
            listaParametros.add(campo3);
            listaParametros.add(campo4);
            listaParametros.add(campo5);
            listaParametros.add(campo6);
            listaParametros.add(campo7);
            listaParametros.add(campo8);
            listaParametros.add(campo9);
            listaParametros.add(campo10);
            listaParametros.add(campo11);
            listaParametros.add(campo12);
            listaParametros.add(campo13);
            listaParametros.add(campo14);
            listaParametros.add(campo15);
            listaParametros.add(campo16);
            listaParametros.add(campo17);
            listaParametros.add(campo18);
            listaParametros.add(campo19);
            listaParametros.add(campo20);

            if (listaParametros.get(indexListaParametros).equalsIgnoreCase("null")){
                String textoColunaObtido = pageRelatoriosAmpliacaoPage.obterTextoDoElemento("//div[@id='tabelaResultado']" +
                        "/table//tr[@class='tabela_linha_destaque']" +
                        "/th["+indexXPATH+"]");
                String textoColunaEsperado = quebrarTextoObtido(textoColunaObtido);
                assertEquals(listaParametros.get(indexListaParametros), textoColunaEsperado);
                indexListaParametros++;
                contador++;
                indexXPATH++;
                listaParametros.clear();
            }else{
                break;
            }
        }while (contador <= 17);
        esperaJanelaCarregar(1000);
        pageRelatoriosAmpliacaoPage.clicarBotaoGerarRelatorio();
        boolean exportarPDFExiste = pageRelatoriosAmpliacaoPage.rolarATelaAteRelatorioPDF();
        assertEquals(true, exportarPDFExiste);
        verificaDiretorioRelatorio();
        if (exportarPDFExiste){
            pageRelatoriosAmpliacaoPage.clicarBotaoExportarPDF();
            esperaJanelaCarregar(2000);
            do {
                String nomeAquivo = verificarArquivoDownLoad();
                if (nomeAquivo.contains("pdf") && !nomeAquivo.contains("crdownload") && tamanhoArquivo > 0){
                    arquivoExiste = true;
                }
            }while (arquivoExiste!=true);

            assertTrue(arquivoExiste);
        }
        deletaRelatorioExistente();

    }

    private void deletaRelatorioExistente() {
        File relatorio = new File(BasePageWeb.pastaRelatorios);
        File[] listaArquivos = relatorio.listFiles();
        if (listaArquivos.length != 0) {
            listaArquivos[0].delete();
            relatorio.delete();
        }
    }

    private void verificaDiretorioRelatorio() {
        File diretorio = new File(BasePageWeb.pastaRelatorios);
        if (!diretorio.exists()){
            diretorio.mkdir();
        }
    }

    private String verificarArquivoDownLoad(){
        File relatorio = new File(BasePageWeb.pastaRelatorios);
        File[] listaArquivos = relatorio.listFiles();
        String nomeArquivo = listaArquivos[0].getName();
        tamanhoArquivo = listaArquivos[0].length();
        System.out.println(listaArquivos[0].getName() + " | Tamanho: " + listaArquivos[0].length());
        return nomeArquivo;
    }

    private void selecionaDataInicialFinalConstatacao() {
        //Data inicial de constatação
        pageRelatoriosAmpliacaoPage.datePickerSelect(PathsAssistant.WEB_DATEPICKER_CONSTATACAO_INICIAL);
        pageRelatoriosAmpliacaoPage.opcaoMesDatePicker("Jan");
        pageRelatoriosAmpliacaoPage.opcaoDataDatePicker("1");
        esperaJanelaCarregar(1000);

        //Data final de constatação
        pageRelatoriosAmpliacaoPage.datePickerSelect(PathsAssistant.WEB_DATEPICKER_CONSTATACAO_FINAL);
        pageRelatoriosAmpliacaoPage.opcaoMesDatePicker("Mai");
        pageRelatoriosAmpliacaoPage.opcaoDataDatePicker("30");
    }

    private String quebrarTextoObtido(String textoColunaEsperado) {
        String textoColuna = textoColunaEsperado.replaceAll("\r", " ").replaceAll("\n", " ");
        return textoColuna;
    }

    private void preparaCenario() {
        webLoginPage.logarSisfWeb(ObjetosParaFiscalizacao.usuario, ObjetosParaFiscalizacao.senha);
        webLoginPage.entrar();
        webPageModulos.clicarBotaoRelatoriosDeFiscalizacao();
    }


}
