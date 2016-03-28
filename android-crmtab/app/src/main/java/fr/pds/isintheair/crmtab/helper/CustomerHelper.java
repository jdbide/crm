package fr.pds.isintheair.crmtab.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.pds.isintheair.crmtab.model.entity.Contact;
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

    public static HashMap<Customer,List<Contact>> getCustomerContactsMap
            (List<Customer> customers,List<Contact> contacts) {
        HashMap<Customer,List<Contact>> customerListHashMap = new HashMap<Customer,List<Contact>>();

        for(Customer customer : customers) {
            List<Contact> customerContactList = new ArrayList<Contact>();
            for(Contact contact : contacts) {
                if(customer.getSiretNumber() == contact.clientId) {
                    customerContactList.add(contact);
                    customerListHashMap.put(customer,customerContactList);
                }
            }
        }

        return customerListHashMap;
    }

    public static List<String> getCustomersName(List<Customer> customers) {
        List<String> customersName = new ArrayList<>();
        for(Customer customer : customers) {
            customersName.add(customer.getName());
        }
        return customersName;
    }

    public static List<String> getCustomerContactName(HashMap<Customer,List<Contact>> hashMap) {
        List<String> customerContactName = new ArrayList<>();
        for(Customer customer : hashMap.keySet()) {
            for(Contact contact : hashMap.get(customer)) {
                customerContactName.add(customer.getName()+" : "+contact.contactName + " "+contact.contactFname);
            }
        }

        return customerContactName;
    }

    public static Contact getContactFromName(String name,List<Contact> contacts) {
        Contact contactReturn = null;
        String[] str_array = name.split(" : ");
        name = str_array[1];
        for(Contact contact : contacts) {
            if(name.equals(contact.contactName + " "+contact.contactFname)) {
                contactReturn = contact;
            }
        }
        return contactReturn;
    }

}
