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

import miage.pds.api.crv.dao.CrvMorphiaDao;
import miage.pds.api.crv.model.Reporting;


/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {

	boolean status = false;  
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	

	public RestController() {


	}

	/**
	 * Simply returns a status string.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String home() {

		logger.info("REST SERVER IS RUNNING :)");
		return "REST SERVER IS RUNNING :)";
	}

}
