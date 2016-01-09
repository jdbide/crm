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
 */
public class HoldingDAO extends BasicDAO<Holding,ObjectId> {

    public HoldingDAO(Datastore ds) {
        super(ds);
    }

    public List<Holding> findAll() {
        return find().asList();
    }

}
