package miage.pds.api;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import miage.pds.model.Users;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {
	long startTime ;
	DAO dao = new DAO();

	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	private Map<String, Users> users = new HashMap<String, Users>();

	public RestController() {
		// pre-initialize the list of users available ...


	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {


		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "status";
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	@ResponseBody
	public String getAllUsers() {
		logger.info("Inside getAllUsers() method...");
		DAO dao = new DAO();
		dao.getUsers();

		return dao.getUsers().toString();
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getUserById(@PathVariable("id") int id) {
		DAO dao = new DAO();
		
		return dao.getUserById(id);
 
	}

	@RequestMapping(value="/user/deleteUser/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public String deleteUserById(@PathVariable("id") int id) {
		DAO dao = new DAO();
		String ret = null;
		try {
			Boolean status =  dao.deleteUserById(id);
			JSONObject obj = new JSONObject();
			JSONObject def = new JSONObject();
			def.put("status", new Boolean(false));
			ret = def.toString();
			
				obj.put("status", status);
				return obj.toString();

			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@RequestMapping(value="/user/create", method=RequestMethod.GET ,headers="Accept=application/json")
	public ModelAndView addUser() {

		return new ModelAndView("addUser", "command", new Users());
	}


	@RequestMapping(value="/user/addUser/{id}/{nom}/{prenom}", method=RequestMethod.POST ,headers="Accept=application/json")
	@ResponseBody
	public String addUser(@PathVariable("id") int id, @PathVariable("nom") String nom, @PathVariable("prenom") String prenom ) {
		DAO dao = new DAO();
		String ret ="";
		JSONObject obj = new JSONObject();
		JSONObject def = new JSONObject();
		try {
			def.put("status", "false");

			ret = def.toString();
			Boolean stat =  dao.createUser(id, nom, prenom);
			
				return obj.put("status", stat).toString();
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@RequestMapping(value="/user/updateUser/{id}/{nom}/{prenom}", method=RequestMethod.POST ,headers="Accept=application/json")
	@ResponseBody
	public String updateUser(@PathVariable("id") int id, @PathVariable("nom") String nom, @PathVariable("prenom") String prenom ) {
		DAO dao = new DAO();
		String ret ="";
		JSONObject obj = new JSONObject();
		JSONObject def = new JSONObject();
		try {
			def.put("status", "false");

			ret = def.toString();
			Boolean stat =  dao.updateUser(id, nom, prenom);
			
				return obj.put("status", stat).toString();
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
