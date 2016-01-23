package miage.pds.api.common;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import miage.pds.api.common.model.User;
import miage.pds.api.registercall.RestController;
import miage.pds.api.registercall.dao.DAO;
import miage.pds.registercallmodel.Cra;

/**
 * Created by jbide on 19/01/2016.
 */
@Controller
public class LoggingRestController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	DAO dao = new DAO();

	public LoggingRestController() {
		User u = new User();
		u.setEmail("datour@crm.fr");
		u.setFname("Julien");
		u.setLname("Datour");
		u.setId("");
		u.setPassword("password");
		u.setTel("");
		
		dao.addUser(u);
		
		u.setEmail("bide@crm.fr");
		u.setFname("Jean-Daniel");
		u.setLname("BIDE");
		u.setId("0762506058");
		u.setPassword("password");
		u.setTel("");
		
		dao.addUser(u);
		
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody User getUser(@RequestBody User user) {
		User result = null;
		String data = null;
		try {
			data = new String(Base64.getDecoder().decode(user.getPassword()),"UTF-8");
			logger.info("Server : Data received for login : " + data);
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
