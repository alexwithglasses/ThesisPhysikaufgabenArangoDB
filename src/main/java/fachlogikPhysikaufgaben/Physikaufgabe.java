package fachlogikPhysikaufgaben;


import java.util.ArrayList;

public class Physikaufgabe {

    private Aufgabenstellung aufgabenstellung;
    private ArrayList<Fragestellung> fragestellungList;


    public Physikaufgabe(Aufgabenstellung aufgabenstellung, Fragestellung... fragestellungen){
        this.aufgabenstellung = aufgabenstellung;
        fragestellungList = new ArrayList<>();

        for (Fragestellung fragestellung: fragestellungen) {
            fragestellungList.add(fragestellung);
        }
    }

    @Override
    public String toString(){

        StringBuilder aufgabeString = new StringBuilder();

        aufgabeString.append(
                "\\section*{"
                        + aufgabenstellung.getId()
                        + " "
                        + aufgabenstellung.getFachbereich()
                + "}");

        aufgabeString.append(System.lineSeparator());
        aufgabeString.append(aufgabenstellung.toString()+"\\\\");
        aufgabeString.append(System.lineSeparator());

        for (Fragestellung fragestellung: fragestellungList
             ) {
            aufgabeString.append(fragestellung.toString() +"\\\\");
            aufgabeString.append(System.lineSeparator());
        }

        return aufgabeString.toString();

    }

    public Aufgabenstellung getAufgabenstellung(){
        return aufgabenstellung;
    }

    public ArrayList<Fragestellung> getFragestellungList(){ return fragestellungList;}
}
