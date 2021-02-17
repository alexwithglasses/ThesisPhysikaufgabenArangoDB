package databaseArangoDBTests;

import com.arangodb.ArangoCursor;
import databaseArangoDB.GraphEntitiesUtility;
import databaseArangoDB.ArangoDBSetup;
import modelPhysikaufgabe.AufgabenParameter;
import modelPhysikaufgabe.Aufgabenstellung;
import modelPhysikaufgabe.Fragestellung;
import modelPhysikaufgabe.PhysikAufgabe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GraphEntitiesUtilitySpec {

    public static ArangoDBSetup physikaufgabenSetup;

    @BeforeAll
    static void init(){
        physikaufgabenSetup = new ArangoDBSetup();

        List<AufgabenParameter> parameterAufgabe1List = Arrays.asList(
                new AufgabenParameter("m1","Masse","m", "g", 50.0F, 150.0F, true),
                new AufgabenParameter("t1", "Zeit","t", "min", 1, 5, false),
                new AufgabenParameter("","Schwingungen","Schwingungen", "Schwingungen", 50, 150, true)
        );

        PhysikAufgabe aufgabe = new PhysikAufgabe(
                new Aufgabenstellung(
                        "A1",
                        "Ein Federpendel mit der Masse {0} führt in {1} {2} aus.",
                        parameterAufgabe1List,
                        "Mechanik"
                ),
                new Fragestellung(
                        "A1a",
                        "a) Bestimmen Sie die Frequenz {0} und die Federkonstante {1} der Schwingung.",
                        Arrays.asList(new AufgabenParameter("f1", "Frequenz", "f","Hz", 0,0, true),
                                new AufgabenParameter("D1", "Federkonstante", "D", "N/m", 0,0, true)),
                        "Mechanik"
                )

        );

        GraphEntitiesUtility.createAufgabe(aufgabe, physikaufgabenSetup);
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
                "FOR vertex, edge IN  OUTBOUND 'Aufgabenstellungen/A1' GRAPH 'aufgaben' return edge.formelsymbol",
                null, null, String.class
        );

        assertThat(query.asListRemaining(), hasItems("m1", "t1", ""));

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
