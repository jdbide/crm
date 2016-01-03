package pds.isintheair.fr.crmtab.registercall.Rest;

import java.util.List;

import pds.isintheair.fr.crmtab.registercall.Rest.Model.Cra;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by j-d on 22/12/2015.
 */
public interface ServiceGenerator {


    @GET("listcra")
    Call<List<Cra>> listcraforuser(@Query("iduser") int iduser) ;


    @POST("createcra")
    Call<Boolean> createcra(@Body Cra cra) ;






}
