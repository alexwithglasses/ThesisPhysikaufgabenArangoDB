package fachlogikPhysikaufgabenTest;

import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.Physikaufgabe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eine Physikaufgabe ")
public class PhysikaufgabeSpec {

    private ArrayList<AufgabenParameter> parameterAufgabe1;
    private Aufgabenstellung aufgabenstellungAufgabe1;
    private Fragestellung frageA;
    private Fragestellung frageB;
    private Physikaufgabe aufgabe1;

    @BeforeEach
    void init(){
        parameterAufgabe1 = new ArrayList<>();
        parameterAufgabe1.add(new AufgabenParameter("m1","Masse", "m", "g", 50.0F, 150.0F,true));
        parameterAufgabe1.add(new AufgabenParameter("t1", "Zeit", "t", "min", 1F, 5F, false));
        parameterAufgabe1.add(new AufgabenParameter("S1", "Schwingungen", "Schwingungen", "", 60.0F, 120.0F,true));

        aufgabenstellungAufgabe1 = new Aufgabenstellung("A1", "Ein Federpendel mit der Masse {0} führt in {1} {2} aus.", parameterAufgabe1, "Mechanik");

        frageA = new Fragestellung("a) Bestimmen Sie die Frequenz {0} der Schwingung.", new AufgabenParameter("f1", "Hz" ));
        frageB = new Fragestellung("b) Bestimmen Sie die Federkonstane {0} der Schwingung.", new AufgabenParameter("D1", "N/m"));


        aufgabe1 = new Physikaufgabe(aufgabenstellungAufgabe1, frageA, frageB);

        System.out.println(aufgabe1.toString());
    }


    @DisplayName("kann beim Erstellen eine Aufgabenstellung mit veränderlichen Parametern enthalten.")
    @Test
    void aufgabeKannMitParametrisierterAufgabenstellungErstelltWerden(){

        assertThat(aufgabe1.getAufgabenstellung().toString()!=null).isTrue();
        assertThat(parameterAufgabe1.get(0).getZahlenwert()).isBetween(50.0F, 150.0F);
        assertThat(parameterAufgabe1.get(1).getZahlenwert()).isBetween(1F, 5F);
        assertThat(parameterAufgabe1.get(2).getZahlenwert()).isBetween(60.0F, 120.0F);
    }

    @DisplayName("kann beim Erstellen eine Aufgabenstellung mit veränderlichen Parametern und mehrere Fragestellungen mit Parametern enthalten.")
    @Test
    void aufgabeKannMitParametrisierterAufgabenstellungUndFragestellungErstelltWerden(){

        assertThat(aufgabe1.getAufgabenstellung().toString()!=null).isTrue();

        assertThat(aufgabe1.getFragestellungList().get(0).toString()).isEqualTo("a) Bestimmen Sie die Frequenz f1 der Schwingung.");
        assertThat(aufgabe1.getFragestellungList().get(1).toString()).isEqualTo("b) Bestimmen Sie die Federkonstane D1 der Schwingung.");
    }


    }

