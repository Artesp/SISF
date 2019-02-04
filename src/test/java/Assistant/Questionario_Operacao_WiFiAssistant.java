package Assistant;

public class Questionario_Operacao_WiFiAssistant {

    public enum QUESTINARIO_WIFI {

        HOUVE_CONEXAO{
            public String toString() {
                return "1";
            }
        },

        HOUVE_CONEXAO_AUSENCIA_SINAL{
            public String toString() {
                return "1";
            }
        },

        HOUVE_CONEXAO_SINAL_INTERMITENTE{
            public String toString() {
                return "2";
            }
        },

        CONFIRMACAO_LOCAL_FISCAL{
            public String toString() {
                return "2";
            }
        },

        HOUVE_ATENDIMENTO {
            public String toString() {
                return "3";
            }
        },

        HOUVE_ATENDIMENTO_NAO_REALIZA_CHAMADA {
            public String toString() {
                return "2";
            }
        },

        HOUVE_ATENDIMENTO_SEM_ATEMDIMENTO_CCO {
            public String toString() {
                return "3";
            }
        },

        QUALIDADE_DA_COMUNICACAO {
            public String toString() {
                return "4";
            }
        },

        QUALIDADE_DA_COMUNICACAO_AUDIO_BAIXO {
            public String toString() {
                return "4";
            }
        },

        QUALIDADE_DA_COMUNICACAO_AUDIO_INTERMITENTE {
            public String toString() {
                return "5";
            }
        },

        QUALIDADE_DA_COMUNICACAO_RUIDO_INTERFERENCIA {
            public String toString() {
                return "6";
            }
        },

        QUALIDADE_DA_COMUNICACAO_ATENDENTE_NAO_ESCUTA{
            public String toString() {
                return "7";
            }
        },

        ENCERRAMENTO_DE_CHAMADA{
            public String toString() {
                return "4";
            }
        },
    }

}
