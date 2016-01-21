package miage.pds.api.admin.customer.crud.controller;

import com.mongodb.MongoClient;


import miage.pds.api.MongoConfig;
import miage.pds.api.admin.customer.crud.createhc.dao.HealthCenterDAO;
import miage.pds.api.admin.customer.crud.createhc.dao.HoldingDAO;
import miage.pds.api.admin.customer.crud.createhc.dao.PurchasingCentralDAO;
import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.api.admin.customer.crud.createhc.entities.Holding;
import miage.pds.api.admin.customer.crud.createhc.entities.PurchasingCentral;
import miage.pds.api.admin.customer.crud.createindep.dao.CompanyDAO;
import miage.pds.api.admin.customer.crud.createindep.dao.IndependantDAO;
import miage.pds.api.admin.customer.crud.createindep.dao.SpecialtyDAO;

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
        new HealthCenterDAO(getDataStore()).save(messageRestCustomer.getHealthCenter());

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
        new IndependantDAO(getDataStore()).save(messageRestCustomer.getIndependant());
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
        final List<Holding> holdings = new HoldingDAO(getDataStore()).findAll();
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
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setPurchasingCentrals(new PurchasingCentralDAO(getDataStore()).findAll());

        return responseRestCustomer;
    }

    /**
     * Used to initialise company in crm tab. It return a list of company with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/company", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getCompanies() {
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setCompanies(new CompanyDAO(getDataStore()).findAll());
        return responseRestCustomer;
    }

    /**
     * Used to initialise specialty in crm tab. It return a list of specialty with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/specialty", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getSpecialties() {
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setSpecialties(new SpecialtyDAO(getDataStore()).findAll());
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
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setHealthCenters(new HealthCenterDAO(getDataStore()).findAllWithoutUserId(iduser));
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
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIndependants(new IndependantDAO(getDataStore()).findAllWithoutUserId(iduser));
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
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIndependants(new IndependantDAO(getDataStore()).findAllWithoutUserId(iduser));
        responseRestCustomer.setHealthCenters(new HealthCenterDAO(getDataStore()).findAllWithoutUserId(iduser));
        return responseRestCustomer;
    }

    /**
     * Used to return datastore to do sql operation
     * @return Datastore
     */

    public static Datastore getDataStore() {
        if(datastore == null ) {
            try {
                datastore = morphia.createDatastore(new MongoClient(MongoConfig.DEV_IP,MongoConfig.DEV_PORT)
                        , SpringMongoConfig.DB_NAME);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return datastore;
    }
}
