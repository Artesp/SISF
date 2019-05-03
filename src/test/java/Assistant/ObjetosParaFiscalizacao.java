package Assistant;

import Core.BasePage;
import Core.BaseTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ObjetosParaFiscalizacao {

    //Hora atual
    public String HORA = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

    //Autenticação
    public static String usuario = "automacao_sisf"; /*artesp/*automacao_sisf*//*testeconservacao*/
    public static String senha = "12345678";

    //RA para testes: CAllBox
    public static String RA = "010008002531";

    // Seção Rodovia
    public String Concessionaria = "VIAOESTE";
    public String RodoviaSP = "SP300";
    public String locFiscInicial = "1";
    public String mtsInicial = "00";/*1*/
    public String locFiscIFinal = "14";/*14*/
    public String mtsFinal = "00";
    public String sentido = "L-Leste";
    public String localizacao = "Teste";

    // Seção Call box
    public String dTransmissao = "Adequado";
    public String dConservacao = "Adequado";
    public String dSeguranca = "Adequado";
    public String dInstalacao = "Adequado";

    //Seção Observação da Fiscalização
    public String obsFiscalizacao = "";

}
