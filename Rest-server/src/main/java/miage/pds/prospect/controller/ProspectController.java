package miage.pds.prospect.controller;

import miage.pds.prospect.dao.ProspectDAO;
import miage.pds.prospect.dao.SalesDAO;
import miage.pds.prospect.dao.UserClientRelationDAO;
import miage.pds.prospect.dao.UserDAO;
import miage.pds.prospect.model.Prospect;
import miage.pds.prospect.model.Sales;
import miage.pds.prospect.model.User;
import miage.pds.prospect.model.UserClientRelation;
import miage.pds.prospect.service.MongoService;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;

import java.util.*;

/**
 * This is a class which control the algorithm to find the prospect for each user.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class ProspectController {

    private MongoService mongoService;
    private ProspectDAO prospectDAO;
    private SalesDAO salesDAO;
    private UserClientRelationDAO userClientRelationDAO;
    private UserDAO userDAO;
    private static Logger logger = MorphiaLoggerFactory.get(ProspectController.class);


    /**
     * Constructor allows to create new instance of Controller
     */
    public ProspectController() {
        this.mongoService = new MongoService();
        this.prospectDAO = new ProspectDAOImpl(Prospect.class, mongoService.getDatastore());
        this.salesDAO = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
        this.userDAO = new UserDAOImpl(User.class, mongoService.getDatastore());
        this.userClientRelationDAO = new UserClientRelationDAOImpl(UserClientRelation.class, mongoService.getDatastore());
    }

    /**
     * The function which analyzes prospect in the database.
     * It analyzes by sales volume then relationship level and finally by place
     * @return userListHashMap
     */
    public HashMap<User, ArrayList<Prospect>> analyseProspect() {
        HashMap<User, ArrayList<Prospect>> userListHashMap = getProspectListForEachUser();
        analyzeProspectBySales(userListHashMap);
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userListHashMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> userArrayListEntry = entryIterator.next();
            ArrayList<Prospect> prospects = userArrayListEntry.getValue();
            User userKey = userArrayListEntry.getKey();
            if (prospects.size() == 0){
                List<Prospect> prospectList = prospectDAO.getAllProspect();
                ArrayList<Prospect> newProspectList = new ArrayList<Prospect>();
                for (Prospect prospect: prospectList){
                    boolean isRelationship = userClientRelationDAO.checkRelation(userKey.getId(), prospect.getId());
                    if (isRelationship == true){
                        newProspectList.add(prospect);
                    }
                }
                userListHashMap.put(userKey, newProspectList);
            }
        }
        logger.info("Before analyze by Relation lv: /n " );
        logger.info(userListHashMap.toString());
        analyzeProspectByRelationLv(userListHashMap);
        logger.info("After: /n");
        logger.info(userListHashMap.toString());
        return userListHashMap;
    }

    /**
     * The function allows to get a list of prospect for each user
     * @return userListHashMap
     */
    public HashMap<User, ArrayList<Prospect>> getProspectListForEachUser() {
        HashMap<User, ArrayList<Prospect>> userListHashMap = new HashMap<User, ArrayList<Prospect>>();
        List<User> userList = userDAO.getAllUsers();
        for (User user : userList) {
            List<Prospect> prospects = prospectDAO.getAllProspect();
            ArrayList<Prospect> prospectList = new ArrayList<Prospect>();
            for (Prospect prospect : prospects) {
                boolean isRelationship = userClientRelationDAO.checkRelation(user.getId(), prospect.getId());
                if (isRelationship == false) {
                    prospectList.add(prospect);
                } else {
                    logger.info("This has a relationship between " + user.getLogin() + " and " + prospect.getName());
                }
            }
            userListHashMap.put(user, prospectList);
        }
        logger.info(userListHashMap.toString());
        return userListHashMap;
    }

    /**
     * @param userHashMap
     * @return userListHashMap
     */
    public HashMap<User, ArrayList<Prospect>> analyzeProspectBySales(HashMap<User, ArrayList<Prospect>> userHashMap) {
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userHashMap.entrySet().iterator();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date date = calendar.getTime();
        double salesAverageTotal = getSalesAverage();
        while (entryIterator.hasNext()) {
            Map.Entry<User, ArrayList<Prospect>> userListEntry = entryIterator.next();
            ArrayList<Prospect> prospects = userListEntry.getValue();
            Iterator<Prospect> prospectIterator = prospects.iterator();
            while (prospectIterator.hasNext()) {
                Prospect prospect = prospectIterator.next();
                double salesTotal = 0.0d;
                double salesAveByPros = 0.0d;
                List<Sales> sales = salesDAO.getSalesByIDClient(prospect.getId());
                Iterator<Sales> salesIterator = sales.iterator();
                if (sales.size() > 0) {
                    while (salesIterator.hasNext()) {
                        Sales sales1 = salesIterator.next();
                        if (sales1.getDate().after(date)) {
                            salesTotal = salesTotal + sales1.getValue();
                        } else {
                            salesIterator.remove();
                        }
                    }
                } else {
                    prospectIterator.remove();
                }
                salesAveByPros = salesTotal / sales.size();

                if (salesAveByPros < salesAverageTotal) {
                    prospectIterator.remove();
                }
            }
        }
        logger.info("ok");
        logger.info(userHashMap.toString());
        return userHashMap;
    }

    /**
     *
     * @param userHashMap
     * @return userListHashMap
     */
    public HashMap<User, ArrayList<Prospect>> analyzeProspectByRelationLv(HashMap<User, ArrayList<Prospect>> userHashMap){
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userHashMap.entrySet().iterator();
        logger.info("check");
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> userListEntry = entryIterator.next();
            ArrayList<Prospect> prospects = userListEntry.getValue();
            HashMap<Prospect, Long> prospectLongHashMap = new HashMap<Prospect, Long>();
            if (prospects.size() > 0){
                for (Prospect prospect: prospects){
                    long count = userClientRelationDAO.countRelationshipByClientID(prospect.getId());
                    prospectLongHashMap.put(prospect, count);
                }
                long max = Collections.max(prospectLongHashMap.values());
                logger.info(String.valueOf(max));
                for (Map.Entry<Prospect, Long> prospectLongEntry : prospectLongHashMap.entrySet()){
                    if (prospectLongEntry.getValue() != max){
                        logger.info(String.valueOf(prospectLongEntry.getValue()));
                        prospects.remove(prospectLongEntry.getKey());
                    }
                }
            }

            logger.info(prospectLongHashMap.toString());

        }
        return userHashMap;
    }


    /**
     * Function calculate the average of all sales in the table.
     * Firstly, it counts all sales in the table
     * Secondly, it calculates all sales value
     * average = sum / count.
     * @return average
     */
    public double getSalesAverage() {
        double sumSalesValue = 0.0d;
        long count = salesDAO.getCountAllSales();
        List<Sales> sales = salesDAO.getAllSales();
        for (Sales sales1 : sales) {
            sumSalesValue = sumSalesValue + sales1.getValue();
        }
        double average = sumSalesValue / count;
        logger.info(String.valueOf(average));
        return average;
    }


}
