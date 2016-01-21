package miage.pds.prospect.mock;

import miage.pds.prospect.controller.ProspectDAOImpl;
import miage.pds.prospect.controller.SalesDAOImpl;
import miage.pds.prospect.controller.UserClientRelationDAOImpl;
import miage.pds.prospect.controller.UserDAOImpl;
import miage.pds.prospect.dao.ProspectDAO;
import miage.pds.prospect.dao.SalesDAO;
import miage.pds.prospect.dao.UserClientRelationDAO;
import miage.pds.prospect.dao.UserDAO;
import miage.pds.prospect.model.Prospect;
import miage.pds.prospect.model.Sales;
import miage.pds.prospect.model.User;
import miage.pds.prospect.model.UserClientRelation;
import miage.pds.prospect.service.MongoService;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


/**
 * Created by Truong on 1/20/2016.
 */
public class MockTable {
    private static final Logger log = MorphiaLoggerFactory.get(MockTable.class);
    private MongoService mongoService;
    private UserDAO userDAO;
    private UserClientRelationDAO userClientRelationDAO;
    private SalesDAO salesDAO;
    private ProspectDAO prospectDAO;
    private Datastore datastore;

    /**
     * Constructor
     */
    public MockTable() {
        this.mongoService           = new MongoService();
        this.userDAO                = new UserDAOImpl(User.class, mongoService.getDatastore());
        this.prospectDAO            = new ProspectDAOImpl(Prospect.class, mongoService.getDatastore());
        this.userClientRelationDAO  = new UserClientRelationDAOImpl(UserClientRelation.class, mongoService.getDatastore());
        this.salesDAO               = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
        this.datastore              = mongoService.getDatastore();
    }

    /**
     *
     */
    public void mockUserTable(){
        for (int i = 0; i < 100; i++){
            User user = new User(i, "test" + i + "@gmail.com");
            log.info("The user " + i);
            datastore.save(user);
            log.info("The user " + i + " is created ....");
        }
    }

    public void mockClientTable(){
        Random random = new Random();
        for (int i = 0; i < 1000; i++){
            Prospect prospect = new Prospect();
            prospect.setId(i);
            prospect.setName("Client " + i);
            int randomInt = random.nextInt(200);
            prospect.setStreetNumber(randomInt);
            int randomLong = random.nextInt(1000000000);
            prospect.setFinessNumber(randomLong);
            prospect.setSiretNumber(randomLong);
            prospect.setAddress("rue Test " + randomInt);
            prospect.setWebsite("www.abc" + i + ".com");
            int randomPlace = random.nextInt(10000);
            prospect.setPlace(randomPlace);
            log.info("The client " + i);
            datastore.save(prospect);
            log.info("The client " + i + " is created ....");
        }
    }

    /**
     *
     */
    public void mockRelationAndSalesTable(){
        Random random = new Random();
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 10; j++){
                UserClientRelation userClientRelation = new UserClientRelation();
                userClientRelation.setIdUser(i);
                int randomClient = random.nextInt(1000);
                userClientRelation.setIdClient(randomClient);
                int randomSalesPerRelation = random.nextInt(10);
                datastore.save(userClientRelation);
                log.info("Number of sales: " + randomSalesPerRelation);
                for (int k = 0; k <= randomSalesPerRelation; k++){
                    Sales salesMock = new Sales();
                    salesMock.setIdSeller(i);
                    salesMock.setIdClient(randomClient);
                    double randomValueSales = random.nextDouble()*200000.0;
                    salesMock.setValue(randomValueSales);
                    int year = randBetween(2015, 2016);
                    int month = randBetween(0, 11);
                    int hour = randBetween(9,20);
                    int min = randBetween(0,59);
                    int sec = randBetween(0,59);
                    GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, 1);
                    int day = randBetween(1, gregorianCalendar.getActualMaximum(gregorianCalendar.DAY_OF_MONTH));
                    gregorianCalendar.set(year, month, day, hour, min, sec);
                    Date date = gregorianCalendar.getTime();
                    salesMock.setDate(date);
                    datastore.save(salesMock);
                }
            }
        }
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
