package Assistant;

public class PathsAssistant {

    public enum PATHS_SISF_TABLET {
        WIFI_INTENSIDADE_DE_SINAL {
            public String toString() {
                return "br.gov.sp.artesp.sisf.mobile:id/intensidade_sinal";
            }
        },

        WIFI_DURACAO_CHAMADA {
            public String toString() {
                return "br.gov.sp.artesp.sisf.mobile:id/duracao_chamada";
            }
        }
    }
}
