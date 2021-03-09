import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.Physikaufgabe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import outputLatex.AufgabeNachTeXDatei;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

@DisplayName("Eine Aufgabe ")
public class LatexOutTest {

    private Physikaufgabe aufgabe2;

    @BeforeEach
    void init() {
        List<AufgabenParameter> parameterAufgabe2List = Arrays.asList(
                new AufgabenParameter("m_1","Masse","m", "kg", 1, 10, true),
                new AufgabenParameter("D_1", "Federkonsante","D", "$\\frac{N}{m}$", 20, 60, true)
        );

        aufgabe2 = new Physikaufgabe(
                new Aufgabenstellung(
                        "A2",
                        "Ein Körper der Masse {0} hängt an einer Feder mit der Federkonstante {1} und schwingt.",
                        parameterAufgabe2List,
                        "Mechanik"
                ),
                new Fragestellung(
                        "A2a",
                        "a) Bestimmen Sie die Winkelgeschwindigkeit {0} des Körpers.",
                        Arrays.asList(new AufgabenParameter("$\\omega _1$", "Winkelgeschwindigkeit", "omega", "$\\frac{\\text{rad}}{s}$", 0, 0, true)),
                        "Mechanik"
                ),

                new Fragestellung(
                        "A2b",
                        "b) Bestimmen Sie seine Schwingfrequenz {0}",
                        Collections.singletonList(new AufgabenParameter("f1", "Frequenz", "f", "Hz", 0, 0, true)),
                        "Mechanik"
                ),

                new Fragestellung(
                        "A2c",
                        "c) Bestimmen Sie die dazugehörige Schwingdauer {0}",
                        Collections.singletonList(new AufgabenParameter("T1", "Periodendauer", "T", "s", 0, 0, true)),
                        "Mechanik"
                )

        );
    }

    @DisplayName("wird in einer neu erstellten TeX Datei im Ordner latexoutput gespeichert.")
    @Test
    void latexFileWirdErstellt(){

        LinkedList<String> aufgaben = new LinkedList<>();
        aufgaben.add(aufgabe2.toString());

       String filePath = AufgabeNachTeXDatei.writeStringToTexFile(aufgaben);

        assertThat(filePath, containsString(".\\latexoutput\\Aufgabe"));

    }
}
