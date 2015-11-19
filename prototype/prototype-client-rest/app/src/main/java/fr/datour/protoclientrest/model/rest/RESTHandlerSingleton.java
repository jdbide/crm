package fr.datour.protoclientrest.model.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RESTHandlerSingleton {
    private static RESTHandlerSingleton mInstance;
    private final UserService userService;

    private RESTHandlerSingleton() {
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
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        userService = retrofit.create(UserService.class);
    }

    public static synchronized RESTHandlerSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new RESTHandlerSingleton();
        }

        return mInstance;
    }

    public UserService getUserService() {
        return userService;
    }
}