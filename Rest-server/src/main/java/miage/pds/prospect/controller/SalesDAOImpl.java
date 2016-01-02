package miage.pds.prospect.controller;

import miage.pds.prospect.dao.SalesDAO;
import miage.pds.prospect.model.Sales;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.Calendar;
import java.util.List;

/**
 * This is a class dao which declare all method communicate with the sales collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class SalesDAOImpl extends BasicDAO<Sales, ObjectId> implements SalesDAO{

    private static final String ID_CLIENT       = "idClient";
    private static final String VALUE           = "value";
    private int year = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * Constructor DAO
     * @param entityClass
     * @param ds
     */
    public SalesDAOImpl(Class<Sales> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    /**
     * Method to select all sales in the database
     * @return
     */
    @Override
    public List<Sales> getAllSales() {
        Query<Sales> query = createQuery();
        return query.asList();
    }


    /**
     * Method to count all sales in the table
     * @return
     */
    @Override
    public long getCountAllSales() {
        long i = getDs().createQuery(Sales.class).countAll();
        return i;
    }

    /**
     * Method to call all sales by client
     * @param idClient
     * @return
     */
    @Override
    public List<Sales> getSalesByIDClient(int idClient) {
        Query<Sales> query = createQuery().field(ID_CLIENT).equal(idClient);
        return query.asList();
    }


}
