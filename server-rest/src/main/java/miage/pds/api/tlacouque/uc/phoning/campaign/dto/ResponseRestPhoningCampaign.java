package miage.pds.api.tlacouque.uc.phoning.campaign.dto;

import miage.pds.api.mbalabascarin.uc.mock.contact.model.Contact;

import java.util.List;

/**
 * Created by tlacouque on 27/03/2016.
 */
public class ResponseRestPhoningCampaign {
    List<Contact> contacts;


    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
