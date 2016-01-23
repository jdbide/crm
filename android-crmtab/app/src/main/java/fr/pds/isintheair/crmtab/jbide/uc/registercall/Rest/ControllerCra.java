package fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.RemoveFragmentEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.common.model.User;
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
                .baseUrl(Constants.getInstance().getBaseUrl())
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
                    alertDialog.setIcon(R.drawable.tick1);

                    // Setting OK Button
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                            Constants.getInstance().getCurrentBusInstance().post(new RemoveFragmentEvent());
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();

                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.v("rest", "no rep" + response.message());
                    Log.v("ok", "cra enregistre");
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            context).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Statut Compte-rendu");

                    // Setting Dialog Message
                    alertDialog.setMessage("Compte-rendu non enregistré");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.no_tick);

                    // Setting OK Button
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                            Constants.getInstance().getCurrentBusInstance().post(new RemoveFragmentEvent());
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                //  Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
                Log.v("Failure", t.getMessage());
                AlertDialog alertDialog = new AlertDialog.Builder(
                        context).create();

                // Setting Dialog Title
                alertDialog.setTitle("Statut Compte-rendu");

                // Setting Dialog Message
                alertDialog.setMessage("Compte-rendu non enregistré : Serveur indisponible");
                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.no_tick);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        Constants.getInstance().getCurrentBusInstance().post(new RemoveFragmentEvent());
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });


    }
}