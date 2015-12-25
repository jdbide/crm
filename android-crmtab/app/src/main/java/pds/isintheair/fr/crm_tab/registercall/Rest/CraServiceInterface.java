package pds.isintheair.fr.crm_tab.registercall.Rest;

import java.util.List;

import pds.isintheair.fr.crm_tab.registercall.Rest.Model.Cra;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by j-d on 22/12/2015.
 */
public interface CraServiceInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("listcra")
    Call<List<Cra>> listcraforuser(@Query("iduser") String iduser) ;
    @POST("createcra")
    Call<Boolean> createcra(@Body Cra cra) ;





}
