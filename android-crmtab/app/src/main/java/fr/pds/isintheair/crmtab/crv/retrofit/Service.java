package fr.pds.isintheair.crmtab.crv.retrofit;

import fr.pds.isintheair.crmtab.crv.model.Reporting;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Muthu on 30/12/2015.
 */
public interface Service {
    @POST("addCrv")
    Call<Boolean> createReport(@Body Reporting reporting);

}
