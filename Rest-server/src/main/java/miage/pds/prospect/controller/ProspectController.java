package miage.pds.prospect.controller;

import miage.pds.prospect.dao.ProspectDAO;
import miage.pds.prospect.dao.SalesDAO;
import miage.pds.prospect.dao.UserClientRelationDAO;
import miage.pds.prospect.model.Prospect;
import miage.pds.prospect.model.Sales;
import miage.pds.prospect.model.UserClientRelation;
import miage.pds.prospect.service.MongoService;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;

import java.util.*;

/**
 * Created by Truong on 12/20/2015.
 */
public class ProspectController {
    private MongoService            mongoService;
    private ProspectDAO             prospectDAO;
    private SalesDAO                salesDAO;
    private UserClientRelationDAO   userClientRelationDAO;
    private static Logger           logger = MorphiaLoggerFactory.get(ProspectController.class);

    /**
     * Constructor allows to create new instance of Controller
     */
    public ProspectController() {
        this.mongoService           = new MongoService();
        this.prospectDAO            = new ProspectDAOImpl(Prospect.class, mongoService.getDatastore());
        this.salesDAO               = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
        this.userClientRelationDAO  = new UserClientRelationDAOImpl(UserClientRelation.class, mongoService.getDatastore());
    }

    public List<Prospect> getProspectByRelationshipLv(){
        List<Prospect> prospectList = getSalesVolumeByProspect();
        HashMap<Prospect, Long> prospectLongHashMap = new HashMap<Prospect, Long>();
        if (prospectList.size() > 1){
            for (Prospect prospect: prospectList){
                long count = userClientRelationDAO.countRelationshipByClientID(prospect.getId());
                logger.info(String.valueOf(count));
                prospectLongHashMap.put(prospect, count);
            }
            long max = Collections.max(prospectLongHashMap.values());
            for (Map.Entry<Prospect, Long> entry : prospectLongHashMap.entrySet()){
                if (entry.getValue() == max){
                    logger.info(String.valueOf(max));
                    logger.info(entry.getKey().toString());
                }
            }
        }

        return prospectList;
    }
    
    /**
     * Function to calculate sales volume for each client
     * @return
     */
    public List<Prospect> getSalesVolumeByProspect(){
        List<Prospect>  prospectList    = new ArrayList<Prospect>();
        List<Prospect>  prospects       = prospectDAO.getAllProspect();
        double          saleAverageTotal= getSalesAverage();
        for (Prospect prospect: prospects){
            List<Sales> sales               = salesDAO.getSalesByIDClient(prospect.getId());
            double      salesTotal          = 0.0d;
            double      salesAveByPros      = 0.0d;
            logger.info("This is my client: " + prospect.toString());
            if (sales.size() > 0){
                for (int i = 0; i < sales.size(); i++){
                    Sales sales1    = sales.get(i);
                    logger.info(String.valueOf(sales.get(i)));
                    salesTotal      = salesTotal + sales1.getValue();
                }
                salesAveByPros  = salesTotal / sales.size();
            } else {
                for (Sales sales1 : sales){
                    salesTotal     = sales1.getValue();
                    logger.info(sales1.toString());
                    salesAveByPros = salesTotal;
                }
            }
            logger.info("This is my sales volume: " + String.valueOf(salesTotal));
            if (salesAveByPros > 0 && salesAveByPros > saleAverageTotal){
                prospectList.add(prospect);
            }
        }
        logger.info("This is my list of prospect");
        logger.info(prospectList.toString());
        return prospectList;
    }

    /**
     * Function calculate the average of all sales in the table
     * @return average
     */
    public double getSalesAverage(){
        double      sumSalesValue   = 0.0d;
        long        count           = salesDAO.getCountAllSales();
        List<Sales> sales           = salesDAO.getAllSales();
        for (Sales sales1 : sales){
            sumSalesValue = sumSalesValue + sales1.getValue();
        }
        double      average         = sumSalesValue / count;
        logger.info(String.valueOf(average));
        return average;
    }



}
