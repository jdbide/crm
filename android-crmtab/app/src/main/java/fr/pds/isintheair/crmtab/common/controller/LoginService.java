package fr.pds.isintheair.crmtab.common.controller;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import fr.pds.isintheair.crmtab.common.model.User;
import fr.pds.isintheair.crmtab.common.service.LoginServiceInterface;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
/**
 * Created by jbide on 22/01/2016.
 */
public class LoginService {

    public static void login(User user,final Context context,final RelativeLayout anim,final CoordinatorLayout coordlayout) {

        //Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        // add logging as last interceptor
        httpClient.interceptors().add(logging);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        LoginServiceInterface service = retrofit.create(LoginServiceInterface.class);
        Call<User> call = service.login(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                User rep = response.body();
                if(rep!=null) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", rep.getEmail());
                    editor.putString("tel", rep.getTel());
                    editor.putString("fname", rep.getFname());
                    editor.putString("lname", rep.getLname());
                    editor.putString("password", rep.getPassword());
                    editor.putString("id", rep.getId());
                    editor.commit();
                }
                    context.startActivity(new Intent(context, MainActivity.class));
                    Snackbar
                            .make(coordlayout, "connection succeed", Snackbar.LENGTH_LONG).show();
                anim.setVisibility(View.GONE);

                } else {
                    Snackbar
                            .make(coordlayout, "response not success", Snackbar.LENGTH_LONG).show();

                    anim.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //  Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
                Log.v("Failure", t.getMessage());
               Snackbar
                        .make(coordlayout, "onfailure connection", Snackbar.LENGTH_LONG).show();

                anim.setVisibility(View.GONE);

            }
        });

    }
    /*private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constants.getInstance().getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    // set your desired log level


    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            // add logging as last interceptor
            httpClient.interceptors().add(logging);
            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic);
                    requestBuilder.header("Accept", "applicaton/json");
                    requestBuilder.method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }*/
}
