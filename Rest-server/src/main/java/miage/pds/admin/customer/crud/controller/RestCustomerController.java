package miage.pds.admin.customer.crud.controller;

import com.mongodb.MongoClient;
import miage.pds.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.admin.customer.crud.createhc.entities.Holding;
import miage.pds.admin.customer.crud.createhc.entities.PurchasingCentral;
import miage.pds.admin.customer.crud.message.MessageRestCustomer;
import miage.pds.admin.customer.crud.message.ResponseRestCustomer;
import miage.pds.orm.SpringMongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.UnknownHostException;
import java.util.List;


/**
 * Created by tlacouque on 16/12/2015.
 */

@Controller
public class RestCustomerController {

    public static String PACKAGE_NAME = "miage.pds.admin.customer.crud.createhc.entities";

    private static final Logger logger = LoggerFactory.getLogger(RestCustomerController.class);

    Datastore datastore;

    final Morphia morphia = new Morphia();

    public RestCustomerController() {
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/customer/hc/create/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseRestCustomer createHealthCenter(@RequestBody MessageRestCustomer messageRestCustomer) {

        getDataStore().ensureIndexes();
        datastore.save(messageRestCustomer.getHealthCenter());
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(true);
        return responseRestCustomer;
    }

    @RequestMapping(value = "/customer/holding", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getHoldings() {
        final Query<Holding> query = getDataStore().createQuery(Holding.class);
        final List<Holding> holdings = query.asList();
    ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setHoldings(holdings);
        return responseRestCustomer;
    }

    @RequestMapping(value = "/customer/purchasingcentral", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getPurchasingCentrals() {
        final Query<PurchasingCentral> query = getDataStore().createQuery(PurchasingCentral.class);
        final List<PurchasingCentral> purchasingCentrals = query.asList();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setPurchasingCentrals(purchasingCentrals);
        return responseRestCustomer;
    }

    private Datastore getDataStore() {
        if(datastore == null ) {
            try {
                datastore = morphia.createDatastore(new MongoClient(), SpringMongoConfig.DB_NAME);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return datastore;
    }
}
