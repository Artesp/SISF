package SISF_WEB_Relatorios;

import Assistant.ObjetosParaFiscalizacao;
import Assistant.PathsAssistant;
import Core.BasePageWeb;
import Core.BaseTestWeb;
import Pages.LoginWebPage;
import Pages.ModulosWebPage;
import SISF_WEB_RelatoriosPages.Web_Relatorios_MeioAmbientePage;
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
@DataLoader(filePaths = "Web_Relatorios_MeioAmbiente.csv")
public class Web_Relatorios_MeioAmbiente extends BaseTestWeb {

    private ModulosWebPage webPageModulos = new ModulosWebPage();
    private LoginWebPage webLoginPage = new LoginWebPage();
    private Web_Relatorios_MeioAmbientePage pageRelatoriosMamb = new Web_Relatorios_MeioAmbientePage();
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
            @Param(name = "campo20")String campo20,
            @Param(name = "campo21")String campo21,
            @Param(name = "campo22")String campo22,
            @Param(name = "campo23")String campo23,
            @Param(name = "campo24")String campo24,
            @Param(name = "campo25")String campo25,
            @Param(name = "campo26")String campo26,
            @Param(name = "campo27")String campo27,
            @Param(name = "campo28")String campo28,
            @Param(name = "campo29")String campo29
    ){
        boolean arquivoExiste = false;
        preparaCenario();
        selecionaDataInicialFinalConstatacao();
        pageRelatoriosMamb.selecionaValorCombo("campoTipoFisc",Tipo);
        pageRelatoriosMamb.selecionaValorCombo("campogrupoFisc",Grupo);
        esperaJanelaCarregar(1000);
        if(pageRelatoriosMamb.campoEstaHabilitado(By.id("campoSubGrupo")))
            pageRelatoriosMamb.selecionaValorCombo("campoSubGrupo", Subgrupo);

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
            listaParametros.add(campo21);
            listaParametros.add(campo22);
            listaParametros.add(campo23);
            listaParametros.add(campo24);
            listaParametros.add(campo25);
            listaParametros.add(campo26);
            listaParametros.add(campo27);
            listaParametros.add(campo28);
            listaParametros.add(campo29);

            if (listaParametros.get(indexListaParametros).equalsIgnoreCase("null")){
                String textoColunaObtido = pageRelatoriosMamb.obterTextoDoElemento("//div[@id='tabelaResultado']" +
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
        pageRelatoriosMamb.clicarBotaoGerarRelatorio();
        boolean exportarPDFExiste = pageRelatoriosMamb.rolarATelaAteExportarPDF();
        assertEquals(true, exportarPDFExiste);
        verificaDiretorioRelatorio();
        if (exportarPDFExiste){
            pageRelatoriosMamb.clicarBotaoExportarPDF();
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
        pageRelatoriosMamb.datePickerSelect(PathsAssistant.WEB_DATEPICKER_CONSTATACAO_INICIAL);
        pageRelatoriosMamb.opcaoMesDatePicker("Jan");
        pageRelatoriosMamb.opcaoDataDatePicker("1");
        esperaJanelaCarregar(1000);

        //Data final de constatação
        pageRelatoriosMamb.datePickerSelect(PathsAssistant.WEB_DATEPICKER_CONSTATACAO_FINAL);
        pageRelatoriosMamb.opcaoMesDatePicker("Jun");
        pageRelatoriosMamb.opcaoDataDatePicker("5");
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
