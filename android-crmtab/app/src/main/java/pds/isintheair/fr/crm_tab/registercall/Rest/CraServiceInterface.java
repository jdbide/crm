package pds.isintheair.fr.crm_tab.registercall.Rest;

import pds.isintheair.fr.crm_tab.registercall.Rest.Model.Cra;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by j-d on 22/12/2015.
 */
public interface CraServiceInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("test")
    Call<String> test() ;
    @POST("createcra")
    Call<Boolean> createcra(@Body Cra cra) ;



}
