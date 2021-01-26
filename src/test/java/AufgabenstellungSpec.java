import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

@DisplayName("Eine Aufgabenstellung ")
public class AufgabenstellungSpec {

    @DisplayName("kann mit einer variabl langen Liste an gegebenen Parametern erstellt werden.")
    @Test
    void aufgabenstellungMitVariabelLangerParameterlisteErstellen(){
        ArrayList<GegebenerParameter> parameterAufgabe1;
        parameterAufgabe1 = new ArrayList<>();
        parameterAufgabe1.add(new GegebenerParameter("m1", "g", 50.0F, 150.0F));
        parameterAufgabe1.add(new GegebenerParameter("t1", "min", 0F, 5F));
        parameterAufgabe1.add(new GegebenerParameter("S1", "Schwingungen", 60.0F, 120.0F));

        Aufgabenstellung aufgabe1 = new Aufgabenstellung("Ein Federpendel mit der Masse {0} führt in {1} {2} aus.", parameterAufgabe1);

        System.out.println(aufgabe1.toString());
        assertThat(aufgabe1.toString()!= null).isTrue();
    }

}
