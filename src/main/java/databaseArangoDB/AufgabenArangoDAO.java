package databaseArangoDB;


import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.PhysikAufgabe;


import java.util.List;

import static databaseArangoDB.aufgabenArangoUtil.CreateAufgabenArangoUtility.*;
import static databaseArangoDB.aufgabenArangoUtil.ReadAufgabenArangoUtility.*;


public class AufgabenArangoDAO extends ArangoDBSetup {

    public static String createAufgabe(PhysikAufgabe aufgabe){

        ArangoDBSetup arangoDBInstanz = new ArangoDBSetup();

        arangoDBInstanz.verbinden();

        String idAufgabenstellung = erstelleKnotenAufgabenstellung(arangoDBInstanz.getDatabaseHandler().collection(KNOTEN_COLLECTIONS[0]), aufgabe.getAufgabenstellung() );

        for (AufgabenParameter parameter: aufgabe.getAufgabenstellung().getGegebeneParameterList()
        ) {
            String idParameter = erstelleKnotenParameter(arangoDBInstanz.getDatabaseHandler().collection(KNOTEN_COLLECTIONS[1]), parameter);
            erstelleKanteParameter(arangoDBInstanz.getGraph().edgeCollection(KANTEN_COLLECTIONS[1]), idAufgabenstellung, idParameter, parameter);
        }

        for (Fragestellung frage : aufgabe.getFragestellungList()
        ) {
            String idFragestellung = erstelleKnotenFragestellung(arangoDBInstanz.getDatabaseHandler().collection(KNOTEN_COLLECTIONS[2]), frage);
            erstelleKanteFragestellung(arangoDBInstanz.getGraph().edgeCollection(KANTEN_COLLECTIONS[2]),idAufgabenstellung, idFragestellung);

            for (AufgabenParameter parameter: frage.getGesuchteParameterList()
            ) {
                String idParameter = erstelleKnotenParameter(arangoDBInstanz.getDatabaseHandler().collection(KNOTEN_COLLECTIONS[1]), parameter);
                erstelleKanteParameter(arangoDBInstanz.getGraph().edgeCollection(KANTEN_COLLECTIONS[0]), idFragestellung, idParameter, parameter);
            }
        }

        arangoDBInstanz.schließen();

        return idAufgabenstellung;
    }


    public static PhysikAufgabe readAufgabe(String aufgabenstellungID){

        ArangoDBSetup arangoDBInstanz = new ArangoDBSetup();

        arangoDBInstanz.verbinden();

            List<Fragestellung> fragen = getFragestellungenMitParametern(aufgabenstellungID, arangoDBInstanz);

            PhysikAufgabe aufgabe = new PhysikAufgabe(
                    getAufgabenstellungMitParametern(aufgabenstellungID,arangoDBInstanz),
                    fragen.toArray(new Fragestellung[fragen.size()])
            );


            arangoDBInstanz.schließen();
            return aufgabe;
        }


}
