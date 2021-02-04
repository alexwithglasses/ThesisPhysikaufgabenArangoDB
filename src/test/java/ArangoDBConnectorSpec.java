import databaseArangoDB.ArangoDBConnector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eine Verbindung zu einer ArangoDB Instanz ")
public class ArangoDBConnectorSpec {


    @DisplayName("kann mit fest definiertem User aufgebaute werden und eine fest definierte Datenbank ausgewählt werden.")
    @Test
    void beiAufgebauterVerbindungWirdEineDatenbankAusgewählt() throws IOException {

        ArangoDBConnector arangoConnector = new ArangoDBConnector();

        assertThat(arangoConnector.getArangoDBInstanz() != null).isEqualTo(true);
        assertThat(arangoConnector.getDatabaseHandler().getInfo().getName()).isEqualTo("physikaufgaben");

    }
}
