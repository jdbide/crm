package miage.pds.api.ctruong.uc.prospect.suggest.rest;

import miage.pds.api.ctruong.uc.prospect.suggest.controller.ProspectController;
import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

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


    /**
     * Constructor for rest controller
     */
    public ProspectRestController() {
        this.controller = new ProspectController();
        this.mongoService = new MongoService();
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

    @RequestMapping(value = "/suggestion/prospect", method = RequestMethod.GET)
    public Prospect prospectAnalyse(){
        List<Prospect> prospects = controller.analyseProspect();
        Iterator<Prospect> iterator = prospects.iterator();
        Prospect prospect = new Prospect();
        while (iterator.hasNext()){
            prospect = iterator.next();

        }
        return prospect;
    }


}
