package miage.pds.admin.customer.crud.controller;

import miage.pds.admin.customer.crud.message.MessageRestCustomer;
import miage.pds.admin.customer.crud.message.ResponseRestCustomer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by tlacouque on 16/12/2015.
 */

@Controller
public class RestCustomerController {

    public RestCustomerController() {
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/customer/hc/create/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseRestCustomer createHealthCenter(MessageRestCustomer MessageRestCustomer) {

      //  final ObjectMapper mapper = new ObjectMapper();
       // final ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);

       /** MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

        HealthCenter healthCenter = null;

        try {
            healthCenter = mapper.readValue(hc,HealthCenter.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mongoOperation.save(healthCenter);
        */
        //System.out.println(MessageRestCustomer.getHealthEtablishment().getName());


        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIdUser(1);


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
