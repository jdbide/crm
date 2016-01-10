package pds.isintheair.fr.crmtab.admin.referentiel.client.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import pds.isintheair.fr.crmtab.admin.referentiel.client.rest.*;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 13/12/2015.
 */
public class RESTCustomerHandlerSingleton {

    private static RESTCustomerHandlerSingleton mInstance;
    private final pds.isintheair.fr.crmtab.admin.referentiel.client.rest.CustomerService customerService;

    private RESTCustomerHandlerSingleton() {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaredClass().equals(ModelAdapter.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(pds.isintheair.fr.crmtab.admin.referentiel.client.rest.CustomerService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        customerService = retrofit.create(pds.isintheair.fr.crmtab.admin.referentiel.client.rest.CustomerService.class);
    }

    public static synchronized RESTCustomerHandlerSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new RESTCustomerHandlerSingleton();
        }

        return mInstance;
    }

    public pds.isintheair.fr.crmtab.admin.referentiel.client.rest.CustomerService getCustomerService() {
        return customerService;
    }

}
