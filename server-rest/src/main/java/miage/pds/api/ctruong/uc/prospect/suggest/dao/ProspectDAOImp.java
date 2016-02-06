package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by Truong on 2/6/2016.
 */
public class ProspectDAOImp extends BasicDAO<Prospect, ObjectId>{

    private Datastore datastore;

    public ProspectDAOImp(Class<Prospect> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
}
