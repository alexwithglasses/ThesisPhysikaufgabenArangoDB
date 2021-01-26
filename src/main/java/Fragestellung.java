import java.util.List;

public class Fragestellung {

    private String fragestellungTest;
    private List<GegebenerParameter> gegebeneParamteterList;

    public String getFragestellungTest() {
        return fragestellungTest;
    }

    public void setFragestellungTest(String fragestellungTest) {
        this.fragestellungTest = fragestellungTest;
    }

    public List<GegebenerParameter> getGegebeneParamteterList() {
        return gegebeneParamteterList;
    }

    public void setGegebeneParamteterList(List<GegebenerParameter> gegebeneParamteterList) {
        this.gegebeneParamteterList = gegebeneParamteterList;
    }

}
