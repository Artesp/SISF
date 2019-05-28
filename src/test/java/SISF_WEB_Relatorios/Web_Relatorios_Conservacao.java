package SISF_WEB_Relatorios;

import Assistant.ObjetosParaFiscalizacao;
import Core.BaseTestWeb;
import Pages.LoginWebPage;
import Pages.ModulosWebPage;
import SISF_WEB_RelatoriosPages.Web_Relatorios_ConservacaoPage;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "Web_Relatorios_Conservacao.csv")
public class Web_Relatorios_Conservacao extends BaseTestWeb {

    private ModulosWebPage webPageModulos = new ModulosWebPage();
    private LoginWebPage webLoginPage = new LoginWebPage();
    private Web_Relatorios_ConservacaoPage pageRelatoriosCosnervacao = new Web_Relatorios_ConservacaoPage();

    @Test
    public void verificarGridDeGrupos_Relatorios(
            @Param(name = "Tipo")String Tipo,
            @Param(name = "Grupo")String Grupo,
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
            @Param(name = "campo17")String campo17
    ){
        preparaCenario();
        pageRelatoriosCosnervacao.selecionaValorCombo("campoTipoFisc",Tipo);
        pageRelatoriosCosnervacao.selecionaValorCombo("campogrupoFisc",Grupo);

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

            if (listaParametros.get(indexListaParametros)==null){
            String textoColunaObtido = pageRelatoriosCosnervacao.obterTextoDoElemento("//div[@id='tabelaResultado']" +
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

        pageRelatoriosCosnervacao.clicarBotaoGerarRelatorio();

        boolean exportarPDFExiste = pageRelatoriosCosnervacao.rolarATelaAteExportarPDF();
        assertEquals(true, exportarPDFExiste);
        if (exportarPDFExiste){
            pageRelatoriosCosnervacao.clicarBotaoExportarPDF();

        }


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
