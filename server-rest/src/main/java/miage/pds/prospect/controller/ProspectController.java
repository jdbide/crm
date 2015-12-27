package miage.pds.prospect.controller;

import miage.pds.prospect.dao.ProspectDAO;
import miage.pds.prospect.dao.SalesDAO;
import miage.pds.prospect.model.Prospect;
import miage.pds.prospect.model.Sales;
import miage.pds.prospect.service.MongoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Truong on 12/20/2015.
 */
public class ProspectController {
    private MongoService    mongoService;
    private ProspectDAO     prospectDAO;
    private SalesDAO        salesDAO;

    /**
     * Constructor allows to create new instance of Controller
     */
    public ProspectController() {
        this.mongoService = new MongoService();
        this.prospectDAO  = new ProspectDAOImpl(Prospect.class, mongoService.getDatastore());
        this.salesDAO     = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
    }

    /**
     *
     */
    public void getSalesByProspect(){
        List<Prospect> prospects    = prospectDAO.getAllProspect();
        List<Sales> sales           = salesDAO.getAllSales();
        for (Prospect prospect: prospects){
            int i = 0;
            Map<Double, Prospect> myMap = new HashMap<Double, Prospect>();
            double value = 0.0d;
            for (Sales sales1 : sales){
                if (prospect.getId() == sales1.getIdProspect()){
                    value = value + sales1.getValue();
                    System.out.println(value);
                    i++;
                    myMap.put(value, prospect);
                }
                System.out.println(myMap);
            }

        }
    }
}
