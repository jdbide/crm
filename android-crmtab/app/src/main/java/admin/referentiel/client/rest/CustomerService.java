package admin.referentiel.client.rest;

import admin.referentiel.client.create.he.entities.HealthEtablishment;
import admin.referentiel.client.create.he.entities.HealthEtablishmentResponse;
import admin.referentiel.client.create.he.message.MessageRest;
import admin.referentiel.client.create.he.message.ResponseRest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by tlacouque on 13/12/2015.
 */
public interface CustomerService {

    String BASE_URL = "http://192.168.43.174:8080";
   // static String BEGIN_URL = "/SpringRESTapi";

    //@Header("Authorization")
    //,@Body HealthEtablishment healthEtablishment
    @POST("/customer/hc/create/")
    Call<ResponseRest> createHealthEtablishment(@Body MessageRest hc);

   /** @GET("/SpringRESTapi/users")
    Call<ArrayList<User>> getUsers();

    @POST("/SpringRESTapi/user/updateUser/{id}/{lastName}/{firstName}")
    Call<CreateOrUpdateResponse> updateUser(@Path("id") Integer id, @Path("lastName") String lastName, @Path("firstName") String firstName);

    @GET("/SpringRESTapi/user/deleteUser/{id}")
    Call<CreateOrUpdateResponse> deleteUser(@Path("id") Integer id);
    */
}
