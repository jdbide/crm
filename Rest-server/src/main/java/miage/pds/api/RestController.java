package miage.pds.api;



import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import miage.pds.model.Reporting;


/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {

	boolean status = false;  
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	DAO dao = new DAO();

	public RestController() {


	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String home() {

		logger.info("REST SERVER IS RUNNING :)");
		return "REST SERVER IS RUNNING :)";
	}

	/*@RequestMapping(value="/crv/addCrv/", method=RequestMethod.POST ,headers="Accept=application/json")
	@ResponseBody
	public String addUser() {
		DAO dao = new DAO();



		return "";
	}*/


	@RequestMapping(value = "/crv/addCrv", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String createCrv(@RequestBody Reporting crv) {
		logger.info("Start create Reporting.");
		JSONObject status = new JSONObject();
		boolean createStatus = dao.createCrv(crv);
		try {
			
			status.put("status", createStatus);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status.toString();

	}
	@RequestMapping(value = "/crv/getRandomInfo", method = RequestMethod.GET)
	public @ResponseBody String getRandomInfo() {
		logger.info("Start create Reporting.");
		int randomId = randInt(1, 5);
		String mock = dao.getRandomInfo(randomId);
		return mock;

	}
	
	//get random int from 1 to 5
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}


}
