package miage.pds.api.admin.customer.crud.createindep.dao;

import miage.pds.api.admin.customer.crud.createindep.entities.Specialty;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 */
public class SpecialtyDAO extends BasicDAO<Specialty,ObjectId> {
    public SpecialtyDAO(Datastore ds) {
        super(ds);
    }

    public List<Specialty> findAll() {
        return find().asList();
    }

}
