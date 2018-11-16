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
            public String toString(){return "É obrigatório preenchimento dos campos para adicionar um novo item";}
        }


    }

}
