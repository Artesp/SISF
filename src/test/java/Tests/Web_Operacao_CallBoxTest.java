package Tests;

import Core.BaseTestWeb;
import Pages.ModulosWebPage;
import Pages.Web_Operacao_CallBoxPage;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.util.Assert.isTrue;

public class Web_Operacao_CallBoxTest extends BaseTestWeb {

    private Web_Operacao_CallBoxPage page = new Web_Operacao_CallBoxPage();
    private ModulosWebPage modulosWebPage = new ModulosWebPage();

    @Test //"MTM_ID 3803: X - Consultar Fiscalização - Call Box"
    public void consultaFiscalizacao_CallBox(){
        //1 - Acessar o módulo de fiscalizações Enviadas
        // Esperado: O sistema apresenta a grid de resultados e filtros para pesquisa de fiscalizações
        page.logarWeb();
        modulosWebPage.clicarBotaoFiscalizacaoEnviadas();
        String textoElemento = page.obterTextoElemento("//*[@class='fonte_titulo']");
        isTrue(!textoElemento.isEmpty(), "Deve retornar o texto da tela de consulta na Web");
        //2 - Filtrar para que seja retornado as fiscalizações de Call Box e clicar em consultar.
        //Esperado: O sistema retornas todas as fiscalizações de Call Box de acordo com o filtro realizado
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        //3 - Clicar no botão Detalhar de uma das fiscalizações da Grid
        //Esperado: O sistema exibe a tela de consulta da fiscalização de Call Box
        page.botaoFiltrar();
        textoElemento = page.obterTextoElemento("//*[@class='fonte_titulo_resultado']");
        isTrue(!textoElemento.isEmpty(), "Deve retornar 'Resultado de Consulta'");
        //4 - Clicar no botão Editar de uma das fiscalizações da Grid
        // Esperado: O sistema exibe a tela de edição da fiscalização de Call Box.
        page.botaoEditar();
        textoElemento = page.obterTextoElemento("//*[@class='fonte_atividade']");
        assertEquals("- Alterar Fiscalização", textoElemento);
        page.botaoVoltar();
        page.botaoSimConfirmacao();
        //5 - Clicar no botão Detalhar de uma das fiscalizações da Grid
        //Esperado: O sistema exibe a tela de detalhamento da fiscalização de Call Box.
        page.botaoDetalhar();
        textoElemento = page.obterTextoElemento("//*[@class='fonte_atividade']");
        assertEquals("- Detalhar Fiscalização", textoElemento);
    }

    @Test //"MTM_ID 3804: RVN1 - Verificar alteração dos campos Padrões"
    public void verificarAlteracao_CamposPadrões(){
        //1 - Acessar a tela de Edição da fiscalização e verificar alteração dos campos:
        //      Código da Fsicalização, Tipo, Grupo, Data, Hora, Data de Envio, Hora de Envio,
        //      Concessionária, Lote, Inicial, Mts Final, Mts Inicial, Local, Rodovia (SP), Rodovia,
        //      Sentido, Extensão , Empresa e Responsável Técnico.
        // Esperado: O sistema não deve permitir a edição dos campos padrões descritos no passo.
        preparaCenario();
        page.botaoEditar();
        String[] listaPathsCampos = geraListaCamposNaoEditaveis();
        boolean existeCampoPadraoEditavel = page.verificaCamposNaoEditaveis(listaPathsCampos);
        isTrue(!existeCampoPadraoEditavel, "Não deve existir campos padrões editaveis na tela de alteração");
    }




    private String[] geraListaCamposNaoEditaveis() {
        String[] paths = new String[24];
        paths[0]="//input[contains(@id, 'codigoFiscalizacao')]";
        paths[1]="//input[contains(@id, 'tipo')]";
        paths[2]="//input[contains(@id, 'grupo')]";
        paths[3]="//input[contains(@id, 'data')]";
        paths[4]="//input[contains(@id, 'hora')]";
        paths[5]="//input[contains(@id, 'dataEnvio')]";
        paths[6]="//input[contains(@id, 'horaEnvio')]";
        paths[7]="//input[contains(@id, 'textoConcessionaria')]";
        paths[8]="//input[contains(@id, 'lote')]";
        paths[9]="//input[contains(@id, 'inicial')]";
        paths[10]="//input[contains(@id, 'inicialMetros')]";
        paths[11]="//input[contains(@id, 'campoFinal')]";
        paths[12]="//input[contains(@id, 'finalMetros')]";
        paths[13]="//input[contains(@id, 'local')]";
        paths[14]="//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:textoRodoviaSP:campoEntrada')]";
        paths[15]="//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:textoRodovia:campoEntrada')]";
        paths[16]="//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:inicial:campoEntrada')]";
        paths[17]="//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:inicialMetros:campoEntrada')]";
        paths[18]="//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:finalMetros:campoEntrada')]";
        paths[19]="//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:campoFinal:campoEntrada')]";
        paths[20]="//select[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:sentido:campoEntrada')]";
        paths[21]="//input[contains(@name, 'baseCabecalhoFiscalizacaoPanel:rodoviaPainel:extensao:campoEntrada')]";
        paths[22]="//input[contains(@id, 'EAF')]";
        paths[23]="//input[contains(@id, 'responsavelTecnico')]";
        return paths;
    }

    private void preparaCenario(){
        page.logarWeb();
        modulosWebPage.clicarBotaoFiscalizacaoEnviadas();
        page.selecionaTipoFiscalizacaoOperacao();
        page.selecionaGrupoFiscalizacaoCallBox();
        page.botaoFiltrar();
    }


}
