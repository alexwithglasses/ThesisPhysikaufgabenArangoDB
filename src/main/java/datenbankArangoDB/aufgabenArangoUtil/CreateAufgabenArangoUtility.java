package datenbankArangoDB.aufgabenArangoUtil;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoEdgeCollection;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Aufgabenstellung;
import fachlogikPhysikaufgaben.Fragestellung;

public class CreateAufgabenArangoUtility {

    protected static boolean existiertKnoten(String knotenKey, ArangoCollection knotenCollection){

        String knotenDocID = knotenCollection.getDocument(knotenKey, String.class);

        //returns true if not null, false otherwise
        return knotenDocID != null;

    }


    public static String erstelleKnotenAufgabenstellung(
            ArangoCollection aufgabenstellungCollection,
            Aufgabenstellung aufgabenstellung
    ){

        if (!existiertKnoten(aufgabenstellung.getId(), aufgabenstellungCollection)) {

            String aufgabenstellungUTF8 = ersetzeUmlaute(aufgabenstellung.getAufgabenstellungText());

            BaseDocument aufgabenstellungDocument = new BaseDocument();
            aufgabenstellungDocument.setKey(aufgabenstellung.getId());
            aufgabenstellungDocument.addAttribute("text", aufgabenstellungUTF8);
            aufgabenstellungDocument.addAttribute("fachbereich", aufgabenstellung.getFachbereich());

            aufgabenstellungCollection.insertDocument(aufgabenstellungDocument);
        }
        return aufgabenstellungCollection.name() + "/" + aufgabenstellung.getId();
    }

    public static String erstelleKnotenFragestellung(
            ArangoCollection fragestellungCollection,
            Fragestellung frage
    ){

        if(!existiertKnoten(frage.getId(), fragestellungCollection)) {

            String fragestellungUTF8 = ersetzeUmlaute(frage.getFragestellungText());

            BaseDocument aufgabenstellungDocument = new BaseDocument();
            aufgabenstellungDocument.setKey(frage.getId());
            aufgabenstellungDocument.addAttribute("text", fragestellungUTF8);
            aufgabenstellungDocument.addAttribute("fachbereich", frage.getFachbereich());

            fragestellungCollection.insertDocument(aufgabenstellungDocument);
        }

        return fragestellungCollection.name() + "/" + frage.getId();
    }

    public static String erstelleKnotenParameter(
            ArangoCollection parameterCollection,
            AufgabenParameter parameter
    ){

        if (!existiertKnoten(parameter.getBezeichner(), parameterCollection)) {
            BaseDocument parameterDocument = new BaseDocument();
            parameterDocument.setKey(parameter.getBezeichner());
            parameterDocument.addAttribute("bezeichnung", parameter.getBezeichnung());

            parameterCollection.insertDocument(parameterDocument);
        }
        return parameterCollection.name() + "/" + parameter.getBezeichner();
    }

    public static void erstelleKanteParameter(
            ArangoEdgeCollection parameterCollection,
            String from,
            String to,
            AufgabenParameter parameter
    ){

        BaseEdgeDocument doc = new BaseEdgeDocument();

        doc.addAttribute("untereSchrankeWertebereich" , parameter.getUntereSchrankeZahlenwert());
        doc.addAttribute("obereSchrankeWertebereich", parameter.getObereSchrankeZahlenwert());
        doc.addAttribute("einheit", parameter.getEinheit());
        doc.addAttribute("formelsymbol", parameter.getFormelsymbol());
        doc.addAttribute("ganzeZahl", parameter.isGanzeZahl());

        doc.setFrom(from);
        doc.setTo(to);

        parameterCollection.insertEdge(doc);
    }

    public static void erstelleKanteFragestellung(
            ArangoEdgeCollection hatFrage,
            String from,
            String to
    ){
        BaseEdgeDocument doc = new BaseEdgeDocument();
        doc.setFrom(from);
        doc.setTo(to);
        hatFrage.insertEdge(doc);
    }


    public static String ersetzeUmlaute(String orig) {
        String[][] UMLAUT_REPLACEMENTS = { { new String("Ä"), "\u00c4" }, { new String("Ü"), "\u00dc" }, { new String("Ö"), "\u00d6" }, { new String("ä"), "\u00e4" }, { new String("ü"), "\u00fc" }, { new String("ö"), "\u00f6" }, { new String("ß"), "\u00df" } };

        String result = orig;

        for (int i = 0; i < UMLAUT_REPLACEMENTS.length; i++) {
            result = result.replace(UMLAUT_REPLACEMENTS[i][0], UMLAUT_REPLACEMENTS[i][1]);
        }

        return result;
    }

}
