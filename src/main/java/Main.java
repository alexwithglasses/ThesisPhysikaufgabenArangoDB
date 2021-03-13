import datenbankArangoDB.ArangoDBSetup;
import datenbankArangoDB.AufgabenArangoDAO;
import fachlogikPhysikaufgaben.Physikaufgabe;
import outputLatex.AufgabeNachTeXDatei;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        ArangoDBSetup arangoDBSetup = new ArangoDBSetup();

        arangoDBSetup.setupCollectionsAndGraph();

        List<Physikaufgabe> aufgabenErstellt = ExampleData.erstelleTestdaten();

        LinkedList<String> aufgabenGelesen = new LinkedList<>();

        for (Physikaufgabe aufgabe : aufgabenErstellt
             ) {
            aufgabenGelesen.add(AufgabenArangoDAO.readAufgabe("Aufgabenstellungen/" +aufgabe.getAufgabenstellung().getId()).toString());
        }


        AufgabeNachTeXDatei.writeStringToTexFile(aufgabenGelesen);



        ExampleData.loescheTestdaten();

    }
}
