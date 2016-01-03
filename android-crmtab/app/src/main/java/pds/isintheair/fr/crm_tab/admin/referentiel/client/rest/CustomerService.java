package pds.isintheair.fr.crm_tab.admin.referentiel.client.rest;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.MessageRestCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.ResponseRestCustomer;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by tlacouque on 13/12/2015.
 * Interface used to
 */
public interface CustomerService {

    String BASE_URL = "http://192.168.20.3:8082";
   // static String BEGIN_URL = "/SpringRESTapi";


    @POST("/customer/hc/create/")
    Call<ResponseRestCustomer> createHealthCenter(@Body MessageRestCustomer hc);

    @POST("/customer/indep/create/")
    Call<ResponseRestCustomer> createIndependant(@Body MessageRestCustomer hc);

    @GET("/customer/holding")
    Call<ResponseRestCustomer> getHoldings();

    @GET("/customer/purchasingcentral")
    Call<ResponseRestCustomer> getPurchasingCentral();

   @GET("/customer/company")
    Call<ResponseRestCustomer> getCompanies();

    @GET("/customer/specialty")
    Call<ResponseRestCustomer> getSpecialties();

    @GET("/customer/healthcenter/{iduser}")
    Call<ResponseRestCustomer> getHealthCenters(@Path("iduser") int iduser);

    @GET("/customer/independant/{iduser}")
    Call<ResponseRestCustomer> getIndependants(@Path("iduser") int iduser);

    @GET("/customer/{iduser}")
    Call<ResponseRestCustomer> getCustomers(@Path("iduser") int iduser);
/**
 @POST("/SpringRESTapi/user/updateUser/{id}/{lastName}/{firstName}")
    Call<CreateOrUpdateResponse> updateUser(@Path("id") Integer id, @Path("lastName") String lastName, @Path("firstName") String firstName);

    @GET("/SpringRESTapi/user/deleteUser/{id}")
    Call<CreateOrUpdateResponse> deleteUser(@Path("id") Integer id);
    */
}
