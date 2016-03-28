package fr.pds.isintheair.crmtab.model.rest.service;

import fr.pds.isintheair.crmtab.Constant;
import fr.pds.isintheair.crmtab.model.entity.MessageRestPhoningCampaign;
import fr.pds.isintheair.crmtab.model.entity.ResponseRestPhoningCampaign;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by tlacouque on 27/03/2016.
 * Interface used to contact rest service for phoning campaign
 */
public interface PhoningCampaignService {

    static String BASE_URL = Constant.REST_URL;

    // static String BEGIN_URL = "/SpringRESTapi";


    @POST("/api/phoningcampaign/contact")
    Call<ResponseRestPhoningCampaign> getContacts(@Body MessageRestPhoningCampaign message);
}
