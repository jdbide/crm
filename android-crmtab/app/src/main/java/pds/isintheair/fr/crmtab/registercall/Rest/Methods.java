package pds.isintheair.fr.crmtab.registercall.Rest;

import java.util.List;

import fr.pds.isintheair.crmtab.registercall.Rest.Model.Cra;
import pds.isintheair.fr.crmtab.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by j-d on 22/12/2015.
 */
public interface Methods {


    @GET("cra/listcra")
    Call<List<Cra>> listcraforuser(@Query("iduser") int iduser);


    @POST("cra/create")
    Call<Boolean> createcra(@Body Cra cra);


    @POST("login")
    Call<User> basicLogin(@Body User user);


}
