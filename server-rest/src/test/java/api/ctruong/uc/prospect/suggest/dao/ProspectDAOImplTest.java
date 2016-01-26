package api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.ProspectDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.ProspectDAO;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The unit test for the class prospect dao
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class ProspectDAOImplTest {
    private final static Logger log         = LoggerFactory.getLogger(ProspectDAOImplTest.class);
    private MongoService service;
    private Datastore           datastore;
    private ProspectDAO prospectDAO;


    @Before
    public void setUp() throws Exception {
        this.service        = new MongoService();
        this.datastore      = service.getDatastore();
        this.prospectDAO         = new ProspectDAOImpl(Prospect.class, datastore);

    }


    @Test
    public void testGetAllProspect() throws Exception {
        List<Prospect> prospects = prospectDAO.getAllProspect();
        assertEquals(prospects.size(), prospectDAO.getAllProspect().size());
    }

    @Test
    public void testGetProspectByID() throws Exception {
        Prospect prospect1  = prospectDAO.getProspectByID(1);
        assertNotNull(prospect1);
    }
}
