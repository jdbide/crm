package miage.pds.api.admin.customer.crud.createindep.dao;

import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.api.admin.customer.crud.createindep.entities.Independant;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 */
public class IndependantDAO extends BasicDAO<Independant,ObjectId> {

    public IndependantDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Return a list of independants from the db without all lines
     * where the idUser is equals to the id pass in parameter
     * @return List<Independant>
     */
    public List<Independant> findAllWithoutUserId(int id) {
        return getDatastore().createQuery(Independant.class).filter("idUser <>",id).asList();
    }
}
