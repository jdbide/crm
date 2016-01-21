package fr.pds.isintheair.crmtab.crv.retrofit;


import java.util.List;

import fr.pds.isintheair.crmtab.crv.model.Report;
import fr.pds.isintheair.crmtab.crv.model.Reporting;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Muthu on 30/12/2015.
 */
public interface Service {
    @POST("addCrv")
    Call<Boolean> createReport(@Body Reporting reporting);

    @GET("getCrv/{client}")
    Call<List<Report>> getReportList(@Path("client") String client);

    @GET("deleteCrv/{idCrv}")
    Call<Boolean> deleteReport(@Path("idCrv") String idReport);

}
