package miage.pds.api.jbide.uc.notifypresence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.common.model.ClockinObject;
import miage.pds.api.jbide.uc.notifypresence.dao.TagDAO;
import miage.pds.api.jbide.uc.notifypresence.model.Tag;


	@Controller
	public class NotifyPresenceController {

		boolean status = false;  
		private static final Logger logger = LoggerFactory.getLogger(NotifyPresenceController.class);
		TagDAO TagDao = new TagDAO(MongoDatastoreConfig.getDataStore());
		
		public NotifyPresenceController() {
		}

		@RequestMapping(value="/notifypresence/clockin", method=RequestMethod.POST)
		@ResponseBody
		public Boolean clock_in(@RequestBody ClockinObject clockin) {
			
			logger.info("CLOCKIN");
			
			Tag tag = TagDao.checkTag(clockin.getTagId());
			if(tag!=null)
			{
			TagDao.updateLocation(clockin.getUser(),tag);

			return true;
			}
			else return false;

		}
		
		@RequestMapping(value="/notifypresence/addnewtag", method=RequestMethod.POST)
		@ResponseBody
		public Boolean addNewTag(@RequestBody Tag tag) {
			
			logger.info("ADD NEW TAG");
			TagDao.addTag(tag);
			return true;
		}
	

	}

