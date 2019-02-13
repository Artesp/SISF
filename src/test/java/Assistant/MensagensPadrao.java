package Assistant;

public class MensagensPadrao {

    public enum EXPECTEDS {
        WIFI {
            public String toString() {
                return "Wi-Fi";
            }
        },

        RODOVIA{
          public String toString(){
              return "Rodovia";
            }
        },

        OBS_FISCALIZACAO{
            public String toString(){
                return "Observação da Fiscalização";
            }
        },

        GALERIA{
            public String toString(){
                return "Galeria";
            }
        },

        MEDICAO_WIFI_ERRO_DE_VALIDACAO{
            public String toString(){return "Dados da rodovia inválidos ('km' e 'm' fora do limite da rodovia).";}
        },

        MEDICAO_WIFI_LIMITE_MAXIMO_PERMITIDO{
            public String toString(){
                return "Não pode exceder número máximo de medições: 50";
            }
        },

        QUESTIONARIO_HOUVE_CONEXAO_AUSENCIA_DE_SINAL{
          public String toString(){return " Ausência de Sinal";}
        },

        QUESTIONARIO_HOUVE_CONEXAO_SINAL_INTERMITENTE{
            public String toString(){return " Sinal intermitente";}
        },

        QUESTIONARIO_HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA{
            public String toString(){return " Não Realiza Chamada";}
        },

        QUESTIONARIO_HOUVE_ATENDIMENTO_SEM_ATENDIMENTO_CCO{
            public String toString(){return " Sem Atendimento pelo CCO";}
        },

        QUESTIONARIO_QUALIDADE_COMUNICACAO_AUDIO_BAIXO{
            public String toString(){return " Áudio Baixo";}
        },

        QUESTIONARIO_QUALIDADE_COMUNICACAO_AUDIO_INTERMITENTE{
            public String toString(){return " Áudio Intermitente";}
        },

        QUESTIONARIO_QUALIDADE_COMUNICACAO_RUIDO_INTERFERENCIA{
            public String toString(){return " Ruído/Interferência";}
        },

        QUESTIONARIO_QUALIDADE_COMUNICACAO_ATENDENTE_NAO_ESCUTA{
            public String toString(){return " Atendente Não Escuta";}
        }

    }

}
