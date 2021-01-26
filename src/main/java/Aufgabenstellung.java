import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Aufgabenstellung {

    private String aufgabenstellungText;
    private ArrayList<GegebenerParameter> gegebeneParameterList;

    public Aufgabenstellung(String aufgabenstellungText, ArrayList<GegebenerParameter> gegebeneParameterList){
        this.aufgabenstellungText = aufgabenstellungText;
        this.gegebeneParameterList = gegebeneParameterList;
    }

    @Override
    public String toString() {
        ArrayList<String> parameterAlsStrings = new ArrayList<>();
        for(GegebenerParameter parameter : gegebeneParameterList){
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

    public List<GegebenerParameter> getGegebeneParameterList() {
        return gegebeneParameterList;
    }

    public void setGegebeneParameterList(ArrayList<GegebenerParameter> gegebeneParameterList) {
        this.gegebeneParameterList = gegebeneParameterList;
    }

}
