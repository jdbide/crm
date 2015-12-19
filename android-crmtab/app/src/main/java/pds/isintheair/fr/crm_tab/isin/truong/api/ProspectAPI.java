package pds.isintheair.fr.crm_tab.isin.truong.api;


import java.util.List;

import pds.isintheair.fr.crm_tab.isin.truong.model.entity.CreateResponse;
import pds.isintheair.fr.crm_tab.isin.truong.model.entity.Prospect;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Truong on 12/14/2015.
 */
public interface ProspectAPI {
    String BASE_URL = "http://192.168.20.3:8082";

    @GET("/crm/{login}/prospects")
    Call<List<Prospect>> getProspects(@Path("id") String login);

    @POST("/crm/{login}/prospect/addClient/{id}/{name}")
    Call<CreateResponse> createClient(@Path("login") String login, @Path("id") int id, @Path("name") String name);

}
