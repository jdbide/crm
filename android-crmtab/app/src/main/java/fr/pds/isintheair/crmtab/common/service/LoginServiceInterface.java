package fr.pds.isintheair.crmtab.common.service;

import fr.pds.isintheair.crmtab.common.model.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by jbide on 22/01/2016.
 */
public interface LoginServiceInterface {

    @POST("login")
    Call<User> login(@Body User user) ;
}
