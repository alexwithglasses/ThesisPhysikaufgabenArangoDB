package datenbankArangoDB;


import fachlogikPhysikaufgaben.AufgabenParameter;
import fachlogikPhysikaufgaben.Fragestellung;
import fachlogikPhysikaufgaben.Physikaufgabe;


import java.util.List;

import static datenbankArangoDB.aufgabenArangoUtil.CreateAufgabenArangoUtility.*;
import static datenbankArangoDB.aufgabenArangoUtil.ReadAufgabenArangoUtility.*;


public class AufgabenArangoDAO extends ArangoDBSetup {

    public static String createAufgabe(Physikaufgabe aufgabe){

        ArangoDBSetup arangoDBInstanz = new ArangoDBSetup();

        arangoDBInstanz.verbinden();

        String idAufgabenstellung = erstelleKnotenAufgabenstellung(databaseHandler.collection(KNOTEN_COLLECTIONS[0]), aufgabe.getAufgabenstellung() );

        for (AufgabenParameter parameter: aufgabe.getAufgabenstellung().getGegebeneParameterList()
        ) {
            String idParameter = erstelleKnotenParameter(databaseHandler.collection(KNOTEN_COLLECTIONS[1]), parameter);
            erstelleKanteParameter(graphHandler.edgeCollection(KANTEN_COLLECTIONS[1]), idAufgabenstellung, idParameter, parameter);
        }

        for (Fragestellung frage : aufgabe.getFragestellungList()
        ) {
            String idFragestellung = erstelleKnotenFragestellung(databaseHandler.collection(KNOTEN_COLLECTIONS[2]), frage);
            erstelleKanteFragestellung(graphHandler.edgeCollection(KANTEN_COLLECTIONS[2]),idAufgabenstellung, idFragestellung);

            for (AufgabenParameter parameter: frage.getGesuchteParameterList()
            ) {
                String idParameter = erstelleKnotenParameter(databaseHandler.collection(KNOTEN_COLLECTIONS[1]), parameter);
                erstelleKanteParameter(graphHandler.edgeCollection(KANTEN_COLLECTIONS[0]), idFragestellung, idParameter, parameter);
            }
        }

        arangoDBInstanz.schließen();

        return idAufgabenstellung;
    }


    public static Physikaufgabe readAufgabe(String aufgabenstellungID){

        ArangoDBSetup arangoDBInstanz = new ArangoDBSetup();

        arangoDBInstanz.verbinden();

            List<Fragestellung> fragen = getFragestellungenMitParametern(aufgabenstellungID, arangoDBInstanz);

            Physikaufgabe aufgabe = new Physikaufgabe(
                    getAufgabenstellungMitParametern(aufgabenstellungID,arangoDBInstanz),
                    fragen.toArray(new Fragestellung[fragen.size()])
            );


            arangoDBInstanz.schließen();
            return aufgabe;
        }


}
