package miage.pds.api.admin.customer.crud.createhc.dao;

import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.api.admin.customer.crud.createhc.entities.Holding;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 * Class used to do operation between mongodb table "holding" and the rest server
 */
public class HoldingDAO extends BasicDAO<Holding,ObjectId> {

    public HoldingDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Return a list of all holding from the db
     * @return List<Holding>
     */
    public List<Holding> findAll() {
        return find().asList();
    }

}
