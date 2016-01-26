package miage.pds.api.common;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.common.model.User;
import miage.pds.api.jbide.uc.registercall.CraRestController;
import miage.pds.api.jbide.uc.registercall.dao.CraDAO;
import miage.pds.api.jbide.uc.registercall.model.Cra;

/**
 * Created by jbide on 19/01/2016.
 */
@Controller
public class LoggingRestController {
	private static final Logger logger = LoggerFactory.getLogger(CraRestController.class);
	
	CraDAO dao = new CraDAO(MongoDatastoreConfig.getDataStore());
	
	public static String idusertest ;

	public LoggingRestController() {
		
		idusertest = "bd299fa2-244c-4b6b-9966-49a84192cc8c";

		User u = new User();
		/*u.setEmail("datour@crm.fr");
		u.setFname("Julien");
		u.setLname("DATOUR");
		u.setPassword("password");
		u.setTel("");
		u.setId(dao.getUniqueUid());
		dao.addUser(u);
		
		u.setEmail("bide@crm.fr");
		u.setFname("Jean-Daniel");
		u.setLname("BIDE");
		u.setId(dao.getUniqueUid());
		u.setPassword("password");
		u.setTel("0762506058");
		dao.addUser(u);*/
		
		u.setEmail("test@crm.fr");
		u.setFname("testFname");
		u.setLname("testLname");
		u.setId(idusertest);
		u.setPassword("password");
		u.setTel("0762506058");
		dao.addUser(u);
		
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody User getUser(@RequestBody User user) {
		User result = null;
		String data = null;
		try {
			data = new String(Base64.getDecoder().decode(user.getPassword()),"UTF-8");
			logger.info("Server : Encrypted msg for login : " + user.getPassword());
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(data!=null){
			logger.info("server: mail after decode  "+ data.substring(0,data.indexOf(":")));
			logger.info("server: password after decode " + data.substring(data.indexOf(":")+1));
			user.setEmail(data.substring(0,data.indexOf(":")));
			user.setPassword(data.substring(data.indexOf(":")+1));
			result = dao.logUser(user);
		}

		if(result!=null) logger.info("User found :)");
		else logger.info("User not found :)");
		return result;
	}
	

}
