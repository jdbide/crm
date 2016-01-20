package pds.isintheair.fr.crmtab.registercall.Rest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import pds.isintheair.fr.crmtab.R;

import pds.isintheair.fr.crmtab.registercall.Objects.Events.RemoveFragmentEvent;
import pds.isintheair.fr.crmtab.registercall.Objects.Singleton;
import pds.isintheair.fr.crmtab.registercall.Rest.Model.Cra;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by j-d on 08/01/2016.
 */
public class ControllerCra {



    public static void registerCra(Cra cra, final Activity context) {


        //Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        // add logging as last interceptor
        httpClient.interceptors().add(logging);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        Methods service = retrofit.create(Methods.class);
        Call<Boolean> call = service.createcra(cra);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    Log.v("ok", "cra enregistre");
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            context).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Statut Compte-rendu");

                    // Setting Dialog Message
                    alertDialog.setMessage("Compte-rendu enregistré");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.tick);

                    // Setting OK Button
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                            Singleton.getInstance().getCurrentBusInstance().post(new RemoveFragmentEvent());
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();

                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.v("rest", "no rep" + response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //  Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
                Log.v("Failure", t.getMessage());
            }
        });
        //Redirect to Call log list view
        //((RegisterCallActivity)(getActivity())).showCallLogList();
    }

   /* public static void hasAccount(User user, final Activity context) {

        User result = null;

        //Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        // add logging as last interceptor
        httpClient.interceptors().add(logging);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        Methods service = retrofit.create(Methods.class);
        Call<User> call = service.basicLogin(user);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                User result = response.body();
                Log.v("result", "ooooooook");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.v("fail", "fail");
            }
        });


    }*/
}
