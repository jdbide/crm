package pds.isintheair.fr.crm_tab.registercall.Rest;

/**
 * Created by j-d on 18/12/2015.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;



public class CreateAservice {

    public static final String BASE_URL = "http://api.myservice.com/";
    private CraServiceInterface service ;

    public CreateAservice(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(CraServiceInterface.class);

    }

    public CraServiceInterface getService()
    {
        return service;
    }


}
