package modelPhysikaufgabenTests;

import modelPhysikaufgabe.Aufgabenstellung;
import modelPhysikaufgabe.AufgabenParameter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

@DisplayName("Eine Aufgabenstellung ")
public class AufgabenstellungSpec {

    @DisplayName("kann mit einer variabl langen Liste an gegebenen Parametern erstellt werden.")
    @Test
    void aufgabenstellungMitVariabelLangerParameterlisteErstellen(){
        ArrayList<AufgabenParameter> parameterAufgabe1;
        parameterAufgabe1 = new ArrayList<>();
        parameterAufgabe1.add(new AufgabenParameter("m1","Masse", "m", "g", 50.0F, 150.0F));
        parameterAufgabe1.add(new AufgabenParameter("t1", "Zeit", "t", "min", 1F, 5F));
        parameterAufgabe1.add(new AufgabenParameter("S1", "Schwingungen", "Schwingungen", "", 60.0F, 120.0F));

        Aufgabenstellung aufgabe1 = new Aufgabenstellung("A1","Ein Federpendel mit der Masse {0} führt in {1} {2} aus.", parameterAufgabe1, "Mechanik");

        System.out.println(aufgabe1.toString());
        assertThat(aufgabe1.toString()!= null).isTrue();
    }

}
