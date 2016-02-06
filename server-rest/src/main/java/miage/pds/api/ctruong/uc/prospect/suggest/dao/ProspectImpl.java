package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by Truong on 2/6/2016.
 */
public class ProspectImpl extends BasicDAO<Prospect, ObjectId> implements ProspectDAO{

    public ProspectImpl(Class<Prospect> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
}
