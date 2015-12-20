package pds.isintheair.fr.crm_tab.crv.test;

import junit.framework.TestCase;

import org.junit.Test;

import pds.isintheair.fr.crm_tab.crv.mock.MockClient;
import pds.isintheair.fr.crm_tab.crv.model.Client;

/**
 * Created by Muthu on 18/12/2015.
 */
public class CrvTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testMockClient(){
        MockClient mc = new MockClient();
        int length = mc.getClients().size();

        assertEquals("mocked 5 ojects", 5, length);

        //test is atleast on client exists and value types are correct

        Client cl = mc.getClients().get(0);
        int id = cl.getClientId();
        String surname = cl.getClientSurname();
        String name = cl.getClientName();
        String address = cl.getClientAddress();

        assertEquals("id must be 1", 1, id);
        assertEquals("surname must be CLIENT", "CLIENT", surname);
        assertEquals("name must be 1", "1", name);
        assertEquals("address must be 1 Rue de paris 75016", "1 Rue de paris 75016", address);

    }
}
