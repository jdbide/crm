package fr.pds.isintheair.crmtab.mbalabascarin.uc.mock.contacts.retrofit;

import java.util.List;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.mock.contacts.model.Contact;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Muthu on 30/12/2015.
 */
public interface CrvRetrofitService {
    @POST("contact/addContacts")
    Call<Boolean> addContacts(@Body List<Contact> contacts);


    @GET("contact/getContacts")
    Call<List<Contact>> getContactList();

}
