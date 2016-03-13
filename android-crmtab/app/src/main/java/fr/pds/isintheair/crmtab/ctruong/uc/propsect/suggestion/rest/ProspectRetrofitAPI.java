package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.rest;

import java.util.List;

import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.domain.Prospect;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Truong on 2/10/2016.
 */
public interface ProspectRetrofitAPI {

    @GET("suggestion/prospect/demo")
    Call<Prospect> getProspect();

    @POST("suggestion/prospect/{siret}")
    Call<Prospect> createClient(@Path("siret") long siret);

    @GET("suggestion/prospects")
    Call<List<Prospect>> getProspects();
}
