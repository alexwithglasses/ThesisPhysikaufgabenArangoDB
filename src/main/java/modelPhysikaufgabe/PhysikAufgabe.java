package modelPhysikaufgabe;


import java.util.ArrayList;
import java.util.List;

public class PhysikAufgabe {

    private Aufgabenstellung aufgabenstellung;
    private ArrayList<Fragestellung> fragestellungList;


    public PhysikAufgabe(Aufgabenstellung aufgabenstellung, Fragestellung... fragestellungen){
        this.aufgabenstellung = aufgabenstellung;
        fragestellungList = new ArrayList<>();

        for (Fragestellung fragestellung: fragestellungen) {
            fragestellungList.add(fragestellung);
        }
    }

    @Override
    public String toString(){

        StringBuilder aufgabenstellungFragestellungString = new StringBuilder();

        aufgabenstellungFragestellungString.append(aufgabenstellung.toString());
        aufgabenstellungFragestellungString.append(System.getProperty("line.seperator"));

        for (Fragestellung fragestellung: fragestellungList
             ) {
            aufgabenstellungFragestellungString.append(fragestellung.toString());
            aufgabenstellungFragestellungString.append(System.getProperty("line.seperator"));
        }

        return aufgabenstellungFragestellungString.toString();

    }

    public Aufgabenstellung getAufgabenstellung(){
        return aufgabenstellung;
    }

    public ArrayList<Fragestellung> getFragestellungList(){ return fragestellungList;}
}
