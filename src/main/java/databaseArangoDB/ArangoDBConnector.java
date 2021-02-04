package databaseArangoDB;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;

public class ArangoDBConnector {

    private final String ARANGO_USER = "root";
    private final String ARANGO_PASSWORD = "arango";
    private final String DB_NAME = "physikaufgaben";

    private ArangoDB arangoDBInstanz;
    private ArangoDatabase databaseHandler;

    public ArangoDBConnector(){

        arangoDBInstanz = new ArangoDB.Builder()
                .user(ARANGO_USER)
                .password(ARANGO_PASSWORD)
                .build();

            databaseHandler = arangoDBInstanz.db(DB_NAME);

    }

    public ArangoDB getArangoDBInstanz() {
        return arangoDBInstanz;
    }

    public ArangoDatabase getDatabaseHandler() {
        return databaseHandler;
    }
}
