package miage.pds.prospect.controller;

import miage.pds.prospect.dao.ProspectDAO;
import miage.pds.prospect.model.Prospect;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 12/22/2015.
 */
public class ProspectDAOImpl extends BasicDAO<Prospect, ObjectId> implements ProspectDAO{

    private static final String ID      = "id";
    private static final String NAME    = "name";
    private static final String PLACE   = "place";
    /**
     *
     * @param entityClass
     * @param ds
     */
    public ProspectDAOImpl(Class<Prospect> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    /**
     *
     * @param id
     * @param name
     * @return
     */
    @Override
    public Prospect getProspectByIDAndName(int id, String name) {
        Query<Prospect> query = createQuery().field(ID).equal(id).field(NAME).equal(name);
        return query.get();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Prospect> getAllProspect() {
        Query<Prospect> query = createQuery();
        return query.asList();
    }

}
