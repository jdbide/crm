package api.admin.customer.crud.createhc;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.admin.customer.crud.createhc.dao.HealthCenterDAO;
import miage.pds.api.admin.customer.crud.createhc.entities.HealthCenter;
import miage.pds.prospect.controller.ProspectDAOImpl;
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
public class HealthCenterDAOTest {

    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    HealthCenterDAO healthCenterDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {

        healthCenterDAO         = new HealthCenterDAO(MongoDatastoreConfig.getDataStore());
    }

    @Test
    public void testfindAllWithoutUserId() throws Exception {
        List<HealthCenter> healthCenters = healthCenterDAO.findAllWithoutUserId(1);
        assertNotNull(healthCenters);
    }
}
