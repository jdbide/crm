package miage.pds.api.ctruong.uc.prospect.suggest.controller;


import com.mongodb.MongoClient;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.ProspectDAO;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * This is a class dao which declare all method communicate with the client collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
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
        Query<Prospect> query = createQuery().order(ID);
        return query.asList();
    }

    /**
     *  Method to call a prospect with it id
     * @param id
     * @return
     */
    @Override
    public Prospect getProspectByID(int id) {
        Query<Prospect> query = createQuery().field(ID).equal(id);
        return query.get();
    }

}