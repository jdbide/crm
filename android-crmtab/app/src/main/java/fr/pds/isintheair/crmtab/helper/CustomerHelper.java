package fr.pds.isintheair.crmtab.helper;

import java.util.List;

import fr.pds.isintheair.crmtab.model.entity.Customer;

/**
 * Created by tlacouque on 27/03/2016.
 */
public class CustomerHelper {

    public static Customer getCustomerFromName(String name,List<Customer> customers) {
        Customer customerReturn = null;
        for(Customer customerfor : customers) {
            if(name.equals(customerfor.getName())) {
                customerReturn = customerfor;
            }
        }
        return customerReturn;
    }
}
