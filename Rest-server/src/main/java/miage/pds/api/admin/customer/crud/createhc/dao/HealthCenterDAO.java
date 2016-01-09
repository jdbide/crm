package miage.pds.api.admin.customer.crud.createhc.dao;

import com.mongodb.MongoClient;
import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 */
public class HealthCenterDAO extends BasicDAO<HealthCenter,ObjectId> {

    public HealthCenterDAO(Datastore ds) {
        super(ds);
    }


    public List<HealthCenter> findAllWithoutUserId(int id) {
       return getDatastore().createQuery(HealthCenter.class).filter("idUser <>",id).asList();
    }





}
