package miage.pds.api.tlacouque.uc.phoning.campaign.controller;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.common.TileDownloader;
import miage.pds.api.tlacouque.uc.admin.ref.customer.common.XYZCalcul;
import miage.pds.api.tlacouque.uc.admin.ref.customer.controller.LocationController;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.HealthCenterDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;
import miage.pds.api.tlacouque.uc.admin.ref.customer.message.MessageRestCustomer;
import miage.pds.api.tlacouque.uc.admin.ref.customer.message.ResponseRestCustomer;
import miage.pds.api.tlacouque.uc.phoning.campaign.dto.MessageRestPhoningCampaign;
import miage.pds.api.tlacouque.uc.phoning.campaign.dto.ResponseRestPhoningCampaign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tlacouque on 27/03/2016.
 * Rest controller used to handle message from commercials tablet for phoning campaign part.
 */

@Controller
public class RestPhoningCampaignController {

    public RestPhoningCampaignController() {
    }

    /**
     * Take a messageRestCustomer (dto) in parameter with an healthcenter in it.
     * Save the healthcenter and return true with a ResponseRestCustomer (dto)
     * @param message
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/phoningcampaign/contact/{iduser}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseRestPhoningCampaign getContacts(@RequestBody MessageRestPhoningCampaign message) {


        return null;
    }
}
