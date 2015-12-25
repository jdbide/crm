package miage.pds.api;



import java.util.List;

import miage.pds.api.registercall.dao.DAO;
import miage.pds.registercallmodel.Cra;
import miage.pds.registercallmodel.ListCra;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
	
	@RequestMapping(value = "/listcra",method = RequestMethod.GET)
	public @ResponseBody List<Cra> getListCraForUser(@RequestParam("iduser") String iduser){
		//boolean status = false;  
		logger.info("Searching licra for : "+iduser);
		//JSONObject status = new JSONObject();
		//boolean reqstatus = true;
		List<Cra> list = (List<Cra>) dao.getListCraForUser(iduser);
		logger.info("first cra : "+ list.get(0).getContactname());
		return list;
		/*try {
			status.put("status", reqstatus);	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//dao.getAllCra();
		//for(Cra c :dao.getAllCra())
		//logger.info(c.getIdcontact());
		//return status.toString();	
		//return "ok";
	}

	@RequestMapping(value = "/createcra", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Boolean createCra(@RequestBody Cra cra) {
		boolean status = false;  
		status = dao.createCra(cra);
		//return status.toString();
		if(status) logger.info("Cra registered :)");
		return status;
		//return false;
	}
	
}
