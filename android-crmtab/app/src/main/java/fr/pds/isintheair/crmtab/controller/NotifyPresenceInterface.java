package fr.pds.isintheair.crmtab.controller;

import fr.pds.isintheair.crmtab.model.ClockinObject;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by jbide on 29/03/2016.
 */
public interface NotifyPresenceInterface {

    @POST("notifypresence/clockin")
    Call<ClockinObject> clockin(@Body ClockinObject clockinObject);

}
