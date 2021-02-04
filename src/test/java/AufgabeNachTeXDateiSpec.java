import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import outputLatex.AufgabeNachTeXDatei;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eine TeX Datei ")
public class AufgabeNachTeXDateiSpec {

    @DisplayName("kann erstellt werden und mit einem String befüllt werden.")
    @Test
    void einStringInLatexDateiSchreiben(){
        Boolean fileCreated = AufgabeNachTeXDatei.writeStringToTexFile("\\bf{Hello Tex!}");

        assertThat(fileCreated).isEqualTo(true);
    }

}
