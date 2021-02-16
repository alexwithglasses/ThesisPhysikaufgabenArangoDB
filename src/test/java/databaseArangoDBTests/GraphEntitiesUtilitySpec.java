package databaseArangoDBTests;

import com.arangodb.ArangoCursor;
import com.arangodb.entity.BaseEdgeDocument;
import databaseArangoDB.GraphEntitiesUtility;
import databaseArangoDB.ArangoDBSetup;
import modelPhysikaufgabe.AufgabenParameter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GraphEntitiesUtilitySpec {

    public static ArangoDBSetup physikaufgabenSetup;

    @BeforeAll
    static void init(){
        physikaufgabenSetup = new ArangoDBSetup();

        String aufgabenstellung1 = GraphEntitiesUtility.erstelleKnotenAufgabenstellung(
                physikaufgabenSetup.getDatabaseHandler().collection("Aufgabenstellungen"),
                "A1",
                "Ein Federpendel mit der Masse {0} führt in {1} {2} aus.",
                "Mechanik"
        );

        String parameterMasse = GraphEntitiesUtility.erstelleKnotenParameter(
            physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "m",
                "Masse"
        );

        String parameterZeit = GraphEntitiesUtility.erstelleKnotenParameter(
                physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "t",
                "Zeit"
        );

        String parameterSchwingungen = GraphEntitiesUtility.erstelleKnotenParameter(
                physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "Schwingungen",
                "Schwingungen"
        );

        GraphEntitiesUtility.erstelleKanteParameter(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("GegebeneParameter"),
                aufgabenstellung1,
                parameterMasse,
                50,
                150,
                "g",
                "Gramm"
        );

        GraphEntitiesUtility.erstelleKanteParameter(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("GegebeneParameter"),
                aufgabenstellung1,
                parameterZeit,
                1,
                5,
                "min",
                "Minuten"
        );

        GraphEntitiesUtility.erstelleKanteParameter(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("GegebeneParameter"),
                aufgabenstellung1,
                parameterSchwingungen,
                50,
                150,
                "Schwingungen",
                "Schwingungen"
        );
    }


    @Test
    void aufgabenstellungDocumentWirdInCollectionAufgabenstellungGeladen(){

        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "RETURN DOCUMENT('Aufgabenstellungen/A1')._key",
                null, null, String.class
        );

        assertThat(query.asListRemaining()).contains("A1");
    }

    @Test
    void einheitDocumentWirdInCollectionAufgabenstellungGeladen(){

        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "RETURN DOCUMENT('Parameter/m').bezeichnung",
                null, null, String.class);

        assertThat(query.asListRemaining()).contains("Masse");
    }

    @Test
    void attributeEinerKanteWerdenAusgelesen(){

        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "FOR vertex, edge IN  OUTBOUND 'Aufgabenstellungen/A1' GRAPH 'aufgaben' return edge.bezeichnungParameter",
                null, null, String.class
        );

        assertThat(query.asListRemaining(), hasItems("Gramm", "Minuten", "Schwingungen"));

        }


    @AfterAll
    public static void teardown(){

        physikaufgabenSetup.getDatabaseHandler().collection("Aufgabenstellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Parameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GegebeneParameter").drop();

        physikaufgabenSetup.getGraph().drop();

        physikaufgabenSetup.getArangoDBInstanz().shutdown();
    }
}
