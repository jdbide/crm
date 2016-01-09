package miage.pds.api.admin.customer.crud.controller;

import com.mongodb.MongoClient;
import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.api.admin.customer.crud.createhc.entities.Holding;
import miage.pds.api.admin.customer.crud.createhc.entities.PurchasingCentral;
import miage.pds.api.admin.customer.crud.createindep.entities.Company;
import miage.pds.api.admin.customer.crud.createindep.entities.Independant;
import miage.pds.api.admin.customer.crud.createindep.entities.Specialty;
import miage.pds.api.admin.customer.crud.message.MessageRestCustomer;
import miage.pds.api.admin.customer.crud.message.ResponseRestCustomer;
import miage.pds.api.admin.customer.crud.SpringMongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;


/**
 * Created by tlacouque on 16/12/2015.
 * Rest controller used to handle message from commercials tablet.
 */

@Controller
public class RestCustomerController {

    static Datastore datastore;

    static final Morphia morphia = new Morphia();

    public RestCustomerController() {
    }

    /**
     * Take a messageRestCustomer (dto) in parameter with an healthcenter in it.
     * Save the healthcenter and return true with a ResponseRestCustomer (dto)
     * @param messageRestCustomer
     * @return ResponseRestCustomer
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

    /**
     * Take a messageRestCustomer (dto) in parameter with an independant in it.
     * Save the independant and return true with a ResponseRestCustomer (dto)
     * @param messageRestCustomer
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/indep/create/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseRestCustomer createIndependant(@RequestBody MessageRestCustomer messageRestCustomer) {

        getDataStore().ensureIndexes();
        datastore.save(messageRestCustomer.getIndependant());
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(true);
        return responseRestCustomer;
    }

    /**
     * Used to initialise holding in crm tab. It return a list of holding with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/holding", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getHoldings() {
        final Query<Holding> query = getDataStore().createQuery(Holding.class);
        final List<Holding> holdings = query.asList();
    ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setHoldings(holdings);
        return responseRestCustomer;
    }

    /**
     * Used to initialise purchasingcentral in crm tab.
     * It return a list of purchasingcentral with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/purchasingcentral", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getPurchasingCentrals() {
        final Query<PurchasingCentral> query = getDataStore().createQuery(PurchasingCentral.class);
        final List<PurchasingCentral> purchasingCentrals = query.asList();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setPurchasingCentrals(purchasingCentrals);
        return responseRestCustomer;
    }

    /**
     * Used to initialise company in crm tab. It return a list of company with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/company", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getCompanies() {
        final Query<Company> query = getDataStore().createQuery(Company.class);
        final List<Company> companies = query.asList();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setCompanies(companies);
        return responseRestCustomer;
    }

    /**
     * Used to initialise specialty in crm tab. It return a list of specialty with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/specialty", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getSpecialties() {
        final Query<Specialty> query = getDataStore().createQuery(Specialty.class);
        final List<Specialty> specialties = query.asList();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setSpecialties(specialties);
        return responseRestCustomer;
    }

    /**
     * Interface used to request all healthcenter created by someone who has not the user id pass in parameter.
     * HealthCenter are encapsulated in ResponseRestCustomer (dto)
     * @param iduser
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/healthcenter/{iduser}", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getHealthCenters(@PathVariable int iduser) {
        final Query<HealthCenter> query = getDataStore().createQuery(HealthCenter.class).filter("idUser <>",iduser);
        final List<HealthCenter> healthCenters = query.asList();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setHealthCenters(healthCenters);
        return responseRestCustomer;
    }

    /**
     * Interface used to request all independant created by someone who has not the user id pass in parameter.
     * Independant are encapsulated in ResponseRestCustomer (dto)
     * @param iduser
     * @return
     */
    @RequestMapping(value = "/customer/independant/{iduser}", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getIndependants(@PathVariable int iduser) {
        final Query<Independant> query = getDataStore().createQuery(Independant.class).filter("idUser <>",iduser);
        final List<Independant> independants = query.asList();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIndependants(independants);
        return responseRestCustomer;
    }

    /**
     * Interface used to request all healthcenter created by someone who has not the user id pass in parameter.
     * HealthCenter are encapsulated in ResponseRestCustomer (dto)
     * @param iduser
     * @return
     */
    @RequestMapping(value = "/customer/{iduser}", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getCustomers(@PathVariable int iduser) {
        final Query<Independant> queryindep = getDataStore().createQuery(Independant.class).filter("idUser <>",iduser);
        final List<Independant> independants = queryindep.asList();
        final Query<HealthCenter> queryhc = getDataStore().createQuery(HealthCenter.class).filter("idUser <>",iduser);
        final List<HealthCenter> healthCenters = queryhc.asList();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIndependants(independants);
        responseRestCustomer.setHealthCenters(healthCenters);
        return responseRestCustomer;
    }

    /**
     * Used to return datastore to do sql operation
     * @return Datastore
     */
    public static Datastore getDataStore() {
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
