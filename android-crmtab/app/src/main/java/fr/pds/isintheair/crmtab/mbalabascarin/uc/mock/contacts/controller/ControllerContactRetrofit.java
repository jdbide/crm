package fr.pds.isintheair.crmtab.mbalabascarin.uc.mock.contacts.controller;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.constant.Constant;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.mock.contacts.model.Contact;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.mock.contacts.retrofit.ContactRetrofitService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Muthu on 02/03/2016.
 */
public class ControllerContactRetrofit {

    public Boolean addContacts(List<Contact> contacts, final Context context){

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //retrofit.client().setConnectTimeout(5000, TimeUnit.MILLISECONDS);
        ContactRetrofitService iContactRetrofitService = retrofit.create(ContactRetrofitService.class);
        Call<Boolean> call = iContactRetrofitService.addContacts(contacts);
        call.enqueue(new Callback<Boolean>() {

            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                Toast.makeText(context, "Les contacts ont bien été importé :)", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return false;
    }
}
