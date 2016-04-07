package fr.pds.isintheair.crmtab.helper;

import java.util.LinkedHashMap;
import java.util.List;

import fr.pds.isintheair.crmtab.model.entity.Contact;
import fr.pds.isintheair.crmtab.model.entity.Customer;

/**
 * Created by tlacouque on 03/04/2016.
 * Class used to help the phoning campaign controller
 */
public class PhoningCampaignHelper {

    /**
     * Static method used to know if the currentContact pass by the position is the last contact
     * of a customer pass in parameter in the linked list in parameter too.
     * @param customerListHashMap
     * @param currentContactPosition
     * @param currentCustomer
     * @return
     */
    public static boolean isLastContact(LinkedHashMap<Customer,List<Contact>> customerListHashMap,
                                        int currentContactPosition,Customer currentCustomer) {
        int futurContactPosition = currentContactPosition + 1;
        return customerListHashMap.get(currentCustomer).size() == futurContactPosition;
    }
}
