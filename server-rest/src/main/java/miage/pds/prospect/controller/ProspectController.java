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

    private MongoService            mongoService;
    private ProspectDAO             prospectDAO;
    private SalesDAO                salesDAO;
    private UserClientRelationDAO   userClientRelationDAO;
    private UserDAO                 userDAO;
    private static Logger           logger = MorphiaLoggerFactory.get(ProspectController.class);


    /**
     * Constructor allows to create new instance of Controller
     */
    public ProspectController() {
        this.mongoService           = new MongoService();
        this.prospectDAO            = new ProspectDAOImpl(Prospect.class, mongoService.getDatastore());
        this.salesDAO               = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
        this.userDAO                = new UserDAOImpl(User.class, mongoService.getDatastore());
        this.userClientRelationDAO  = new UserClientRelationDAOImpl(UserClientRelation.class, mongoService.getDatastore());
    }

    /**
     * The function which analyzes prospect in the database.
     * It analyzes by sales volume then relationship level and finally by place.
     * @return userListHashMap
     */
    public HashMap<User, ArrayList<Prospect>> analyseProspect() {
        HashMap<User, ArrayList<Prospect>>              userListHashMap = getProspectListForEachUser();
        analyzeProspectBySales(userListHashMap);
        Iterator<Map.Entry<User, ArrayList<Prospect>>>  entryIterator   = userListHashMap.entrySet().iterator();

        // Refill the list of prospect for each user
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>>    userArrayListEntry  = entryIterator.next();
            ArrayList<Prospect>                     prospects           = userArrayListEntry.getValue();
            User userKey = userArrayListEntry.getKey();
            if (prospects.size() == 0){
                List<Prospect>      prospectList    = prospectDAO.getAllProspect();
                ArrayList<Prospect> newProspectList = new ArrayList<Prospect>();
                for (Prospect prospect: prospectList){
                    boolean         isRelationship  = userClientRelationDAO.checkRelation(userKey.getId(), prospect.getId());
                    if (isRelationship == true){
                        newProspectList.add(prospect);
                    }
                }
                userListHashMap.put(userKey, newProspectList);
            }
        }
        analyzeProspectByRelationLv(userListHashMap);
        analyzeProspectByPlaceNumber(userListHashMap);
        logger.info(userListHashMap.toString());
        return userListHashMap;
    }

    /**
     * The function allows to get a list of prospect for each user.
     * It puts user and prospect list into a map to make easier to control
     * @return userListHashMap
     */
    public HashMap<User, ArrayList<Prospect>> getProspectListForEachUser() {
        logger.info("Get the first prospects list is launching ...");
        HashMap<User, ArrayList<Prospect>>  userListHashMap = new HashMap<User, ArrayList<Prospect>>();
        List<User>                          userList        = userDAO.getAllUsers();

        // Check prospect list for each user
        for (User user : userList) {
            List<Prospect>      prospects       = prospectDAO.getAllProspect();
            ArrayList<Prospect> prospectList    = new ArrayList<Prospect>();

            // If it doesn't have a relationship between user and prospect, put into the list.
            for (Prospect prospect : prospects) {
                boolean         isRelationship  = userClientRelationDAO.checkRelation(user.getId(), prospect.getId());
                if (isRelationship == false) {
                    prospectList.add(prospect);
                }
            }
            userListHashMap.put(user, prospectList);
        }
        logger.info("End of the function");
        return userListHashMap;
    }

    /**
     * Within the map of user and prospect list, it finds the sales volumes of each prospect in the prospect list.
     * Before, It find only the sales after 6 month ago and calculates the sum of sales of each prospect.
     * Next, it finds the average of sales.
     * Then, it compares the prospect sales average and the average global.
     * If the prospect sales average is superior than global, it keeps the prospect in the list.
     * Else, it removes from the list.
     * Finally, its reduces in the map.
     * @param userHashMap
     * @return userHashMap
     */
    public HashMap<User, ArrayList<Prospect>> analyzeProspectBySales(HashMap<User, ArrayList<Prospect>> userHashMap) {
        logger.info("The function analyze prospect by Sales volume is launching ...");
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userHashMap.entrySet().iterator();

        // The date 6 month before
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date date = calendar.getTime();

        // Average global
        double salesAverageTotal = getSalesAverage();
        // For each user, it has a list of prospects different
        while (entryIterator.hasNext()) {
            Map.Entry<User, ArrayList<Prospect>> userListEntry = entryIterator.next();

            // Analyse the list of prospects
            ArrayList<Prospect>     prospects        = userListEntry.getValue();
            Iterator<Prospect>      prospectIterator = prospects.iterator();
            while (prospectIterator.hasNext()) {
                Prospect prospect = prospectIterator.next();
                double salesTotal = 0.0d;
                double salesAveByPros = 0.0d;

                // Get sales from each prospect
                List<Sales>             sales = salesDAO.getSalesByIDClient(prospect.getId());
                Iterator<Sales> salesIterator = sales.iterator();
                if (sales.size() > 0) {

                    // Analyze the list of sales
                    while (salesIterator.hasNext()) {
                        Sales sales1 = salesIterator.next();

                        // Condition which remove all sales before 6 month ago
                        if (sales1.getDate().after(date)) {
                            salesTotal = salesTotal + sales1.getValue();
                        } else {
                            salesIterator.remove();
                        }
                    }
                } else {
                    prospectIterator.remove();
                }

                // The average for each prospect
                salesAveByPros = salesTotal / sales.size();

                // Remove the prospect in the list inside the map if the average is under global
                if (salesAveByPros < salesAverageTotal) {
                    prospectIterator.remove();
                }
            }
        }
        logger.info("The end of the function.");
        return userHashMap;
    }

    /**
     * The function which analyze the prospect list by their relationship lv.
     * It counts the max value of relationship level in the database.
     * After, it compares each prospect's relationship level and the max value.
     * Remove in the ArrayList<Prospect> if the value is under the max.
     * It keeps only the value equal max.
     * @param userHashMap
     * @return userHashMap
     */
    public HashMap<User, ArrayList<Prospect>> analyzeProspectByRelationLv(HashMap<User, ArrayList<Prospect>> userHashMap){
        logger.info("The function analyze prospect by Relation level is launching ...");

        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userHashMap.entrySet().iterator();

        // Analyze the HashMap<User, ArrayList<Prospect>>
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>>    userListEntry   = entryIterator.next();
            ArrayList<Prospect>                     prospects       = userListEntry.getValue();

            // Create new HashMap<Prospect, long> to compare which prospect is greater relationship level
            HashMap<Prospect, Long>             prospectLongHashMap = new HashMap<Prospect, Long>();
            if (prospects.size() > 0){

                // Put into the map
                for (Prospect prospect: prospects){
                    long count = userClientRelationDAO.countRelationshipByClientID(prospect.getId());
                    prospectLongHashMap.put(prospect, count);
                }
                long max = Collections.max(prospectLongHashMap.values());
                // Compare all prospects in the list with the max value of relationship level
                for (Map.Entry<Prospect, Long> prospectLongEntry : prospectLongHashMap.entrySet()){
                    if (prospectLongEntry.getValue() != max){
                        prospects.remove(prospectLongEntry.getKey());
                    }
                }
            }
        }
        logger.info("End of the function");

        return userHashMap;
    }

    /**
     * Function analyzes the prospect list by the number of place.
     * It finds the max of place.
     * It compares between the prospect's place and the max. If under the max value, remove the prospect from the list.
     * @param userHashMap
     * @return userHashMap
     */
    public HashMap<User, ArrayList<Prospect>> analyzeProspectByPlaceNumber(HashMap<User, ArrayList<Prospect>> userHashMap){
        logger.info("The function analyze prospect by place number is launching ...");
        Iterator<Map.Entry<User, ArrayList<Prospect>>> entryIterator = userHashMap.entrySet().iterator();

        // Analyze the map
        while (entryIterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> userArrayListEntry = entryIterator.next();
            ArrayList<Prospect>                     prospects       = userArrayListEntry.getValue();

            // If the prospect list more than one element
            if (prospects.size() > 1){
                ArrayList<Integer> placeList = new ArrayList<Integer>();

                // Create new list to check the max place
                for (Prospect prospect: prospects){
                    placeList.add(prospect.getPlace());
                }
                int maxPlace = Collections.max(placeList);
                Iterator<Prospect> prospectIterator = prospects.iterator();

                // Remove the prospect if out of condition
                while (prospectIterator.hasNext()){
                    Prospect prospect = prospectIterator.next();
                    if (prospect.getPlace() < maxPlace){
                        prospectIterator.remove();
                    }
                }
            }
        }
        logger.info("End of the function");
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
        logger.info("The function which gets the sales average ... ");
        double  sumSalesValue   = 0.0d;

        // Get the count of sales list
        long        count       = salesDAO.getCountAllSales();

        // Get all sales from the database
        List<Sales> sales       = salesDAO.getAllSales();

        // Calculate sum
        for (Sales sales1 : sales) {
            sumSalesValue       = sumSalesValue + sales1.getValue();
        }

        // Average
        double  average         = sumSalesValue / count;
        logger.info("The average global is " + average);
        return average;
    }


}
