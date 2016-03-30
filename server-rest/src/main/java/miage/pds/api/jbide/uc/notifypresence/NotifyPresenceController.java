package miage.pds.api.jbide.uc.notifypresence;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.common.model.ClockinObject;
import miage.pds.api.jbide.uc.notifypresence.dao.TagDAO;
import miage.pds.api.jbide.uc.notifypresence.model.Tag;
import miage.pds.api.jbide.uc.registercall.model.Cra;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
	import org.springframework.web.servlet.ModelAndView;


	/**
	 * Handles requests for the application home page.
	 */
	@Controller
	public class NotifyPresenceController {

		boolean status = false;  
		private static final Logger logger = LoggerFactory.getLogger(NotifyPresenceController.class);
		TagDAO TagDao = new TagDAO(MongoDatastoreConfig.getDataStore());

		private Map<String, Tag> tag = new HashMap<String, Tag>();
		
		public NotifyPresenceController() {
		}

		@RequestMapping(value="/notifypresence/clockin", method=RequestMethod.POST)
		@ResponseBody
		public Boolean clock_in(@RequestBody ClockinObject clockin) {

			TagDao.updateLocation(ClockinObject);
				logger.info("CLOCKIN");
				return true;

		}
	

	}

