package miage.pds.prospect.controller;

import com.mongodb.MongoClient;
import miage.pds.prospect.dao.ProspectDAO;
import miage.pds.prospect.model.Prospect;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 12/22/2015.
 */
public class ProspectDAOImpl extends BasicDAO<Prospect, ObjectId> implements ProspectDAO{

    private static final String ID      = "id";

    /**
     * Constructor basic
     * @param entityClass
     * @param ds
     */
    public ProspectDAOImpl(Class<Prospect> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    /**
     * Constructor with full parameters
     * @param mongoClient
     * @param morphia
     * @param dbName
     */
    public ProspectDAOImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }


    /**
     *  Method to call the list which contain all prospect in the table client
     * @return list
     */
    @Override
    public List<Prospect> getAllProspect() {
        Query<Prospect> query = createQuery();
        return query.asList();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Prospect getProspectByID(int id) {
        Query<Prospect> query = createQuery().field(ID).equal(id);
        return query.get();
    }

}
