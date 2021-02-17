package databaseArangoDB;

import com.arangodb.ArangoCursor;
import com.arangodb.entity.BaseDocument;
import com.arangodb.util.MapBuilder;
import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.PhysikAufgabe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadAufgabenArangoUtility {


    public static String getAufgabeAlsString(String handleAufgabenstellung, ArangoDBSetup physikaufgabenSetup) {

        List<Fragestellung> fragen = getFragestellungenMitParametern(handleAufgabenstellung, physikaufgabenSetup);

        PhysikAufgabe aufgabe = new PhysikAufgabe(
                getAufgabenstellungMitParametern(handleAufgabenstellung,physikaufgabenSetup),
                fragen.toArray(new Fragestellung[fragen.size()])
                );

        return aufgabe.toString();
    }


    public static ArrayList<String> getFragestellungenAlsString(String handleAufgabe, ArangoDBSetup physikaufgabenSetup){

        ArrayList<String> returnFragestellungen = new ArrayList<>();

        for (Fragestellung frage: getFragestellungenMitParametern(handleAufgabe, physikaufgabenSetup)
             ) {
            returnFragestellungen.add(frage.toString());
        }

        return returnFragestellungen;

    }


    public static String getAufgabenstellungAlsString(String handleAufgabe, ArangoDBSetup physikaufgabenSetup){

         Aufgabenstellung aufgabenstellung = getAufgabenstellungMitParametern(handleAufgabe, physikaufgabenSetup);
        return aufgabenstellung.toString();
    }



    private static Aufgabenstellung getAufgabenstellungMitParametern(String handleAufgabe, ArangoDBSetup physikaufgabenSetup) {

        BaseDocument aufgabenstellungDoc = physikaufgabenSetup.getDatabaseHandler().getDocument(handleAufgabe, BaseDocument.class);

        Map<String, Object> queryGegebeneParameterBindVars = new MapBuilder().put("aufgabe", handleAufgabe).get();

        ArangoCursor<BaseDocument> queryGegebeneParameter = physikaufgabenSetup.getDatabaseHandler().query(
                "FOR v,e IN 1..1 OUTBOUND @aufgabe GegebeneParameter RETURN e",
                queryGegebeneParameterBindVars, null, BaseDocument.class
        );

        ArrayList<AufgabenParameter> gegebeneParameterList = new ArrayList<>();

        for (BaseDocument gegebenerParameter: queryGegebeneParameter.asListRemaining()
        ) {

            AufgabenParameter parameter = new AufgabenParameter(
                    (String) gegebenerParameter.getAttribute("formelsymbol"),
                    (String) gegebenerParameter.getAttribute("bezeichnung"),
                    (String) gegebenerParameter.getAttribute("bezeichnungParameter"),
                    (String) gegebenerParameter.getAttribute("einheit"),
                    new Float((Double) gegebenerParameter.getAttribute("untereSchrankeWertebereich")),
                    new Float((Double) gegebenerParameter.getAttribute("obereSchrankeWertebereich")),
                    (boolean) gegebenerParameter.getAttribute("ganzeZahl")
            );

            gegebeneParameterList.add(parameter);

        }

        Aufgabenstellung aufgabenstellung = new Aufgabenstellung(
                aufgabenstellungDoc.getKey(),
                (String) aufgabenstellungDoc.getAttribute("text"),
                gegebeneParameterList,
                (String) aufgabenstellungDoc.getAttribute("fachbereich")
        );

        return aufgabenstellung;
    }

    private static List<Fragestellung> getFragestellungenMitParametern(String handleAufgabenstellung, ArangoDBSetup physikaufgabenSetup){

        List<Fragestellung> returnFragestellungen = new ArrayList<>();

        Map<String, Object> queryFragestellungBindVars = new MapBuilder().put("aufgabe", handleAufgabenstellung).get();

        ArangoCursor<BaseDocument> queryFragestellung = physikaufgabenSetup.getDatabaseHandler().query(
                "FOR v IN 1..1 OUTBOUND @aufgabe HatFragestellung RETURN v",
                queryFragestellungBindVars, null, BaseDocument.class
        );

        List<BaseDocument> fragestellungen = queryFragestellung.asListRemaining();

        for (BaseDocument frageDoc: fragestellungen
        ) {
            Map<String, Object> queryParameterFrageCollection = new MapBuilder().put("frage", frageDoc.getId() ).get();

            ArangoCursor<BaseDocument> queryParameter = physikaufgabenSetup.getDatabaseHandler().query(
                    "FOR v,e IN 1..2 OUTBOUND @frage GesuchteParameter RETURN e",
                    queryParameterFrageCollection, null, BaseDocument.class
            );

            List<BaseDocument> parameter = queryParameter.asListRemaining();
            List<AufgabenParameter> gesuchteParameter = new ArrayList<>();

            for (BaseDocument parameterDoc: parameter
            ) {
                gesuchteParameter.add(new AufgabenParameter(
                        (String) parameterDoc.getAttribute("formelsymbol"),
                        (String) parameterDoc.getAttribute("einheit")
                ));
            }

            returnFragestellungen.add(new Fragestellung(
                    (String) frageDoc.getKey(),
                    (String) frageDoc.getAttribute("text"),
                    gesuchteParameter,
                    (String) frageDoc.getAttribute("fachbereich")
                    )
            );



        }

        return returnFragestellungen;
    }
}
