package datenbankArangoDB;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.ArangoGraph;

import com.arangodb.entity.EdgeDefinition;

import java.util.ArrayList;
import java.util.Collection;

public class ArangoDBSetup {

    private final String ARANGO_USER = "root";
    private final String ARANGO_PASSWORD = "arango";
    private final String DB_NAME = "physikaufgaben";
    private final String GRAPH_NAME = "aufgaben";
    public static final String[] KNOTEN_COLLECTIONS = {"Aufgabenstellungen", "Parameter", "Fragestellungen"};
    public static final String[] KANTEN_COLLECTIONS = {"GesuchteParameter", "GegebeneParameter", "HatFragestellung"};

    public static ArangoDB arangoDBInstanz;
    public static ArangoDatabase databaseHandler;
    public static ArangoGraph graphHandler;

    public void verbinden(){
        arangoDBInstanz = new ArangoDB.Builder()
                .user(ARANGO_USER)
                .password(ARANGO_PASSWORD)
                .build();

        databaseHandler = arangoDBInstanz.db(DB_NAME);

        graphHandler = databaseHandler.graph(GRAPH_NAME);
    }

    public void schließen(){
        arangoDBInstanz.shutdown();
    }

    public void setupArangoDBInstance(){
        verbinden();

        setupDatabaseAndCollectionsIfNeeded();

        if(!graphHandler.exists()){
            createGraph();
        }

        schließen();
    }

    private void setupDatabaseAndCollectionsIfNeeded(){
        if (!arangoDBInstanz.db(DB_NAME).exists()){
            arangoDBInstanz.createDatabase(DB_NAME);
        }

        databaseHandler = arangoDBInstanz.db(DB_NAME);

        for (String collectionName: KNOTEN_COLLECTIONS
        ) {
            if (!databaseHandler.collection(collectionName).exists()){
                databaseHandler.createCollection(collectionName);
            }
        }
    }

    private  void createGraph(){
        Collection<EdgeDefinition> kantenDefinition = new ArrayList<>();
        EdgeDefinition kantenGesuchterParameter = new EdgeDefinition().collection(KANTEN_COLLECTIONS[0]).from(KNOTEN_COLLECTIONS[2]).to(KNOTEN_COLLECTIONS[1]);
        EdgeDefinition kantenGegebeneParameter = new EdgeDefinition().collection(KANTEN_COLLECTIONS[1]).from(KNOTEN_COLLECTIONS[0]).to(KNOTEN_COLLECTIONS[1]);
        EdgeDefinition kantenHatFragestellung = new EdgeDefinition().collection(KANTEN_COLLECTIONS[2]).from(KNOTEN_COLLECTIONS[0]).to(KNOTEN_COLLECTIONS[2]);

        kantenDefinition.add(kantenGesuchterParameter);
        kantenDefinition.add(kantenGegebeneParameter);
        kantenDefinition.add(kantenHatFragestellung);

        databaseHandler.createGraph(GRAPH_NAME, kantenDefinition);
    }

    public ArangoDB getArangoDBInstanz() {
        return arangoDBInstanz;
    }

    public ArangoGraph getGraph() {
        return databaseHandler.graph(GRAPH_NAME);
    }

    public ArangoDatabase getDatabaseHandler() {
        return databaseHandler;
    }
}
