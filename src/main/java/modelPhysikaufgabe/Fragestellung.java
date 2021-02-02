package modelPhysikaufgabe;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Fragestellung {

    private String fragestellungText;
    private List<AufgabenParameter> gesuchteParameterList;

    public Fragestellung(String fragestellungText, AufgabenParameter gesuchterParameter){
        this.fragestellungText = fragestellungText;
        gesuchteParameterList = new ArrayList<>();
        gesuchteParameterList.add(gesuchterParameter);
    }

    @Override
    public String toString() {
        ArrayList<String> parameterAlsStrings = new ArrayList<>();
        for(AufgabenParameter parameter : gesuchteParameterList){
            parameterAlsStrings.add(parameter.getBezeichnungInFließtext());
        }

        return MessageFormat.format(fragestellungText, parameterAlsStrings.toArray());
    }

    public String getFragestellungTest() {
        return fragestellungText;
    }

    public void setFragestellungTest(String fragestellungTest) {
        this.fragestellungText = fragestellungTest;
    }

    public List<AufgabenParameter> getparamteterList() {
        return gesuchteParameterList;
    }

    public void setparamteterList(List<AufgabenParameter> gesuchteParamteterList) {
        this.gesuchteParameterList = gesuchteParamteterList;
    }

}
