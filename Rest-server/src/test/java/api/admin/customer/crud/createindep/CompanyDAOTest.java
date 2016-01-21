package api.admin.customer.crud.createindep;

import com.mongodb.MongoClient;
import miage.pds.api.admin.customer.crud.createhc.dao.PurchasingCentralDAO;
import miage.pds.api.admin.customer.crud.createhc.entities.PurchasingCentral;
import miage.pds.api.admin.customer.crud.createindep.dao.CompanyDAO;
import miage.pds.api.admin.customer.crud.createindep.entities.Company;
import miage.pds.prospect.model.Prospect;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by tlacouque on 17/01/2016.
 */
public class CompanyDAOTest {

    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    CompanyDAO companyDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();
        this.morphia        = new Morphia();
        this.morphia.map(Prospect.class);
        this.datastore      = this.morphia.createDatastore(mongoClient,dbName);
        companyDAO = new CompanyDAO(datastore);

    }

    @Test
    public void testFindAll() throws Exception {
        List<Company> companies = companyDAO.findAll();
        assertEquals(34,companies.size());
        assertThat(35,not(companies.size()));
    }
}
