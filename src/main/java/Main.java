import databaseArangoDB.ArangoDBSetup;
import databaseArangoDB.AufgabenArangoDAO;
import outputLatex.AufgabeNachTeXDatei;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        ArangoDBSetup arangoDBSetup = new ArangoDBSetup();

        arangoDBSetup.setupCollectionsAndGraph();

        ExampleData.erstelleTestdaten();

        List<String> aufgaben = new ArrayList<>();

        aufgaben.add(AufgabenArangoDAO.readAufgabe("Aufgabenstellungen/A1").toString());
        aufgaben.add(AufgabenArangoDAO.readAufgabe("Aufgabenstellungen/A2").toString());

        AufgabeNachTeXDatei.writeStringToTexFile(aufgaben);

    }
}
