package miage.pds.admin.customer.crud.controller;

import com.mongodb.MongoClient;
import miage.pds.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.admin.customer.crud.message.MessageRestCustomer;
import miage.pds.admin.customer.crud.message.ResponseRestCustomer;
import miage.pds.orm.SpringMongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.UnknownHostException;


/**
 * Created by tlacouque on 16/12/2015.
 */

@Controller
public class RestCustomerController {

    public static String PACKAGE_NAME = "miage.pds.admin.customer.crud.createhc.entities";

    public RestCustomerController() {
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/customer/hc/create/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseRestCustomer createHealthCenter(MessageRestCustomer messageRestCustomer) {


        final Morphia morphia = new Morphia();
        morphia.mapPackage(PACKAGE_NAME,true);
        Datastore datastore = null;

        try {
            datastore = morphia.createDatastore(new MongoClient(), SpringMongoConfig.DB_NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        datastore.ensureIndexes();
        datastore.save(messageRestCustomer.getHealthCenter());
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIdClient(messageRestCustomer.getHealthCenter().getId());

        return responseRestCustomer;
    }

    @RequestMapping(value = "/customer/holding", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getHoldings() {
    ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        return responseRestCustomer;
    }

    @RequestMapping(value = "/customer/purchasingcentral", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getPurchasingCentrals() {
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        return responseRestCustomer;
    }
}
