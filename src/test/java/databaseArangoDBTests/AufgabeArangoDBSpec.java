package databaseArangoDBTests;

import com.arangodb.ArangoCursor;
import com.arangodb.entity.BaseDocument;
import databaseArangoDB.ArangoDBSetup;
import databaseArangoDB.AufgabeArangoDB;
import databaseArangoDB.GraphEntitiesUtility;
import modelPhysikaufgabe.AufgabenParameter;
import modelPhysikaufgabe.Aufgabenstellung;
import modelPhysikaufgabe.Fragestellung;
import modelPhysikaufgabe.PhysikAufgabe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;

public class AufgabeArangoDBSpec {

    public static ArangoDBSetup physikaufgabenSetup;

    @BeforeAll
    static void init(){
        physikaufgabenSetup = new ArangoDBSetup();

        List<AufgabenParameter> parameterAufgabe1List = Arrays.asList(
                new AufgabenParameter("m1","Masse","m", "g", 50.0F, 150.0F),
                new AufgabenParameter("t1", "Zeit","t", "min", 1, 5),
                new AufgabenParameter("","Schwingungen","Schwingungen", "Schwingungen", 50, 150)
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
                        Arrays.asList(new AufgabenParameter("f1", "Frequenz", "f","Hz", 0,0),
                                new AufgabenParameter("D1", "Federkonstante", "D", "N/m", 0,0)),
                        "Mechanik"
                )

        );

        GraphEntitiesUtility.createAufgabe(aufgabe, physikaufgabenSetup);


    }

    @Test
    void erhalteFragestellungenOhneParameter(){
        ArangoCursor<String> query = physikaufgabenSetup.getDatabaseHandler().query(
                "FOR v, e IN 1..1  OUTBOUND 'Aufgabenstellungen/A1' HatFragestellung RETURN v.text",
                null, null, String.class
        );

        assertThat(query.asListRemaining(), hasItems("a) Bestimmen Sie die Frequenz {0} und die Federkonstante {1} der Schwingung."));
    }

    @Test
    void erhalteFragestellungenMitParameterAlsString(){

              ArrayList<String> fragen =  AufgabeArangoDB.getFragestellungenAlsString("Aufgabenstellungen/A1", physikaufgabenSetup);

              assertThat(fragen, hasItems("a) Bestimmen Sie die Frequenz f1 und die Federkonstante D1 der Schwingung."));


    }

    @Test
    void erhalteAufgabenstellungMitParameterAlsString(){

        String aufgabenstellung = AufgabeArangoDB.getAufgabenstellungAlsString("Aufgabenstellungen/A1", physikaufgabenSetup);

        System.out.println(aufgabenstellung);

        assertThat(aufgabenstellung, notNullValue());

    }

    @Test
    void erhalteKompletteAufgabeAlsString(){

        String aufgabe = AufgabeArangoDB.getAufgabeAlsString("Aufgabenstellungen/A1", physikaufgabenSetup);

        System.out.println(aufgabe);

        assertThat(aufgabe, notNullValue());

    }




    @AfterAll
    public static void teardown(){


        physikaufgabenSetup.getDatabaseHandler().collection("Aufgabenstellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Parameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GegebeneParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("GesuchteParameter").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("Fragestellungen").drop();
        physikaufgabenSetup.getDatabaseHandler().collection("HatFragestellung").drop();

        physikaufgabenSetup.getGraph().drop();





        physikaufgabenSetup.getArangoDBInstanz().shutdown();
    }
}
