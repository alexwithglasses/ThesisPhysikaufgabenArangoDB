package fachlogikPhysikaufgabenTest;

import fachlogikPhysikaufgaben.AufgabenParameter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ein gegebener Parameter ")
public class AufgabenParameterSpec {



    @DisplayName("kann mit einem gegebenen Wert erstellt werden.")
    @Test
    void erstellenEinesParametersMitGegebenemWert(){
        AufgabenParameter masse1 = new AufgabenParameter("m1", "Masse", "m" , "g", 100, true);
        assertThat(masse1.toString()).isEqualTo(String.format("%.2f", 100.0) + "g");
    }

    @DisplayName("kann ohne einen gegebenen Wert, aber mit einem Werteintervall erstellt werden.")
    @Test
    void erstellenEinesParametersOhneGegebenemWertMitWerteintervall(){
        AufgabenParameter masse2 = new AufgabenParameter("m2","Masse","m", "g", 50.0F, 150.0F,true);
        assertThat(masse2.getUntereSchrankeZahlenwert()).isEqualTo(new Float(50));
        assertThat(masse2.getObereSchrankeZahlenwert()).isEqualTo(new Float(150));
    }

    @DisplayName("kann ohne einen gegebenen Wert erstellt werden und mit einem zufaellig erstelltem Wert innerhalb des Intervalls bestueckt werden.")
    @Test
    void generierenEinesZahlenwertesInnerhalbDesWerteintervalls(){
        AufgabenParameter masse2 = new AufgabenParameter("m2","Masse", "m", "g", 50.0F, 150.0F,true);
        masse2.generiereZahlenwert();
        assertThat(masse2.getZahlenwert()).isBetween(50.0F, 150.0F);
    }



}
