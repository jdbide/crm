package miage.pds.api.ctruong.uc.prospect.suggest.rest;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.ProspectController;
import miage.pds.api.ctruong.uc.prospect.suggest.mock.MockTable;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Truong on 1/23/2016.
 */
@Controller
public class ProspectRestController {
    private static final Logger log = LoggerFactory.getLogger(ProspectRestController.class);

    public ProspectRestController() {

    }

    @RequestMapping(value = "/suggestion", method = RequestMethod.GET)
    public
    @ResponseBody
    String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(value = "/suggestion/prospects", method = RequestMethod.GET)
    public
    @ResponseBody
    HashMap<User, ArrayList<Prospect>> getAnalyze() {
//        MockTable mockTable = new MockTable();
//        mockTable.mockClientTable();
//        mockTable.mockUserTable();
//        mockTable.mockRelationAndSalesTable();
        ProspectController controller = new ProspectController();
        return controller.analyseProspect();
    }

    @RequestMapping(value = "/suggestion/mock", method = RequestMethod.GET)
    public
    @ResponseBody
    String mocking() {
        MockTable mockTable = new MockTable();
        mockTable.mockClientTable();
        mockTable.mockUserTable();
        mockTable.mockRelationAndSalesTable();
        return "Done";
    }
}
