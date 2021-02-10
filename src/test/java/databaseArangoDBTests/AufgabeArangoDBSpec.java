package databaseArangoDBTests;

import com.arangodb.ArangoCursor;
import databaseArangoDB.ArangoDBSetup;
import databaseArangoDB.GraphEntitiesUtility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

public class AufgabeArangoDBSpec {

    public static ArangoDBSetup physikaufgabenSetup;

    @BeforeAll
    static void init(){
        physikaufgabenSetup = new ArangoDBSetup();

        String aufgabenstellung1 = GraphEntitiesUtility.erstelleKnotenAufgabenstellung(
                physikaufgabenSetup.getDatabaseHandler().collection("Aufgabenstellungen"),
                "A1",
                "Ein Federpendel mit der Masse {0} führt in {1} {2} aus.",
                "Mechanik"
        ).getId();

        String parameterMasse = GraphEntitiesUtility.erstelleKnotenParameter(
                physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "m",
                "Masse"
        ).getId();

        String parameterZeit = GraphEntitiesUtility.erstelleKnotenParameter(
                physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "t",
                "Zeit"
        ).getId();

        String parameterSchwingungen = GraphEntitiesUtility.erstelleKnotenParameter(
                physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "Schwingungen",
                "Schwingungen"
        ).getId();

        GraphEntitiesUtility.erstelleKanteParameter(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("GegebeneParameter"),
                aufgabenstellung1,
                parameterMasse,
                50,
                150,
                "g",
                "m"
        );

        GraphEntitiesUtility.erstelleKanteParameter(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("GegebeneParameter"),
                aufgabenstellung1,
                parameterZeit,
                1,
                5,
                "min",
                "t"
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

        String fragestellungA1a = GraphEntitiesUtility.erstelleKnotenFragestellung(
                physikaufgabenSetup.getDatabaseHandler().collection("Fragestellungen"),
                "A1a",
                "a) Bestimmen Sie die Frequenz {0} der Schwingung.",
                "Mechanik"
        ).getId();

        String parameterFrequenz = GraphEntitiesUtility.erstelleKnotenParameter(
                physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "f",
                "Frequenz"
        ).getId();

        GraphEntitiesUtility.erstelleKanteParameter(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("GesuchteParameter"),
                fragestellungA1a,
                parameterFrequenz,
                0,
                0,
                "Hz",
                "f"
        );

        GraphEntitiesUtility.erstelleKanteFragestellung(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("HatFragestellung"),
                aufgabenstellung1,
                fragestellungA1a
        );

        String fragestellungA1b = GraphEntitiesUtility.erstelleKnotenFragestellung(
                physikaufgabenSetup.getDatabaseHandler().collection("Fragestellungen"),
                "A1b",
                "b) Bestimmen Sie die Federkonstane {0} der Schwingung.",
                "Mechanik"
        ).getId();

        String parameterFederkonstante = GraphEntitiesUtility.erstelleKnotenParameter(
                physikaufgabenSetup.getDatabaseHandler().collection("Parameter"),
                "D",
                "Federkonstante"
        ).getId();

        GraphEntitiesUtility.erstelleKanteParameter(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("GesuchteParameter"),
                fragestellungA1b,
                parameterFederkonstante,
                0,
                0,
                "N/m",
                "D"
        );

        GraphEntitiesUtility.erstelleKanteFragestellung(
                physikaufgabenSetup.getDatabaseHandler().graph("aufgaben").edgeCollection("HatFragestellung"),
                aufgabenstellung1,
                fragestellungA1b

        );
    }

    @Test
    void erhalteFragestellungenAnhandDerAufgabenstellungMitEingesetztemParameter(){
        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "FOR v, e IN 1..1 OUTBOUND 'Aufgabenstellungen/A1' HatFragestellung RETURN v.text",
                null, null, String.class
        );

        assertThat(query.asListRemaining(), hasItems("a) Bestimmen Sie die Frequenz {0} der Schwingung.","b) Bestimmen Sie die Federkonstane {0} der Schwingung."));
    }




    @AfterAll
    public static void teardown(){
/*
        physikaufgabenSetup.getDatabaseHandler().collection("Aufgabenstellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Parameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GegebeneParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GesuchteParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Fragestellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("HatFragestellung").drop();

        physikaufgabenSetup.getGraph().drop();


 */
        physikaufgabenSetup.getArangoDBInstanz().shutdown();
    }
}
