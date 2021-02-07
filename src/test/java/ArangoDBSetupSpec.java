import databaseArangoDB.ArangoDBSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eine neue ArangoDB Instanz ")
public class ArangoDBSetupSpec {


    @DisplayName("wird mit einem fest definierten User aufgebaut und enthält eine fest definierte Datenbank.")
    @Test
    void beiAufgebauterVerbindungWirdEineDatenbankAusgewählt(){

        ArangoDBSetup arangoConnector = new ArangoDBSetup();

        assertThat(arangoConnector.getArangoDBInstanz() != null).isEqualTo(true);
        assertThat(arangoConnector.getDatabaseHandler().getInfo().getName()).isEqualTo("physikaufgaben");

    }

    @DisplayName("enthält einen Graph mit Knoten und Kanten Collections.")
    @Test
    void arangoDBInstanzEnthaeltGraphMitKnotenUndKanten(){

        ArangoDBSetup arangoConnector = new ArangoDBSetup();

        assertThat(arangoConnector.getGraph().getVertexCollections().containsAll(Arrays.asList("Aufgabenstellungen", "Einheiten")));
        assertThat(arangoConnector.getGraph().getEdgeDefinitions().containsAll(Arrays.asList("Fragestellungen", "GegebeneParameter")));


    }
}
