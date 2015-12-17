package admin.customer.crud.controller;

import admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.orm.SpringMongoConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;


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
    public @ResponseBody String createHealthCenter(int idUser) {

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
        System.out.println("Je suis appelé");


        return "{\"idCustomer\": \"1\"}";
    }
}
