package modelPhysikaufgabe;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Aufgabenstellung {

    private String aufgabenstellungText;
    private ArrayList<AufgabenParameter> gegebeneParameterList;

    public Aufgabenstellung(String aufgabenstellungText, ArrayList<AufgabenParameter> gegebeneParameterList){
        this.aufgabenstellungText = aufgabenstellungText;
        this.gegebeneParameterList = gegebeneParameterList;
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

}
