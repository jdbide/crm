package fr.pds.isintheair.crmtab.common.controller;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.common.model.rest.LoginServiceInterface;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.receiver.NotificationEventReceiver;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.service.CallService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by jbide on 22/01/2016.
 */
public class LoginService {

    public static void login(final User userlogging, final Context context, final RelativeLayout anim, final CoordinatorLayout coordlayout) {

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
        Call<User>            call    = service.login(userlogging);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    User rep = response.body();

                    if (rep != null) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("email", rep.getEmail());
                        editor.putString("tel", rep.getTel());
                        editor.putString("fname", rep.getFname());
                        editor.putString("lname", rep.getLname());
                        editor.putString("password", rep.getPassword());
                        editor.putString("id", rep.getId());
                        editor.commit();

                        User user = new User();
                        user.setEmail(rep.getEmail());
                        user.save();
                        Snackbar.make(coordlayout, "Credentials Ok for user " + rep.getEmail(), Snackbar.LENGTH_LONG).show();
                        final Intent intent = new Intent(context, CallService.class);
                        context.startService(intent);

                        context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        NotificationEventReceiver.setUpAlarm(context);
                    }
                    else {
                        Snackbar.make(coordlayout, "User: " + userlogging.getEmail() + " : Wrong Credentials or not registererd ", Snackbar.LENGTH_LONG).show();
                    }


                }
                else {

                    Snackbar.make(coordlayout, "No response from server ", Snackbar.LENGTH_LONG).show();

                }
                anim.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {

                Log.v("Failure", t.getMessage());
                Snackbar
                        .make(coordlayout, "onfailure connection", Snackbar.LENGTH_LONG).show();
                anim.setVisibility(View.GONE);

            }
        });

    }
}
