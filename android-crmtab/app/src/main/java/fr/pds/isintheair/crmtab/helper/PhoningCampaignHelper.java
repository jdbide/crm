package fr.pds.isintheair.crmtab.helper;

import java.util.LinkedHashMap;
import java.util.List;

import fr.pds.isintheair.crmtab.model.entity.Contact;
import fr.pds.isintheair.crmtab.model.entity.Customer;

/**
 * Created by tlacouque on 03/04/2016.
 */
public class PhoningCampaignHelper {


    public static boolean isLastContact(LinkedHashMap<Customer,List<Contact>> customerListHashMap,
                                        int currentContactPosition,Customer currentCustomer) {
        int futurContactPosition = currentContactPosition + 1;
        return customerListHashMap.get(currentCustomer).size()-1 == futurContactPosition;
    }
}
