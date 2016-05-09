package fr.pds.isintheair.crmtab.model.rest.service;


import java.util.List;

import fr.pds.isintheair.crmtab.model.entity.Prospect;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Truong on 5/5/2016.
 */
public interface SyncRetrofitAPI {
    static final String BASE_URL = "http://192.168.20.3:8081/api/";

    @GET("suggestion/prospects")
    Call<List<Prospect>> getProspects();

}
