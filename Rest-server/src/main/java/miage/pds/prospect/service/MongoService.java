package miage.pds.prospect.service;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

/**
 * The class to create the service to communicate with mongodb
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class MongoService {

    private Morphia morphia;
    private Datastore datastore;
    private static Logger logger = LoggerFactory.getLogger(MongoService.class);
    private static final String DATABASE_NAME = "crm";

    public MongoService() {
        try {
            MongoClient mongoClient = new MongoClient(MorphiaConfig.URL, MorphiaConfig.PORT);
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
