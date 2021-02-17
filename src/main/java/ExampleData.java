import databaseArangoDB.ArangoDBSetup;
import databaseArangoDB.AufgabenArangoDAO;
import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.PhysikAufgabe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExampleData {

    static void erstelleTestdaten(){


        List<AufgabenParameter> parameterAufgabe1List = Arrays.asList(
                new AufgabenParameter("m1","Masse","m", "g", 50.0F, 150.0F, true),
                new AufgabenParameter("t1", "Zeit","t", "min", 1, 5, true),
                new AufgabenParameter("","Schwingungen","Schwingungen", "Schwingungen", 50, 150, true)
        );

        PhysikAufgabe aufgabe1 = new PhysikAufgabe(
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

        new AufgabenArangoDAO().createAufgabe(aufgabe1);



        //Aufgabe2

        List<AufgabenParameter> parameterAufgabe2List = Arrays.asList(
                new AufgabenParameter("m_1","Masse","m", "kg", 1, 10, true),
                new AufgabenParameter("D_1", "Federkonsante","D", "$\\frac{N}{m}$", 20, 60, true)
        );

        PhysikAufgabe aufgabe2 = new PhysikAufgabe(
                new Aufgabenstellung(
                        "A2",
                        "Ein Körper der Masse {0} hängt an einer Feder mit der Federkonstante {1} und schwingt.",
                        parameterAufgabe2List,
                        "Mechanik"
                ),
                new Fragestellung(
                        "A2a",
                        "a) Bestimmen Sie die Winkelgeschwindigkeit {0} des Körpers.",
                        Arrays.asList(new AufgabenParameter("$\\omega _1$", "Winkelgeschwindigkeit", "omega","$\\frac{\\text{rad}}{s}$", 0,0, true)),
                        "Mechanik"
                ),

                new Fragestellung(
                        "A2b",
                        "b) Bestimmen Sie seine Schwingfrequenz {0}",
                        Collections.singletonList(new AufgabenParameter("f1", "Frequenz", "f", "Hz", 0,0,true)),
                        "Mechanik"
                ),

                new Fragestellung(
                        "A2c",
                        "c) Bestimmen Sie die dazugehörige Schwingdauer {0}",
                        Collections.singletonList(new AufgabenParameter("T1", "Periodendauer", "T", "s", 0,0,true)),
                        "Mechanik"
                )

        );

        new AufgabenArangoDAO().createAufgabe(aufgabe2);



    }

}
