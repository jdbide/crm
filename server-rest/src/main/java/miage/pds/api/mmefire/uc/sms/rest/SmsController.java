package miage.pds.api.mmefire.uc.sms.rest;

import java.util.List;


import miage.pds.api.mmefire.uc.sms.dao.Message;
import miage.pds.api.mmefire.uc.sms.model.MessageDTO;
import miage.pds.api.mmefire.uc.sms.service.MessageService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


// fait le traitement � partir de l'url passe en parammetre
@Controller
@RequestMapping (value = "/sms")// defini l'url
public class SmsController {

	boolean status = false;  
	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);
	MessageService messageService = new MessageService();
	public SmsController() {
	}


	@RequestMapping(value = "/addSms", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Boolean addSms(@RequestBody MessageDTO messageDto) {
		logger.info("Add new message");
		return messageService.addMessage(messageDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Message> getMessagesForContact(@PathVariable String id) {
		logger.info("Start get Messages.");
		return messageService.getMessagesForContact(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Boolean deleteMessageById(@PathVariable String id) {
		logger.info("Start delete Message by id.");
		return messageService.deleteMessageById(id);
	}
	
	
}
