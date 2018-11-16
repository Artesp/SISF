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
        }


    }

}
