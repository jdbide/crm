package miage.pds.api;



import java.util.Locale;

import miage.pds.api.model.Cra;
import miage.pds.api.model.CreateCraResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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
	public String home(Locale locale, Model model) {

		logger.info("REST SERVER IS RUNNING :)");

		return "REST SERVER IS RUNNING :)";
	}
	//logger.info("");
	@RequestMapping(value = "/createcra", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody CreateCraResponse createCra(@RequestBody Cra cra) {
		
		CreateCraResponse response = new CreateCraResponse();
		//boolean status = true;
		response.setStatus(status);
        return response;
	}

	


}
