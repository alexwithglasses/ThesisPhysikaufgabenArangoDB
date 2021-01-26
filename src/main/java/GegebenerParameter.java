import java.util.Random;

public class GegebenerParameter {

    private String bezeichner;
    private String einheit;
    private float zahlenwert;
    private float untereSchrankeZahlenwert;
    private float obereSchrankeZahlenwert;

    public GegebenerParameter(String bezeichner, String einheit, float zahlenwert){
        this.bezeichner = bezeichner;
        this.einheit = einheit;
        this.zahlenwert = zahlenwert;
    }

    public GegebenerParameter(String bezeichner, String einheit, float untereSchrankeZahlenwert, float obereSchrankeZahlenwert){
        this.bezeichner = bezeichner;
        this.einheit = einheit;
        this.untereSchrankeZahlenwert = untereSchrankeZahlenwert;
        this.obereSchrankeZahlenwert = obereSchrankeZahlenwert;

        this.generiereZahlenwert();
    }

    public void generiereZahlenwert(){
        Random r = new Random();
        zahlenwert = untereSchrankeZahlenwert + r.nextFloat() * (obereSchrankeZahlenwert - untereSchrankeZahlenwert);
    }

    @Override
    public String toString(){
        return String.format("%.2f", zahlenwert) + " " + einheit;
    }

    public float getZahlenwert() {
        return zahlenwert;
    }

    public float getUntereSchrankeZahlenwert() {
        return untereSchrankeZahlenwert;
    }

    public float getObereSchrankeZahlenwert() {
        return obereSchrankeZahlenwert;
    }

}
