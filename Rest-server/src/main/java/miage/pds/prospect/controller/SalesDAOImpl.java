package miage.pds.prospect.controller;

import com.mongodb.MongoClient;
import miage.pds.prospect.dao.SalesDAO;
import miage.pds.prospect.model.Sales;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Truong on 12/22/2015.
 */
public class SalesDAOImpl extends BasicDAO<Sales, ObjectId> implements SalesDAO{

    private static final String ID_CLIENT       = "idClient";
    private static final String VALUE           = "value";
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    /**
     *
     * @param mongoClient
     * @param morphia
     * @param dbName
     */
    public SalesDAOImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public SalesDAOImpl(Class<Sales> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    @Override
    public List<Sales> getAllSales() {
        Query<Sales> query = createQuery();
        return query.asList();
    }


    @Override
    public long getCountAllSales() {
        long i = getDs().createQuery(Sales.class).countAll();
        return i;
    }


    @Override
    public List<Sales> getSalesByIDClient(int idClient) {
        Query<Sales> query = createQuery().field(ID_CLIENT).equal(idClient);
        return query.asList();
    }


}
