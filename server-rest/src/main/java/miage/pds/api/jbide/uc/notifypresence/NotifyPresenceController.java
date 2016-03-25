package miage.pds.api.jbide.uc.notifypresence;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.jbide.uc.notifypresence.dao.TagDAO;
import miage.pds.api.jbide.uc.notifypresence.model.Tag;
	import java.text.DateFormat;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.Locale;
	import java.util.Map;

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


	/**
	 * Handles requests for the application home page.
	 */
	@Controller
	public class NotifyPresenceController {

		boolean status = false;  
		private static final Logger logger = LoggerFactory.getLogger(NotifyPresenceController.class);
		TagDAO db = new TagDAO(MongoDatastoreConfig.getDataStore());

		private Map<String, Tag> tag = new HashMap<String, Tag>();
		
		public NotifyPresenceController() {
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

		@RequestMapping(value="/doBadge/{id}", method=RequestMethod.GET)
		@ResponseBody
		public Boolean checkTag(@PathVariable("id") String id) {

			status = db.DatabaseConnection(id);
			if (status) {

				logger.info("Inside checkTag, returned: " + id);
				return status;
			}

			else {

				logger.info("Inside checkTag, error: " + id + ", NOT FOUND!");
				return false;
			} 
		}
		
		@RequestMapping(value="/doChangeStatus/{id}", method=RequestMethod.GET)
		@ResponseBody
		public Boolean setStatus(@PathVariable("id") String id) {

			DAO db = new DAO();



			status = db.setStatus(id);
			if (status) {

				logger.info("Inside checkTag, returned: " + id);
				return status;
			}

			else {

				logger.info("Inside checkTag, error: " + id + ", NOT FOUND!");
				return false;
			} 
		}
		
		@RequestMapping(value="/checkStatus/{id}", method=RequestMethod.GET)
		@ResponseBody
		public Boolean checkStatus(@PathVariable("id") String id) {

			DAO db = new DAO();



			status = db.checkStatus(id);
			if (status) {

				logger.info("Inside checkTag, returned: " + id);
				return status;
			}

			else {

				logger.info("Inside checkTag, error: " + id + ", NOT FOUND!");
				return false;
			} 
		}




	}

