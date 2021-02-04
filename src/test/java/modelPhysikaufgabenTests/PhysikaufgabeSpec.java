package modelPhysikaufgabenTests;

import modelPhysikaufgabe.Aufgabenstellung;
import modelPhysikaufgabe.AufgabenParameter;
import modelPhysikaufgabe.PhysikAufgabe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eine Physikaufgabe ")
public class PhysikaufgabeSpec {

    private ArrayList<AufgabenParameter> parameterAufgabe1;
    private Aufgabenstellung aufgabenstellungAufgabe1;
    private PhysikAufgabe aufgabe1;

    @BeforeEach
    void init(){
        parameterAufgabe1 = new ArrayList<>();
        parameterAufgabe1.add(new AufgabenParameter("m1", "g", 50.0F, 150.0F));
        parameterAufgabe1.add(new AufgabenParameter("t1", "min", 1F, 5F));
        parameterAufgabe1.add(new AufgabenParameter("S1", "Schwingungen", 60.0F, 120.0F));

        aufgabenstellungAufgabe1 = new Aufgabenstellung("Ein Federpendel mit der Masse {0} führt in {1} {2} aus.", parameterAufgabe1);

        aufgabe1 = new PhysikAufgabe(aufgabenstellungAufgabe1);
    }


    @DisplayName("kann beim Erstellen eine Aufgabenstellung mit veränderlichen Parametern enthalten.")
    @Test
    void aufgabeKannMitParametrisierterAufgabenstellungErstelltWerden(){

        System.out.println("Test modelPhysikaufgabenTests.PhysikaufgabeSpec, Aufgabenstellung: ");
        System.out.println(aufgabe1.getAufgabenstellung().toString());

        assertThat(aufgabe1.getAufgabenstellung().toString()!=null).isTrue();
        assertThat(parameterAufgabe1.get(0).getZahlenwert()).isBetween(50.0F, 150.0F);
        assertThat(parameterAufgabe1.get(1).getZahlenwert()).isBetween(1F, 5F);
        assertThat(parameterAufgabe1.get(2).getZahlenwert()).isBetween(60.0F, 120.0F);
    }

    @DisplayName("kann beim Erstellen eine Aufgabenstellung mit veränderlichen Parametern und eine Fragestellung enthalten.")
    @Test
    void aufgabeKannMitParametrisierterAufgabenstellungUndFragestellungErstelltWerden(){



    }
}
