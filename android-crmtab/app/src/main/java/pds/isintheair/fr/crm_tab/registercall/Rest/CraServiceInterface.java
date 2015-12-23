package pds.isintheair.fr.crm_tab.registercall.Rest;

import pds.isintheair.fr.crm_tab.registercall.Rest.Model.Cra;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by j-d on 22/12/2015.
 */
public interface CraServiceInterface {

    @POST("/createcra")
    Call<String> createcra(@Body Cra cra) ;



}
