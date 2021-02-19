import databaseArangoDB.ArangoDBSetup;
import databaseArangoDB.AufgabenArangoDAO;
import fachlogikPhysikaufgaben.PhysikAufgabe;
import outputLatex.AufgabeNachTeXDatei;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        ArangoDBSetup arangoDBSetup = new ArangoDBSetup();

        arangoDBSetup.setupCollectionsAndGraph();

        List<PhysikAufgabe> aufgabenErstellt = ExampleData.erstelleTestdaten();

        LinkedList<String> aufgabenGelesen = new LinkedList<>();

        for (PhysikAufgabe aufgabe : aufgabenErstellt
             ) {
            aufgabenGelesen.add(AufgabenArangoDAO.readAufgabe("Aufgabenstellungen/" +aufgabe.getAufgabenstellung().getId()).toString());
        }


        AufgabeNachTeXDatei.writeStringToTexFile(aufgabenGelesen);



        ExampleData.loescheTestdaten();

    }
}
