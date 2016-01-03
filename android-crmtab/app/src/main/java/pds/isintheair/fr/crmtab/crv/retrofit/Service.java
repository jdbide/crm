package pds.isintheair.fr.crmtab.crv.retrofit;

import pds.isintheair.fr.crmtab.crv.model.Reporting;
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
