package databaseArangoDB;

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

        if(knotenDocID != null){
            return true;
        }else{
            return false;
        }

    }


    protected static String erstelleKnotenAufgabenstellung(
            ArangoCollection aufgabenstellungCollection,
            Aufgabenstellung aufgabenstellung
    ){

        if (!existiertKnoten(aufgabenstellung.getId(), aufgabenstellungCollection)) {

            BaseDocument aufgabenstellungDocument = new BaseDocument();
            aufgabenstellungDocument.setKey(aufgabenstellung.getId());
            aufgabenstellungDocument.addAttribute("text", aufgabenstellung.getAufgabenstellungText());
            aufgabenstellungDocument.addAttribute("fachbereich", aufgabenstellung.getFachbereich());

            aufgabenstellungCollection.insertDocument(aufgabenstellungDocument);
        }
        return aufgabenstellungCollection.name() + "/" + aufgabenstellung.getId();
    }

    protected static String erstelleKnotenFragestellung(
            ArangoCollection fragestellungCollection,
            Fragestellung frage
    ){

        if(!existiertKnoten(frage.getId(), fragestellungCollection)) {

            BaseDocument aufgabenstellungDocument = new BaseDocument();
            aufgabenstellungDocument.setKey(frage.getId());
            aufgabenstellungDocument.addAttribute("text", frage.getFragestellungText());
            aufgabenstellungDocument.addAttribute("fachbereich", frage.getFachbereich());

            fragestellungCollection.insertDocument(aufgabenstellungDocument);
        }

        return fragestellungCollection.name() + "/" + frage.getId();
    }

    protected static String erstelleKnotenParameter(
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

    protected static void erstelleKanteParameter(
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

    protected static void erstelleKanteFragestellung(
            ArangoEdgeCollection hatFrage,
            String from,
            String to
    ){
        BaseEdgeDocument doc = new BaseEdgeDocument();
        doc.setFrom(from);
        doc.setTo(to);
        hatFrage.insertEdge(doc);
    }

}
