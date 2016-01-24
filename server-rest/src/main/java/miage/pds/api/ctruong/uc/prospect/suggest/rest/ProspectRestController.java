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
 */
@Controller
public class ProspectRestController {
    private static final Logger log = LoggerFactory.getLogger(ProspectRestController.class);
    ProspectController controller = new ProspectController();

    private MongoService mongoService;
    private UserClientRelationDAO userClientRelationDAO;
    private SalesDAO salesDAO;


    public ProspectRestController() {
        this.mongoService     = new MongoService();
        userClientRelationDAO = new UserClientRelationDAOImpl(UserClientRelation.class, mongoService.getDatastore());
        salesDAO              = new SalesDAOImpl(Sales.class, mongoService.getDatastore());
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
        HashMap<User, ArrayList<Prospect>> map = controller.analyseProspect();
//        MockTable mockTable = new MockTable();
//        mockTable.mockClientTable();
//        mockTable.mockUserTable();
//        mockTable.mockRelationAndSalesTable();
        return map;
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

    @RequestMapping(value = "/suggestion/demo", method = RequestMethod.GET)
    public @ResponseBody String demo(){
        HashMap<User, ArrayList<Prospect>> map = controller.analyseProspect();
        Iterator<Map.Entry<User, ArrayList<Prospect>>> iterator = map.entrySet().iterator();
        String demo = "This is an example of analyze: \r\n";
        // The date 6 month before
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date date = calendar.getTime();

        while (iterator.hasNext()){
            Map.Entry<User, ArrayList<Prospect>> entry = iterator.next();
            User user = entry.getKey();
            ArrayList<Prospect> prospects = entry.getValue();

            if (user.getId() == 1){
                if (prospects.size() == 1){
                    demo += "User: " + user.getLogin() + "\r\n <br>";
                    demo += "The sales average global is " + controller.getSalesAverage() + "\r\n <br>";
                    for (Prospect prospect: prospects){
                        demo += "The prospect: " + prospect.getName() + "\r\n <br>" ;
                        List<Sales> sales = salesDAO.getSalesByIDClient(prospect.getId());
                        Iterator<Sales> salesIterator = sales.iterator();
                        double salesTotal = 0.0d;
                        double salesAveByPros = 0.0d;
                        while (salesIterator.hasNext()){
                            Sales sale = salesIterator.next();

                            if (sale.getDate().before(date)){

                                salesIterator.remove();
                            } else {
                                salesTotal = salesTotal + sale.getValue();
                            }
                            salesAveByPros = salesTotal /sales.size();
                        }

                        demo += "The sales value: " + salesAveByPros + "<br>";
                        demo += "The relation level of this prospect: " + userClientRelationDAO.countRelationshipByClientID(prospect.getId()) + "<br>";
                        demo += "The size of this prospect: " + prospect.getPlace() + "<br>";
                        demo += "=====================> Notify to user this prospect ";
                        log.info("User: " + user.getLogin() + " will receive a notification which suggest a prospect: " + prospect.getName());
                    }
                }
            }
        }

        return demo;
    }
}
