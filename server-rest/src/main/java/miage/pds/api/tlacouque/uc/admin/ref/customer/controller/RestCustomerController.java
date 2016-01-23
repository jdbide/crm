package miage.pds.api.tlacouque.uc.admin.ref.customer.controller;

import com.mongodb.MongoClient;


import miage.pds.MongoConfig;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.HealthCenterDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.HoldingDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.PurchasingCentralDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.Holding;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.CompanyDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.IndependantDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.SpecialtyDAO;

import miage.pds.api.tlacouque.uc.admin.ref.customer.message.MessageRestCustomer;
import miage.pds.api.tlacouque.uc.admin.ref.customer.message.ResponseRestCustomer;

import miage.pds.api.tlacouque.uc.admin.ref.customer.SpringMongoConfig;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
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
        boolean customerInserted = true;
        try {
            new HealthCenterDAO(MongoDatastoreConfig.getDataStore()).save(messageRestCustomer.getHealthCenter());
        } catch (Exception e) {
            customerInserted = false;
        }
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(customerInserted);
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
        boolean customerInserted = true;
        try {
            new IndependantDAO(MongoDatastoreConfig.getDataStore()).save(messageRestCustomer.getIndependant());
        } catch (Exception e) {
        customerInserted = false;
    }
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(customerInserted);
        return responseRestCustomer;
    }


    /**
     * Used to initialise holding in crm tab. It return a list of holding with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/holding", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getHoldings() {
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            final List<Holding> holdings = new HoldingDAO(MongoDatastoreConfig.getDataStore()).findAll();
            responseRestCustomer.setHoldings(holdings);
        } catch (Exception e) {
            responseRestCustomer.setHoldings(null);
        }
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
        try {
            responseRestCustomer.setPurchasingCentrals(new PurchasingCentralDAO(MongoDatastoreConfig.getDataStore()).findAll());
        } catch (Exception e) {
            responseRestCustomer.setPurchasingCentrals(null);
        }

        return responseRestCustomer;
    }

    /**
     * Used to initialise company in crm tab. It return a list of company with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/company", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getCompanies() {
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setCompanies(new CompanyDAO(MongoDatastoreConfig.getDataStore()).findAll());
        } catch (Exception e) {
            responseRestCustomer.setCompanies(null);
        }
        return responseRestCustomer;
    }

    /**
     * Used to initialise specialty in crm tab. It return a list of specialty with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/specialty", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getSpecialties() {
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setSpecialties(new SpecialtyDAO(MongoDatastoreConfig.getDataStore()).findAll());
        } catch (Exception e) {
            responseRestCustomer.setSpecialties(null);
        }
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
        try {
            responseRestCustomer.setHealthCenters(new HealthCenterDAO(MongoDatastoreConfig.getDataStore()).findAllWithoutUserId(iduser));
        } catch (Exception e) {
            responseRestCustomer.setHealthCenters(null);
        }
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
        try {
            responseRestCustomer.setIndependants(new IndependantDAO(MongoDatastoreConfig.getDataStore()).findAllWithoutUserId(iduser));
        } catch (Exception e) {
            responseRestCustomer.setIndependants(null);
        }
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
        try {
            responseRestCustomer.setIndependants(new IndependantDAO(MongoDatastoreConfig.getDataStore()).findAllWithoutUserId(iduser));
            responseRestCustomer.setHealthCenters(new HealthCenterDAO(MongoDatastoreConfig.getDataStore()).findAllWithoutUserId(iduser));
        } catch (Exception e) {
            responseRestCustomer.setHealthCenters(null);
            responseRestCustomer.setIndependants(null);
        }
       return responseRestCustomer;
    }

}
