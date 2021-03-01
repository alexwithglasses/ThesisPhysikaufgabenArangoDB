package databaseArangoDBTests;

import datenbankArangoDB.ArangoDBSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eine neue ArangoDB Instanz ")
public class ArangoDBSetupSpec {

    public static ArangoDBSetup arangoConnector;

    @BeforeAll
    static void init(){
        arangoConnector = new ArangoDBSetup();
    }


    @DisplayName("wird mit einem fest definierten User aufgebaut und enthält eine fest definierte Datenbank.")
    @Test
    void beiAufgebauterVerbindungWirdEineDatenbankAusgewählt(){

        assertThat(arangoConnector.getArangoDBInstanz() != null).isEqualTo(true);
        assertThat(arangoConnector.getDatabaseHandler().getInfo().getName()).isEqualTo("physikaufgaben");

    }

    @DisplayName("enthält einen Graph mit Knoten und Kanten Collections.")
    @Test
    void arangoDBInstanzEnthaeltGraphMitKnotenUndKanten(){

        assertThat(arangoConnector.getGraph().getVertexCollections().containsAll(Arrays.asList("Aufgabenstellungen", "Einheiten")));
        assertThat(arangoConnector.getGraph().getEdgeDefinitions().containsAll(Arrays.asList("Fragestellungen", "GegebeneParameter")));


    }

    @AfterAll
    static void teardown(){
        arangoConnector.getArangoDBInstanz().shutdown();
    }
}
