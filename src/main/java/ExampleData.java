import datenbankArangoDB.ArangoDBSetup;
import datenbankArangoDB.AufgabenArangoDAO;
import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.Physikaufgabe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExampleData {

    static List<Physikaufgabe> erstelleTestdaten(){

        List<Physikaufgabe> aufgabenList = new ArrayList<>();

        //Aufgabe 1 B8 4

        List<AufgabenParameter> parameterAufgabe1List = Arrays.asList(
                new AufgabenParameter("m1","Masse","m", "g", 50.0F, 150.0F, true),
                new AufgabenParameter("t1", "Zeit","t", "min", 1, 5, true),
                new AufgabenParameter("","Schwingungen","Schwingungen", "Schwingungen", 50, 150, true)
        );

        Physikaufgabe aufgabe1 = new Physikaufgabe(
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

        aufgabenList.add(aufgabe1);



        //Aufgabe2 B8 1

        List<AufgabenParameter> parameterAufgabe2List = Arrays.asList(
                new AufgabenParameter("m_1","Masse","m", "kg", 1, 10, true),
                new AufgabenParameter("D_1", "Federkonsante","D", "$\\frac{N}{m}$", 20, 60, true)
        );

        Physikaufgabe aufgabe2 = new Physikaufgabe(
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

        aufgabenList.add(aufgabe2);


        //Aufgabe 3 B2 1
        List<AufgabenParameter> parameterAufgabe3List = Arrays.asList(
                new AufgabenParameter("m_1","Masse","m", "g", 200, 700, true),
                new AufgabenParameter("h_1", "Höhe","h", "m", 5, 50, true),
                new AufgabenParameter("v_1", "Geschwindigkeit", "v", "$\\frac{m}{s}$", 2,10,true)
        );

        Physikaufgabe aufgabe3 = new Physikaufgabe(
                new Aufgabenstellung(
                        "A3",
                        "Ein Ball mit der Masse {0} wird von einem Turm aus {1} Höhe senkrecht nach oben mit einer Anfangsgeschwindigkeit von {2} geworfen.",
                        parameterAufgabe3List,
                        "Mechanik"
                ),
                new Fragestellung(
                        "A3a",
                        "a) Berechnen Sie die erreichte Wurfhöhe {0}.",
                        Arrays.asList(new AufgabenParameter("$h_{max}$", "Höhe", "h","m", 0,0, true)),
                        "Mechanik"
                ),

                new Fragestellung(
                        "A3b",
                        "b) Berechnen Sie die Zeit {0} die verstreicht, bis der Ball seine maximale Wurfhöhe erreicht.",
                        Collections.singletonList(new AufgabenParameter("$t_{max}$", "Zeit", "t", "s", 0,0,true)),
                        "Mechanik"
                )
        );

        aufgabenList.add(aufgabe3);


        //Aufgabe 4 B2 4

        List<AufgabenParameter> parameterAufgabe4List = Arrays.asList(
                new AufgabenParameter("v_F","Geschwindigkeit","v", "$\\frac{m}{s}$", 1, 10, true),
                new AufgabenParameter("$\\alpha$", "Winkel","Winkel", "$^{\\circ}$", 10, 40, true),
                new AufgabenParameter("h_{Band}", "Höhe", "h", "m", 1,10, true),
                new AufgabenParameter("g", "Erdbeschleunigung", "g", "$\\frac{m}{s^2}$", 9.81F, true)
        );

        Physikaufgabe aufgabe4 = new Physikaufgabe(
                new Aufgabenstellung(
                        "A4",
                        "Auf einem schrägen Förderband wird ein Gut mit einer Geschwindigkeit $v_F=$ {0} transportiert." +
                                "Das Förderband steht in einem Winkel von {1}. Am Ende des Förderbands fliegt das Gut eintsprechend dem schiefen Wurf" +
                                "für eine Zeit $t_F$, bevor es nach einer Strecke $s$ am Boden aufprallt. Der Endpunkt des Förderbandes liegt in {2} Höhe. " +
                                "Als Erdbeschleunigung sei {3} anzunehmen.",
                        parameterAufgabe4List,
                        "Mechanik"
                ),
                new Fragestellung(
                        "A4a",
                        "a) Berechnen Sie die Geschwindigkeit {0} in horizontaler Richtung (entlang dem Weg s) und die Geschwindigkeit {1}" +
                                "in vertikaler Richtung direkt nach dem Verlassen des Förderbandes.",
                        Arrays.asList(new AufgabenParameter("$v_x$", "Geschwindigkeit", "v","$\\frac{m}{s}$", 0,0, true),
                                new AufgabenParameter("$v_y$", "Geschwindigkeit", "v","$\\frac{m}{s}$", 0,0, true)),
                        "Mechanik"
                ),

                new Fragestellung(
                        "A4b",
                        "b) Berechnen Sie die Höhe {0}, die das Gut zusätzlich erreicht.",
                        Collections.singletonList(new AufgabenParameter("$h_{steig}$", "Höhe", "h", "m", 0,0,true)),
                        "Mechanik"
                ),

                new Fragestellung(
                        "A4c",
                        "c) Berechnen Sie den Weg {0}, den das Gut auf seinem Flug zurücklegt.",
                        Collections.singletonList(new AufgabenParameter("s", "Strecke", "s", "m", 0,0,true)),
                        "Mechanik"
                )

        );

        aufgabenList.add(aufgabe4);





        for (Physikaufgabe aufgabe: aufgabenList
             ) {

            AufgabenArangoDAO.createAufgabe(aufgabe);

        }

    return aufgabenList;

    }

    static void loescheTestdaten(){
        ArangoDBSetup arangoDBSetup = new ArangoDBSetup();

        arangoDBSetup.verbinden();


        arangoDBSetup.getDatabaseHandler().collection("Aufgabenstellungen").drop();
        arangoDBSetup.getDatabaseHandler().collection("Parameter").drop();
        arangoDBSetup.getDatabaseHandler().collection("GegebeneParameter").drop();
        arangoDBSetup.getDatabaseHandler().collection("Fragestellungen").drop();
        arangoDBSetup.getDatabaseHandler().collection("GesuchteParameter").drop();
        arangoDBSetup.getDatabaseHandler().collection("HatFragestellung").drop();

        arangoDBSetup.getGraph().drop();

        arangoDBSetup.schließen();
    }

}
