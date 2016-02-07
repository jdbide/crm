package mbalabascarin.uc.edit.crv;

import android.content.Context;

import junit.framework.TestCase;

import java.util.List;

import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller.CrvController;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.MockClient;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.MockVisit;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Report;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;

/**
 * Created by Muthu on 07/02/2016.
 */
public class CrvControllerTest extends TestCase {

    CrvController controller;
    Client client;
    Contact contact;
    Visit visit;
    Context context;

    public void setUp() throws Exception {
        super.setUp();
        controller = new CrvController();
        client = new MockClient().getClients().get(0);
        contact = new Contact();
        contact.setFirstName("Muthu");
        contact.setLastName("Bala");
        contact.setPhoneNumber("0778801708");
        visit = new MockVisit().getMockVisit().get(0);
        context = null;

    }

    public void testGetAllReportForClient() throws Exception {

        List<Report> reports = controller.getAllReportForClient(Integer.toString(client.getClientId()), client, contact, visit, context);

       // assertEquals(reports.size(),0);
    }

    public void testDeleteReport() throws Exception {

    }
}