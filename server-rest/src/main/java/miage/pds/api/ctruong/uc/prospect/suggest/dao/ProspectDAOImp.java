package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public class ProspectDAOImp extends BasicDAO<Prospect, ObjectId> implements ProspectDAO{

    private Datastore datastore;

    public ProspectDAOImp(Class<Prospect> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    @Override
    public Prospect getProspectById(long id) {
        return null;
    }

    @Override
    public List<Prospect> getListProspect() {
        return null;
    }
}
