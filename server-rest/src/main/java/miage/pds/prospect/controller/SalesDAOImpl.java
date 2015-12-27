package miage.pds.prospect.controller;

import miage.pds.prospect.dao.SalesDAO;
import miage.pds.prospect.model.Sales;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 12/22/2015.
 */
public class SalesDAOImpl extends BasicDAO<Sales, ObjectId> implements SalesDAO{

    private static final String ID_CLIENT       = "idClient";
    private static final String VALUE           = "value";

    /**
     *
     * @param entityClass
     * @param ds
     */
    public SalesDAOImpl(Class<Sales> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    /**
     *
     * @param idClient
     * @return
     */
    @Override
    public List<Sales> getSalesByIdProspect(int idClient) {
        Query<Sales> query = createQuery().filter(ID_CLIENT + " ==" , idClient);
        return query.asList();
    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    public Sales getSalesBySalesValue(double value) {
        Query<Sales> query = createQuery().field(VALUE).equal(value);
        return query.get();
    }

    @Override
    public List<Sales> getAllSales() {
        Query<Sales> query = createQuery();
        return query.asList();
    }


}
