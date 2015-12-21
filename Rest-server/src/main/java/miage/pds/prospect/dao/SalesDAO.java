package miage.pds.prospect.dao;

import miage.pds.prospect.model.Sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Truong on 12/20/2015.
 */
public class SalesDAO {

    static final Logger logger = LoggerFactory.getLogger(SalesDAO.class);

    @Autowired
    MongoTemplate mongoTemplate;

    public void findAllSales(){
        List<Sales> sales = mongoTemplate.findAll(Sales.class);
        logger.info("Sales total: {}" , sales.toString());
    }

    /**
     *
     * @return
     */
    public double getSalesAverage(){
        List<Sales> sales = mongoTemplate.findAll(Sales.class);
        double value = 0.0d;
        Iterator<Sales> salesIterator = sales.iterator();
        while (salesIterator.hasNext()){
            Sales nextSales = salesIterator.next();
            value += nextSales.getSalesValue();
        }
        return value / sales.size();
    }

    /**
     * Create a {@link Sales} collection if the collection does not already exists
     */
    public void createCollection(){
        if (!mongoTemplate.collectionExists(Sales.class)) {
            mongoTemplate.createCollection(Sales.class);
        }
    }

    /**
     * Drops the {@link Sales} collection if the collection does already exists
     */
    public void dropPersonCollection() {
        if (mongoTemplate.collectionExists(Sales.class)) {
            mongoTemplate.dropCollection(Sales.class);
        }
    }
}
