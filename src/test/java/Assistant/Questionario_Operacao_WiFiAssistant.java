package Assistant;

public class Questionario_Operacao_WiFiAssistant {

    public enum QUESTINARIO_WIFI {
        HOUVE_CONEXAO{
            public String toString() {
                return "0";
            }
        },

        HOUVE_ATENDIMENTO {
            public String toString() {
                return "1";
            }
        },

        QUALIDADE_DA_COMUNICACAO {
            public String toString() {
                return "2";
            }
        },

        ENCERRAMENTO_DE_CHAMADA{
            public String toString() {
                return "3";
            }
        },
    }

}
