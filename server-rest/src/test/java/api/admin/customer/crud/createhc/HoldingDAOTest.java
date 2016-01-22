package api.admin.customer.crud.createhc;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.admin.customer.crud.createhc.dao.HoldingDAO;
import miage.pds.api.admin.customer.crud.createhc.entities.Holding;
import miage.pds.api.admin.customer.crud.createindep.dao.IndependantDAO;
import miage.pds.prospect.model.Prospect;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 17/01/2016.
 */
public class HoldingDAOTest {
    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    HoldingDAO holdingDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();

        holdingDAO = new HoldingDAO(MongoDatastoreConfig.getDataStore());

    }

    @Test
    public void testFindAll() throws Exception {
        List<Holding> holdingList = holdingDAO.findAll();
        assertEquals(9,holdingList.size());
    }
}
