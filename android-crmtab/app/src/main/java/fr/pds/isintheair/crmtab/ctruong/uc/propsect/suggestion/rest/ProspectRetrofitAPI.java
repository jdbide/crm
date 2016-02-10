package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.rest;

import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.domain.Prospect;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Truong on 2/10/2016.
 */
public interface ProspectRetrofitAPI {

    @GET("prospect")
    Call<Prospect> getProspect();
}
