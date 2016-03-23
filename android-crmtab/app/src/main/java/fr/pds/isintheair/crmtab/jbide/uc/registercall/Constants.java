package fr.pds.isintheair.crmtab.jbide.uc.registercall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fr.pds.isintheair.crmtab.Constant;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.model.entity.Client;
import fr.pds.isintheair.crmtab.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.model.entity.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.model.entity.User;
import fr.pds.isintheair.crmtab.model.rest.service.CrvRetrofitService;
import fr.pds.isintheair.crmtab.view.activity.CrvHomeActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by j-d on 21/12/2015.
 */
public class Constants {
    private static Constants instance = null;
    private String               BASE_URL;
    private AndroidBus           currentBusInstance;
    private Boolean              popupdisplayed;
    private List<Cra>            CraListForUser;
    private User                 currentUser;
    private List<CallEndedEvent> pendingList;


    private Constants() {
        currentBusInstance = new AndroidBus();
        popupdisplayed = false;
        //BASE_URL = "http://192.168.43.131:8080/api/";
        //BASE_URL = "http://192.168.1.68:8080/api/";
        BASE_URL = "http://192.168.20.3:8082/api/";
        CraListForUser = new ArrayList<Cra>();
        currentUser = new User();
        pendingList = new ArrayList<CallEndedEvent>();


    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }


    public Boolean isPopUpDisplayed() {

        if (popupdisplayed == null) {
            popupdisplayed = false;
        }
        return popupdisplayed;

    }

    public void setPopUpDisplayed(boolean state) {

        popupdisplayed = state;
    }

    public AndroidBus getCurrentBusInstance() {

        if (currentBusInstance == null) {
            currentBusInstance = new AndroidBus();
        }
        return currentBusInstance;
    }

    public String getBaseUrl() {

        return BASE_URL;
    }

    public List<Cra> getCraListForUser() {

        if (CraListForUser == null) {
            CraListForUser = new ArrayList<Cra>();
        }
        return CraListForUser;
    }

    public void setCraListForUser(List<Cra> liste) {

        CraListForUser = liste;

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentuser) {
        currentUser = currentuser;
    }

    public List<CallEndedEvent> getPendindList() {
        return pendingList;
    }

    List<Client> clients = new ArrayList<Client>();
    List<HealthCenter> healthCenters = new ArrayList<HealthCenter>();
    Client client;


}
