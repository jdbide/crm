package api.admin.customer.crud.createindep;

import com.mongodb.MongoClient;
import miage.pds.api.admin.customer.crud.createhc.dao.HealthCenterDAO;
import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.api.admin.customer.crud.createindep.dao.IndependantDAO;
import miage.pds.api.admin.customer.crud.createindep.entities.Independant;
import miage.pds.prospect.model.Prospect;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by tlacouque on 17/01/2016.
 */

public class IndependantDAOTest {
    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    IndependantDAO independantDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();
        this.morphia        = new Morphia();
        this.morphia.map(Prospect.class);
        this.datastore      = this.morphia.createDatastore(mongoClient,dbName);
        independantDAO = new IndependantDAO(datastore);
    }

    @Test
    public void testfindAllWithoutUserId() throws Exception {
        List<Independant> independants = independantDAO.findAllWithoutUserId(1);
        assertNotNull(independants);
    }
}
