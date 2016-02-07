package miage.pds.api.ctruong.uc.prospect.suggest.controller;

import miage.pds.api.ctruong.uc.prospect.suggest.dao.*;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.model.RelationUserClient;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger logger = LoggerFactory.getLogger(ProspectController.class);
    private ProspectDAO prospectDAO;
    private RelationUserClientDAO relationUserClientDAO;
    private SalesDAO salesDAO;


    /**
     * Constructor allows to create new instance of Controller
     */
    public ProspectController() {
        this.mongoService           = new MongoService();
        this.prospectDAO = new ProspectDAOImp(Prospect.class, mongoService.getDatastore());
        this.salesDAO = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
        this.relationUserClientDAO = new RelationUserClientDAOImpl(RelationUserClient.class, mongoService.getDatastore());
    }

    public List<Prospect> getListProspectByTurnover(){
        List<Prospect> prospects = prospectDAO.getListProspect();
        Iterator<Prospect> iterator = prospects.iterator();
        while (iterator.hasNext()){
            Prospect prospect = iterator.next();
            if (relationUserClientDAO.checkRelationBetweenUserAndClient(prospect.getId()) == true){
                iterator.remove();
            }
        }
        logger.info("The system found " + prospects.size() + " prospects which haven't relationship with commercials");
        long average = getSalesAverage(prospects);
        logger.info(prospects.toString());
        Iterator<Prospect> iterator1 = prospects.iterator();
        while (iterator1.hasNext()){
            Prospect prospect = iterator1.next();
            if (prospect.getTurnover() == 0 || prospect.getTurnover() < average){
                iterator1.remove();
            }
        }
        logger.info("The system found " + prospects.size() + " prospects which have their name in the market during 6 month");
        logger.info(prospects.toString());
        Collections.sort(prospects, new Comparator<Prospect>() {
            @Override
            public int compare(Prospect o1, Prospect o2) {
                return (int) (o1.getTurnover() - o2.getTurnover());
            }
        });

        logger.info("The system sorts " + prospects.size() + " prospects which have their name in the market during 6 month");
        logger.info(prospects.toString());
        return prospects;
    }

    private long getSalesAverage(List<Prospect> prospectsList){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date date = calendar.getTime();
        logger.info("6 month ago was: " + String.valueOf(date));
        HashMap<Prospect,Long> hashMap = new HashMap<Prospect, Long>();
        List<Prospect> prospects = prospectsList;
        Iterator<Prospect> iterator = prospects.iterator();
        while (iterator.hasNext()){
            Prospect prospect = iterator.next();
            List<Sales> sales = salesDAO.getSalesSixMonthEachProspect(prospect.getId(), date);
            long turnover = 0;
            for (Sales sale: sales){
                turnover += sale.getValue();
            }
            if (sales.size() == 0){
                long marketShare = 0;
                prospect.setTurnover(marketShare);
                hashMap.put(prospect, marketShare);
            } else {
                long marketShare = turnover/sales.size();
                prospect.setTurnover(marketShare);
                hashMap.put(prospect, marketShare);
            }
        }

        long sum = 0;
        for (long s : hashMap.values()){
            sum += s;
        }
        logger.info("TurnOver's sum: " + sum);
        long average = sum /hashMap.size();
        logger.info("Average is " + average);


        return average;
    }

}
