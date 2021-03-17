package fachlogikPhysikaufgaben;

import java.util.Random;

public class AufgabenParameter {

    private String bezeichner;
    private String bezeichnung;
    private String formelsymbol;
    private String einheit;
    private double zahlenwert;
    private double untereSchrankeZahlenwert;
    private double obereSchrankeZahlenwert;
    private boolean ganzeZahl;


    public AufgabenParameter(String bezeichner, String einheit){
        this.bezeichner = bezeichner;
        this.einheit = einheit;
    }

    public AufgabenParameter(String formelsymbol, String bezeichnung, String bezeichner, String einheit, double zahlenwert, boolean ganzeZahl){
        this.formelsymbol = formelsymbol;
        this.bezeichnung = bezeichnung;
        this.bezeichner = bezeichner;
        this.einheit = einheit;
        this.zahlenwert = zahlenwert;
        this.ganzeZahl = ganzeZahl;
    }

    public AufgabenParameter(String formelsymbol, String bezeichnung, String bezeichner, String einheit, double untereSchrankeZahlenwert, double obereSchrankeZahlenwert, boolean ganzeZahl){
        this.formelsymbol = formelsymbol;
        this.bezeichnung = bezeichnung;
        this.bezeichner = bezeichner;
        this.einheit = einheit;
        this.untereSchrankeZahlenwert = untereSchrankeZahlenwert;
        this.obereSchrankeZahlenwert = obereSchrankeZahlenwert;
        this.ganzeZahl = ganzeZahl;

        this.generiereZahlenwert();
    }

    public void generiereZahlenwert(){
        Random r = new Random();
        if(ganzeZahl){
            zahlenwert = r.ints(1,(int) untereSchrankeZahlenwert, (int)obereSchrankeZahlenwert+1).findFirst().getAsInt();
        }else {
            zahlenwert = r.doubles(1, untereSchrankeZahlenwert, obereSchrankeZahlenwert+1).findFirst().getAsDouble();
        }
    }

    @Override
    public String toString(){
        if(isGanzeZahl()){
            return (int) zahlenwert + " " + einheit;
        } else {
            return String.format("%.2f", zahlenwert) + " " + einheit;
        }
    }

    public boolean isGanzeZahl() {
        return ganzeZahl;
    }

    public void setBezeichner(String bezeichner) {
        this.bezeichner = bezeichner;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getFormelsymbol() {
        return formelsymbol;
    }

    public void setFormelsymbol(String formelsymbol) {
        this.formelsymbol = formelsymbol;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public void setZahlenwert(float zahlenwert) {
        this.zahlenwert = zahlenwert;
    }

    public void setUntereSchrankeZahlenwert(float untereSchrankeZahlenwert) {
        this.untereSchrankeZahlenwert = untereSchrankeZahlenwert;
    }

    public void setObereSchrankeZahlenwert(float obereSchrankeZahlenwert) {
        this.obereSchrankeZahlenwert = obereSchrankeZahlenwert;
    }

    public double getZahlenwert() {
        return zahlenwert;
    }

    public double getUntereSchrankeZahlenwert() {
        return untereSchrankeZahlenwert;
    }

    public double getObereSchrankeZahlenwert() {
        return obereSchrankeZahlenwert;
    }

    public String getBezeichner(){ return bezeichner;}

}
