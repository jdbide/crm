package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Report;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.retrofit.Service;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view.CrvMainActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Muthu on 06/01/2016.
 */
public class CrvController {
    List<Report> reports = new ArrayList<Report>();
    Boolean status;
    public List<Report> getAllReportForClient(String idClient , final Client client, final Context context){
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.20.3:8070/api/crv/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        Service iService = retrofit.create(Service.class);
        Call<List<Report>> call = iService.getReportList(idClient);
        call.enqueue(new Callback<List<Report>>() {

            @Override
            public void onResponse(Response<List<Report>> response, Retrofit retrofit) {
                reports = response.body();
                Log.d("onResponse", "" + response.body().size());


                Intent intent = new Intent(context, CrvMainActivity.class);
                intent.putExtra("ClientObject", client);
                Bundle b = new Bundle();
                b.putSerializable("list", (Serializable) reports);
                intent.putExtra("listReport", b);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure","onFailure()");
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return reports;
    }

    public Boolean deleteReport(String id, final Client client, final Context context){


        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.20.3:8070/api/crv/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Service iService = retrofit.create(Service.class);
        Call<Boolean> call = iService.deleteReport(id);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    status = true;
                    getAllReportForClient(Integer.toString(client.getClientId()),client,context);

                }else{
                    status = false;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                    status = false;
            }
        });

        return status;
    }
}
