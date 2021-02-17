package databaseArangoDBTests;

import com.arangodb.ArangoCursor;
import databaseArangoDB.ArangoDBSetup;
import databaseArangoDB.ReadAufgabenArangoUtility;
import databaseArangoDB.AufgabenArangoDAO;
import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.PhysikAufgabe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;

public class ReadAufgabenArangoUtilitySpec {

    public static ArangoDBSetup physikaufgabenSetup;

    @BeforeAll
    static void init(){
        physikaufgabenSetup = new ArangoDBSetup();

        physikaufgabenSetup.setupCollectionsAndGraph();

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

        AufgabenArangoDAO.createAufgabe(aufgabe);


    }

    @Test
    void erhalteFragestellungenOhneParameter(){

        physikaufgabenSetup.verbinden();

        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "FOR v, e IN 1..1  OUTBOUND 'Aufgabenstellungen/A1' HatFragestellung RETURN v.text",
                null, null, String.class
        );

        physikaufgabenSetup.schließen();

        assertThat(query.asListRemaining(), hasItems("a) Bestimmen Sie die Frequenz {0} und die Federkonstante {1} der Schwingung."));
    }

    @Test
    void erhalteFragestellungenMitParameterAlsString(){

              ArrayList<String> fragen =  ReadAufgabenArangoUtility.getFragestellungenAlsString("Aufgabenstellungen/A1", physikaufgabenSetup);

              assertThat(fragen, hasItems("a) Bestimmen Sie die Frequenz f1 und die Federkonstante D1 der Schwingung."));


    }


    @Test
    void erhalteAufgabenstellungMitParameterAlsString(){

        String aufgabenstellung = ReadAufgabenArangoUtility.getAufgabenstellungAlsString("Aufgabenstellungen/A1", physikaufgabenSetup);

        System.out.println(aufgabenstellung);

        assertThat(aufgabenstellung, notNullValue());

    }


    @Test
    void erhalteKompletteAufgabeAlsString(){

        String aufgabe = ReadAufgabenArangoUtility.getAufgabeAlsString("Aufgabenstellungen/A1", physikaufgabenSetup);

        System.out.println(aufgabe);

        assertThat(aufgabe, notNullValue());

    }




    @AfterAll
    public static void teardown(){

        physikaufgabenSetup.verbinden();

        physikaufgabenSetup.getDatabaseHandler().collection("Aufgabenstellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Parameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GegebeneParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GesuchteParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Fragestellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("HatFragestellung").drop();

        physikaufgabenSetup.getGraph().drop();

        physikaufgabenSetup.schließen();
    }
}
