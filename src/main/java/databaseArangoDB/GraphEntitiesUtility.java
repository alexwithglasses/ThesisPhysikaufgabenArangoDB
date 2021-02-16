package databaseArangoDB;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoEdgeCollection;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.entity.DocumentCreateEntity;

public class GraphEntitiesUtility {

    public static boolean existiertKnoten(String knotenKey, ArangoCollection knotenCollection){

        String knotenDocID = knotenCollection.getDocument(knotenKey, String.class);

        if(knotenDocID != null){
            return true;
        }else{
            return false;
        }

    }


    public static String erstelleKnotenAufgabenstellung(
            ArangoCollection aufgabenstellung,
            String key,
            String aufgabenstellungText,
            String fachbereich){

        if (!existiertKnoten(key, aufgabenstellung)) {

            BaseDocument aufgabenstellungDocument = new BaseDocument();
            aufgabenstellungDocument.setKey(key);
            aufgabenstellungDocument.addAttribute("text", aufgabenstellungText);
            aufgabenstellungDocument.addAttribute("fachbereich", fachbereich);

            aufgabenstellung.insertDocument(aufgabenstellungDocument);
        }
        return aufgabenstellung.name() + "/" + key;
    }

    public static String erstelleKnotenFragestellung(
            ArangoCollection fragestellung,
            String key,
            String fragestellungText,
            String fachbereich){

        if(!existiertKnoten(key, fragestellung)) {

            BaseDocument aufgabenstellungDocument = new BaseDocument();
            aufgabenstellungDocument.setKey(key);
            aufgabenstellungDocument.addAttribute("text", fragestellungText);
            aufgabenstellungDocument.addAttribute("fachbereich", fachbereich);

            fragestellung.insertDocument(aufgabenstellungDocument);
        }

        return fragestellung.name() + "/" + key;
    }

    public static String erstelleKnotenParameter(
            ArangoCollection parameter,
            String key,
            String bezeichnung
        ){

        if (!existiertKnoten(key, parameter)) {
            BaseDocument parameterDocument = new BaseDocument();
            parameterDocument.setKey(key);
            parameterDocument.addAttribute("bezeichnung", bezeichnung);

            parameter.insertDocument(parameterDocument);
        }
        return parameter.name() + "/" + key;
    }

    public static void erstelleKanteParameter(
            ArangoEdgeCollection parameter,
            String from,
            String to,
            float untereSchrankeWertebereich,
            float obereSchrankeWertebereich,
            String einheit,
            String bezeichnungParameter
    ){
        BaseEdgeDocument doc = new BaseEdgeDocument();

        doc.addAttribute("untereSchrankeWertebereich" , untereSchrankeWertebereich);
        doc.addAttribute("obereSchrankeWertebereich", obereSchrankeWertebereich);
        doc.addAttribute("einheit", einheit);
        doc.addAttribute("bezeichnungParameter", bezeichnungParameter);

        doc.setFrom(from);
        doc.setTo(to);

        parameter.insertEdge(doc);
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




}
