package miage.pds.prospect.controller;

import miage.pds.prospect.dao.ProspectDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Truong on 12/20/2015.
 */


public class ProspectController {
    static final Logger logger = LoggerFactory.getLogger(ProspectController.class);

    public static void main(String[] args) {
        logger.info("Bootstrapping MongoDemo application");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webapp/WEB-INF/spring/AppServlet/servlet-context.xml");
        ProspectDAO prospectDAO = context.getBean(ProspectDAO.class);

        prospectDAO.dropCollection();
        prospectDAO.createCollection();

        for (int i = 0; i< 20; i++){
            prospectDAO.insertProspectWithRandomPlace();
        }

        prospectDAO.findAllProspect();
        logger.info("Finished MongoDB demo");
    }
}
