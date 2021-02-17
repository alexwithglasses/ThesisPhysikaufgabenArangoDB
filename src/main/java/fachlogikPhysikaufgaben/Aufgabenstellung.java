package fachlogikPhysikaufgaben;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Aufgabenstellung {

    private String aufgabenstellungText;
    private String id;
    private List<AufgabenParameter> gegebeneParameterList;
    private String fachbereich;

    public Aufgabenstellung(String id,String aufgabenstellungText, List<AufgabenParameter> gegebeneParameterList, String fachbereich){

        this.id = id;

        //byte[] byteString = aufgabenstellungText.getBytes(StandardCharsets.ISO_8859_1);
        //this.aufgabenstellungText = new String(byteString, StandardCharsets.UTF_8);
        this.aufgabenstellungText = aufgabenstellungText;

        this.gegebeneParameterList = gegebeneParameterList;
        this.fachbereich = fachbereich;
    }

    @Override
    public String toString() {
        ArrayList<String> parameterAlsStrings = new ArrayList<>();
        for(AufgabenParameter parameter : gegebeneParameterList){
            parameterAlsStrings.add(parameter.toString());
        }

        return MessageFormat.format(aufgabenstellungText, parameterAlsStrings.toArray());
    }

    public String getAufgabenstellungText() {
        return aufgabenstellungText;
    }

    public void setAufgabenstellungText(String aufgabenstellungText) {
        this.aufgabenstellungText = aufgabenstellungText;
    }

    public List<AufgabenParameter> getGegebeneParameterList() {
        return gegebeneParameterList;
    }

    public void setGegebeneParameterList(ArrayList<AufgabenParameter> gegebeneParameterList) {
        this.gegebeneParameterList = gegebeneParameterList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFachbereich() {
        return fachbereich;
    }

    public void setFachbereich(String fachbereich) {
        this.fachbereich = fachbereich;
    }
}
