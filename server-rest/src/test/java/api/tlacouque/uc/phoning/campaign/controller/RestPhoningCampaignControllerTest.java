package api.tlacouque.uc.phoning.campaign.controller;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.mbalabascarin.uc.mock.contact.model.Contact;
import miage.pds.api.tlacouque.uc.admin.ref.customer.controller.RestCustomerController;
import miage.pds.api.tlacouque.uc.phoning.campaign.controller.RestPhoningCampaignController;
import miage.pds.api.tlacouque.uc.phoning.campaign.dto.MessageRestPhoningCampaign;
import miage.pds.api.tlacouque.uc.phoning.campaign.dto.ResponseRestPhoningCampaign;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by tlacouque on 29/03/2016.
 */
public class RestPhoningCampaignControllerTest {

    Datastore datastore;
    RestPhoningCampaignController restPhoningCampaignController;

    @Before
    public void setUp() throws Exception {
        this.datastore = MongoDatastoreConfig.getDataStore();
        restPhoningCampaignController = new RestPhoningCampaignController();
    }


    @Test
    public void testGetContacts() throws Exception {

        long client1 = 1L;
        long client2 = 2L;

        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Contact contact3 = new Contact();

        contact1.setClientId(client1);
        contact2.setClientId(client1);
        contact3.setClientId(client2);

        contact1.setContactId(1);
        contact2.setContactId(2);
        contact3.setContactId(3);

        datastore.save(contact1);
        datastore.save(contact2);
        datastore.save(contact3);

        ArrayList<String> strings = new ArrayList<String>();
        strings.add(Long.toString(client1));
        strings.add(Long.toString(client2));

        MessageRestPhoningCampaign message = new MessageRestPhoningCampaign();
        message.setCustomersId(strings);

        ResponseRestPhoningCampaign response = restPhoningCampaignController.getContacts(message);

        datastore.delete(contact1);
        datastore.delete(contact2);
        datastore.delete(contact3);

        assertEquals(3,response.getContacts().size());

    }
}
