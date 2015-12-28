package miage.pds.prospect.service;

import com.mongodb.MongoClient;
import miage.pds.api.Config;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

/**
 * Created by Truong on 12/22/2015.
 */
public class MongoService {

    private Morphia morphia;
    private Datastore datastore;
    private static Logger logger = LoggerFactory.getLogger(MongoService.class);
    private static final String DATABASE_NAME = "crm";

    public MongoService() {
        try {
            MongoClient mongoClient = new MongoClient(Config.URL, Config.PORT);
            logger.info("The new instance of mongo service running with Morphia");
            // Create new instance
            this.morphia    =   new Morphia();
            this.datastore  =   morphia.createDatastore(mongoClient, DATABASE_NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
}