package fr.pds.isintheair.crmtab.model.entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tlacouque on 27/03/2016.
 */
public class ResponseRestPhoningCampaign {
    HashMap<Customer,List<Contact>> contacts;

    public HashMap<Customer, List<Contact>> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<Customer, List<Contact>> contacts) {
        this.contacts = contacts;
    }
}
