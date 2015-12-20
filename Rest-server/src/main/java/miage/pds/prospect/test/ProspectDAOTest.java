package miage.pds.prospect.test;

import miage.pds.prospect.dao.ProspectDAO;
import miage.pds.prospect.model.Prospect;
import miage.pds.prospect.test.data.TestData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.Assert.*;

/**
 * Created by Truong on 12/20/2015.
 */

public class ProspectDAOTest {

    @Autowired private ProspectDAO prospectDAO;
    @Autowired private MongoTemplate mongoTemplate;


    @Test
    public void reset(){
        TestData.prospect();
    }


    @Test
    public void testFindAllProspect() throws Exception {
        prospectDAO.findAllProspect();
        assertEquals("True", true, mongoTemplate.findAll(Prospect.class));
    }

    @Test
    public void testCreateCollection() throws Exception {
        prospectDAO.createCollection();
        assertEquals("True", true, mongoTemplate.createCollection(Prospect.class));
    }

    @Test
    public void testDropCollection() throws Exception {

    }

    @Test
    public void testInsertProspectWithRandomPlace() throws Exception {

    }
}
