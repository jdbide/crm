package admin.customer.crud.controller;

import admin.customer.crud.createhc.entities.HealthCenter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by tlacouque on 16/12/2015.
 */

@Controller
public class RestCustomerController {

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/customer/create/hc/{hc}", method = RequestMethod.GET)
    public @ResponseBody String createHealthCenter(HealthCenter hc) {




        return "REST SERVER IS RUNNING :)";
    }
}
