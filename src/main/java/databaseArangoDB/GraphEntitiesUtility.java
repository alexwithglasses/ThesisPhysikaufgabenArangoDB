package databaseArangoDB;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoEdgeCollection;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.entity.DocumentCreateEntity;

public class GraphEntitiesUtility {

    public static DocumentCreateEntity<BaseDocument> erstelleKnotenAufgabenstellung(
            ArangoCollection aufgabenstellung,
            String key,
            String aufgabenstellungText,
            String fachbereich){

        BaseDocument aufgabenstellungDocument = new BaseDocument();
        aufgabenstellungDocument.setKey(key);
        aufgabenstellungDocument.addAttribute("text", aufgabenstellungText);
        aufgabenstellungDocument.addAttribute("fachbereich", fachbereich);

        return aufgabenstellung.insertDocument(aufgabenstellungDocument);
    }

    public static DocumentCreateEntity<BaseDocument> erstelleKnotenFragestellung(
            ArangoCollection fragestellung,
            String key,
            String fragestellungText,
            String fachbereich){

        BaseDocument aufgabenstellungDocument = new BaseDocument();
        aufgabenstellungDocument.setKey(key);
        aufgabenstellungDocument.addAttribute("text", fragestellungText);
        aufgabenstellungDocument.addAttribute("fachbereich", fachbereich);

        return fragestellung.insertDocument(aufgabenstellungDocument);
    }

    public static DocumentCreateEntity<BaseDocument> erstelleKnotenParameter(
            ArangoCollection parameter,
            String key,
            String bezeichnung
        ){

        BaseDocument parameterDocument = new BaseDocument();
        parameterDocument.setKey(key);
        parameterDocument.addAttribute("bezeichnung", bezeichnung);

        return parameter.insertDocument(parameterDocument);
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
