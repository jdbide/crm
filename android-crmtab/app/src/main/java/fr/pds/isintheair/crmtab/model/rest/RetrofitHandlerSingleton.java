package fr.pds.isintheair.crmtab.model.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import fr.pds.isintheair.crmtab.model.rest.service.LoginService;
import fr.pds.isintheair.crmtab.phoning.model.constant.Constant;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/******************************************
 * Created by        :                    *
 * Creation date     : 03/20/16            *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class RetrofitHandlerSingleton {
    private static RetrofitHandlerSingleton mInstance = new RetrofitHandlerSingleton();

    private LoginService loginService;

    private RetrofitHandlerSingleton() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaredClass().equals(ModelAdapter.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        Retrofit loginRetrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        loginService = loginRetrofit.create(LoginService.class);
    }

    public static synchronized RetrofitHandlerSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitHandlerSingleton();
        }

        return mInstance;
    }

    public LoginService getLoginService() {
        return loginService;
    }
}
