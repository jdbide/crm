package miage.pds.prospect.test.data;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import miage.pds.api.Config;

import java.net.UnknownHostException;

/**
 * Created by Truong on 12/20/2015.
 */
public class TestData {


    private static MongoClient mongoClient() {
        try {
            return new MongoClient(Config.URL, Config.PORT );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void prospect() {

        MongoClient client = mongoClient();
        DB db = client.getDB( "crm");

        DBCollection prospect = db.getCollection("client");
        prospect.drop();

        prospect.insert(dbObjectFromJson("{_id : 3, name : 'ES3'}"));
        prospect.insert(dbObjectFromJson("{_id : 4, name : 'ES4'}"));
        prospect.insert(dbObjectFromJson("{_id : 5, name : 'ES5'}"));
        prospect.insert(dbObjectFromJson("{_id : 6, name : 'ES6'}"));
        prospect.insert(dbObjectFromJson("{_id : 7, name : 'ES7'}"));
        prospect.insert(dbObjectFromJson("{_id : 8, name : 'ES8'}"));
        prospect.insert(dbObjectFromJson("{_id : 9, name : 'ES9'}"));
    }

    private static DBObject dbObjectFromJson(String json) {
        return (DBObject) JSON.parse(json);

    }

}
