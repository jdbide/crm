package miage.pds.api.crv;



import miage.pds.api.RestController;
import miage.pds.api.crv.dao.CrvMorphiaDao;
import miage.pds.api.crv.model.Reporting;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrvController {

	boolean status = false;  
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	CrvMorphiaDao dao = new CrvMorphiaDao();
	public CrvController() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Simply returns a status string.
	 */
	@RequestMapping(value = "/crv", method = RequestMethod.GET)
	public @ResponseBody String home() {

		//logger.info("REST SERVER IS RUNNING :)");
		return "TEST PAGE :)";
	}
	@RequestMapping(value = "/crv/addCrv", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Boolean createCrv(@RequestBody Reporting crv) {
		logger.info("Start create Reporting.");
		return dao.createCrv(crv.getReport());
		

	}
	
	
}
