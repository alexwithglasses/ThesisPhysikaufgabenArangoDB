package fachlogikPhysikaufgaben;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Fragestellung {

    private String id;
    private String fragestellungText;
    private List<AufgabenParameter> gesuchteParameterList;
    private String fachbereich;

    public Fragestellung(String id, String fragestellungText, List<AufgabenParameter> gesuchterParameter, String fachbereich) {
        this.id = id;
        this.fragestellungText = fragestellungText;
        this.gesuchteParameterList = gesuchterParameter;
        this.fachbereich = fachbereich;
    }

   /*
    public Fragestellung(String fragestellungText, AufgabenParameter gesuchterParameter) {

        this.fragestellungText = fragestellungText;
        gesuchteParameterList = new ArrayList<>();
        gesuchteParameterList.add(gesuchterParameter);
    }

    */

    @Override
    public String toString() {
        ArrayList<String> parameterAlsStrings = new ArrayList<>();
        for (AufgabenParameter parameter : gesuchteParameterList) {
            parameterAlsStrings.add(parameter.getBezeichner());
        }

        return MessageFormat.format(fragestellungText, parameterAlsStrings.toArray());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFragestellungText() {
        return fragestellungText;
    }

    public void setFragestellungText(String fragestellungText) {
        this.fragestellungText = fragestellungText;
    }

    public List<AufgabenParameter> getGesuchteParameterList() {
        return gesuchteParameterList;
    }

    public void setGesuchteParameterList(List<AufgabenParameter> gesuchteParameterList) {
        this.gesuchteParameterList = gesuchteParameterList;
    }

    public String getFachbereich() {
        return fachbereich;
    }

    public void setFachbereich(String fachbereich) {
        this.fachbereich = fachbereich;
    }

    public List<AufgabenParameter> getparamteterList() {
        return gesuchteParameterList;
    }

    public void setparamteterList(List<AufgabenParameter> gesuchteParamteterList) {
        this.gesuchteParameterList = gesuchteParamteterList;
    }

}
