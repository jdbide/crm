package fr.pds.isintheair.crmtab.client.rest;

import fr.pds.isintheair.crmtab.client.message.MessageRestCustomer;
import fr.pds.isintheair.crmtab.client.message.ResponseRestCustomer;
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

   static String BASE_URL = "http://192.168.20.3:8070";
   // static String BEGIN_URL = "/SpringRESTapi";


    @POST("/api/customer/hc/create/")
    Call<ResponseRestCustomer> createHealthCenter(@Body MessageRestCustomer hc);

    @POST("/api/customer/indep/create/")
    Call<ResponseRestCustomer> createIndependant(@Body MessageRestCustomer hc);

    @GET("/api/customer/holding")
    Call<ResponseRestCustomer> getHoldings();

    @GET("/api/customer/purchasingcentral")
    Call<ResponseRestCustomer> getPurchasingCentral();

   @GET("/api/customer/company")
    Call<ResponseRestCustomer> getCompanies();

    @GET("/api/customer/specialty")
    Call<ResponseRestCustomer> getSpecialties();

    @GET("/api/customer/healthcenter/{iduser}")
    Call<ResponseRestCustomer> getHealthCenters(@Path("iduser") int iduser);

    @GET("/api/customer/independant/{iduser}")
    Call<ResponseRestCustomer> getIndependants(@Path("iduser") int iduser);

    @GET("/api/customer/{iduser}")
    Call<ResponseRestCustomer> getCustomers(@Path("iduser") int iduser);

}
