package miage.pds.api.ctruong.uc.prospect.suggest.rest;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.ProspectController;
import miage.pds.api.ctruong.uc.prospect.suggest.controller.SalesDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.controller.UserClientRelationDAOImpl;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.SalesDAO;
import miage.pds.api.ctruong.uc.prospect.suggest.dao.UserClientRelationDAO;
import miage.pds.api.ctruong.uc.prospect.suggest.mock.MockTable;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import miage.pds.api.ctruong.uc.prospect.suggest.model.User;
import miage.pds.api.ctruong.uc.prospect.suggest.model.UserClientRelation;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Truong on 1/23/2016.
 *
 * @version 1.1.19
 * @since 2016-01-24
 */
@Controller
public class ProspectRestController {
    private static final Logger log = LoggerFactory.getLogger(ProspectRestController.class);
    ProspectController controller;

    private MongoService mongoService;
    private UserClientRelationDAOImpl userClientRelationDAO;
    private SalesDAOImpl salesDAO;


    /**
     * Constructor for rest controller
     */
    public ProspectRestController() {
        this.controller = new ProspectController();
        this.mongoService = new MongoService();
        userClientRelationDAO = new UserClientRelationDAOImpl(UserClientRelation.class, mongoService.getDatastore());
        salesDAO = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
    }

    /**
     * Test Spring
     *
     * @return Hello World
     */
    @RequestMapping(value = "/suggestion", method = RequestMethod.GET)
    public
    @ResponseBody
    String helloWorld() {
        return "Hello Davide's World";
    }


}
