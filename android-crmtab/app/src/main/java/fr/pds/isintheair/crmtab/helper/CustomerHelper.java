package fr.pds.isintheair.crmtab.helper;

import java.util.List;

import fr.pds.isintheair.crmtab.model.entity.Customer;
import fr.pds.isintheair.crmtab.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.model.entity.Independant;

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

    public static void addHCIndependantIntoList(List<Customer> customers,List<Independant>independants
                                             , List<HealthCenter> healthCenters) {
        for(Customer customer:customers) {
            if(customer instanceof HealthCenter) {
                healthCenters.add((HealthCenter) customer);
            } else if(customer instanceof Independant)  {
                independants.add((Independant) customer);
            }
        }
    }
}
