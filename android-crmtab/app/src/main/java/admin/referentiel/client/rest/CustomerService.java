package admin.referentiel.client.rest;

import admin.referentiel.client.create.he.entities.HealthEtablishment;
import admin.referentiel.client.create.he.entities.HealthEtablishmentResponse;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by tlacouque on 13/12/2015.
 */
public interface CustomerService {

    String BASE_URL = "http://192.168.43.106:8080";
   // static String BEGIN_URL = "/SpringRESTapi";


    @POST("/customer/hc/create/")
    Call<String> createHealthEtablishment(@Header("Authorization") int idUser,@Body HealthEtablishment healthEtablishment);

   /** @GET("/SpringRESTapi/users")
    Call<ArrayList<User>> getUsers();

    @POST("/SpringRESTapi/user/updateUser/{id}/{lastName}/{firstName}")
    Call<CreateOrUpdateResponse> updateUser(@Path("id") Integer id, @Path("lastName") String lastName, @Path("firstName") String firstName);

    @GET("/SpringRESTapi/user/deleteUser/{id}")
    Call<CreateOrUpdateResponse> deleteUser(@Path("id") Integer id);
    */
}
