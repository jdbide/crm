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


import java.util.ArrayList;
import java.util.List;

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

    public List<Prospect> getProspectByRelationshipLevel(){
        List<Prospect> prospects        = new ArrayList<Prospect>();
        List<Prospect> prospectBySales  = getProspectsBySalesSuperiorThanAverage();
        for (Prospect prospect : prospectBySales){
            List<UserClientRelation> userClientRelations = userClientRelationDAO.getUserByClientId(prospect.getId());
            System.out.println(userClientRelations.size());
        }
        return prospects;
    }
    /**
     *
     * @return
     */
    public List<Prospect> getProspectsBySalesSuperiorThanAverage(){
        List<Prospect> prospects  = new ArrayList<Prospect>();
        logger.info("hello");
        double salesAverage = getSalesAverage();
        List<Sales> salesProspect = salesDAO.getSalesSuperiorThanAverage(salesAverage);
        int i = 0;
        for (Sales sales : salesProspect){
            Prospect prospect     = prospectDAO.getProspectByID(sales.getIdProspect());
            prospects.add(i, prospect);
            i++;
        }
        return prospects;
    }

    /**
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
        return average;
    }



}
