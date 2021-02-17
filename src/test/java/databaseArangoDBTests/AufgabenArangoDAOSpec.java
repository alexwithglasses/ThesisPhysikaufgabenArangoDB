package databaseArangoDBTests;

import com.arangodb.ArangoCursor;
import databaseArangoDB.AufgabenArangoDAO;
import databaseArangoDB.ArangoDBSetup;
import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.PhysikAufgabe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AufgabenArangoDAOSpec {


    static ArangoDBSetup physikaufgabenSetup;

    @BeforeAll
    static void init(){

        physikaufgabenSetup = new ArangoDBSetup();

        physikaufgabenSetup.setupCollectionsAndGraph();

        List<AufgabenParameter> parameterAufgabe1List = Arrays.asList(
                new AufgabenParameter("m1","Masse","m", "g", 50.0F, 150.0F, true),
                new AufgabenParameter("t1", "Zeit","t", "min", 1, 5, true),
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

        new AufgabenArangoDAO().createAufgabe(aufgabe);

    }

    @Test
    void lesenEinerPhysikaufgabeAnhandDerAufgabenstellungID(){

        PhysikAufgabe aufgabe = AufgabenArangoDAO.readAufgabe("Aufgabenstellungen/A1");

        System.out.println(aufgabe.toString());

        assertThat(aufgabe.getAufgabenstellung(), notNullValue());

    }


    @Test
    void aufgabenstellungDocumentWirdInCollectionAufgabenstellungGeladen(){


        physikaufgabenSetup.verbinden();

        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "RETURN DOCUMENT('Aufgabenstellungen/A1')._key",
                null, null, String.class
        );

        physikaufgabenSetup.schließen();

        assertThat(query.asListRemaining()).contains("A1");
    }

    @Test
    void einheitDocumentWirdInCollectionAufgabenstellungGeladen(){


        physikaufgabenSetup.verbinden();

        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "RETURN DOCUMENT('Parameter/m').bezeichnung",
                null, null, String.class);

        assertThat(query.asListRemaining()).contains("Masse");

        physikaufgabenSetup.schließen();
    }

    @Test
    void attributeEinerKanteWerdenAusgelesen(){


        physikaufgabenSetup.verbinden();

        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "FOR vertex, edge IN  OUTBOUND 'Aufgabenstellungen/A1' GRAPH 'aufgaben' return edge.formelsymbol",
                null, null, String.class
        );

        physikaufgabenSetup.schließen();

        assertThat(query.asListRemaining(), hasItems("m1", "t1", ""));

        }


    @AfterAll
    public static void teardown(){

        physikaufgabenSetup.verbinden();


        physikaufgabenSetup.getDatabaseHandler().collection("Aufgabenstellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Parameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GegebeneParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Fragestellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GesuchteParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("HatFragestellung").drop();

        physikaufgabenSetup.getGraph().drop();

        physikaufgabenSetup.schließen();
    }
}
