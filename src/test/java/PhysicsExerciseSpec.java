import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eine physikalische Uebungsaufgabe ")
public class PhysicsExerciseSpec {

    private Aufgabenstellung beispielAufgabe1;

    @BeforeEach
    void init(){
        beispielAufgabe1 = new Aufgabenstellung("Ein Federpendel mit der Masse %v führt in %v2 Minuten %v3 Schwingungen aus.", null);
    }

    @Ignore
    @DisplayName("enthaelt eine Aufgabenstellung wenn eine neue Aufgabe mit Aufgabenstellung angelegt wird.")
    @Test
    void aufgabeEnthaeltEineAufgabenstellungWennAufgabeMitAufgabenstellungAngelegtWird(){
        PhysikAufgabe physikAufgabe = new PhysikAufgabe(beispielAufgabe1);
        assertThat(physikAufgabe.getAufgabenstellung()).isEqualTo("Ein Federpendel mit der Masse 100g führt in 2 Minuten 90 Schwingungen aus.");
    }

    @Ignore
    @DisplayName("enthält eine Aufgabenstellung mit Parametern.")
    @Test
    void aufgabeHatAufgabenstellungMitParametern(){
        PhysikAufgabe physikAufgabe = new PhysikAufgabe(beispielAufgabe1);
    }


}
