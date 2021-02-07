import com.arangodb.entity.VertexEntity;
import databaseArangoDB.ArangoDBSetup;
import databaseArangoDB.AufgabenGraph;
import modelPhysikaufgabe.AufgabenParameter;
import modelPhysikaufgabe.Aufgabenstellung;
import modelPhysikaufgabe.Fragestellung;
import modelPhysikaufgabe.PhysikAufgabe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@DisplayName("Der Graph \"Aufgaben\" ")
public class AufgabenGraphSpec {


    private ArrayList<AufgabenParameter> parameterAufgabe1;
    private Aufgabenstellung aufgabenstellungAufgabe1;
    private Fragestellung frageA;
    private Fragestellung frageB;
    private PhysikAufgabe aufgabe1;
    private ArangoDBSetup arangoConnection;
    private AufgabenGraph aufgabenGraph;

    @BeforeAll
    void connectToArango(){
        arangoConnection = new ArangoDBSetup();
        aufgabenGraph = new AufgabenGraph();
    }

    @BeforeEach
    void init(){
        parameterAufgabe1 = new ArrayList<>();
        parameterAufgabe1.add(new AufgabenParameter("m1", "g", 50.0F, 150.0F));
        parameterAufgabe1.add(new AufgabenParameter("t1", "min", 1F, 5F));
        parameterAufgabe1.add(new AufgabenParameter("S1", "Schwingungen", 60.0F, 120.0F));

        aufgabenstellungAufgabe1 = new Aufgabenstellung("Ein Federpendel mit der Masse {0} führt in {1} {2} aus.", parameterAufgabe1);

        frageA = new Fragestellung("a) Bestimmen Sie die Frequenz {0} der Schwingung.", new AufgabenParameter("f1", "Hz" ));
        frageB = new Fragestellung("b) Bestimmen Sie die Federkonstane {0} der Schwingung.", new AufgabenParameter("D1", "N/m"));


        aufgabe1 = new PhysikAufgabe(aufgabenstellungAufgabe1, frageA, frageB);

    }

    @DisplayName("kann einen Knoten der Collection \"Aufgabenstellungen\" enthalten.")
    @Test
    void erstelleKnotenInCollectionAufgabenstellungen(){

        //VertexEntity aufgabenstellung1 = new erstelleAufgabenstellungKnoten(aufgabenstellungAufgabe1);



    }

}
