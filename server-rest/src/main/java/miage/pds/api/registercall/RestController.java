package miage.pds.api.registercall;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import miage.pds.api.registercall.dao.DAO;
import miage.pds.registercallmodel.Cra;


/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {

	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	DAO dao = new DAO();

	public RestController() {
	}
	
	/**
	 * Simply returns a status string.
	 */

	@RequestMapping(value = "/cra/listcra",method = RequestMethod.GET)
	public @ResponseBody List<Cra> getListCraForUser(@RequestParam("iduser") int iduser){

		List<Cra> liste = dao.getListCraForUser(iduser);
		System.out.println("response list size :" + liste.size());
		return liste;
	}

	@RequestMapping(value = "/cra/create", method = RequestMethod.POST)
	public @ResponseBody Boolean createCra(@RequestBody Cra cra) {
		boolean status = false;  
		status = dao.createCra(cra);
		if(status) logger.info("Cra registered :)");
		else logger.info("Cra not registered :)");
		return status;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Boolean createCra() {
		boolean status = false;  
		//status = dao.createCra(cra);
		//if(status) logger.info("Cra registered :)");
		//else logger.info("Cra not registered :)");
		return status;
	}
	
}
