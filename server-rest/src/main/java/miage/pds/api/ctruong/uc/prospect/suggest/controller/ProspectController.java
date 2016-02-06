package miage.pds.api.ctruong.uc.prospect.suggest.controller;

import miage.pds.api.ctruong.uc.prospect.suggest.dao.ProspectDAO;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.ProspectDAOImp;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This is a class which control the algorithm to find the prospect for each user.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class ProspectController {

    private MongoService            mongoService;
    private static Logger logger = LoggerFactory.getLogger(ProspectController.class);
    private ProspectDAO prospectDAO;


    /**
     * Constructor allows to create new instance of Controller
     */
    public ProspectController() {
        this.mongoService           = new MongoService();
        this.prospectDAO = new ProspectDAOImp(Prospect.class, mongoService.getDatastore());
    }

    public List<Prospect> getListProspectByTurnover(){
        List<Prospect> prospects = prospectDAO.getListProspect();

        return prospects;
    }

}
