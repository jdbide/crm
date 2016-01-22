package fr.pds.isintheair.crmtab.registercall.Objects;

import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.registercall.Objects.Events.CallEndedEvent;
import fr.pds.isintheair.crmtab.registercall.User;


import fr.pds.isintheair.crmtab.registercall.Rest.Model.Cra;

/**
 * Created by j-d on 21/12/2015.
 */
public class Constants {
    private  static Constants instance = null;
    private String BASE_URL ;
    private User currentUser;
    private  Bus currentBusInstance;
    private  Boolean popupdisplayed;
    private List<CallEndedEvent> pendigCallList;
    private List<Cra> CraListForUser;


    private Constants(){
        currentBusInstance = new Bus();
        currentUser = new User();
        popupdisplayed = false;
        //BASE_URL = "http://192.168.43.131:8080/api/";
        //BASE_URL = "http://192.168.1.68:8080/api/";
        BASE_URL = "http://192.168.20.3:8070/api/";
        pendigCallList = new ArrayList<CallEndedEvent>();
        CraListForUser = new ArrayList<Cra>();

    }

    public static Constants getInstance(){
        if(instance == null)
        {
            instance = new Constants();
        }
        return instance;
    }

    public Boolean isPopUpDisplayed(){

        if(popupdisplayed == null)
        {
            popupdisplayed = false;
        }
        return popupdisplayed;

    }

    public void setPopUpDisplayed(boolean state){

         popupdisplayed = state;
    }

    public  Bus getCurrentBusInstance(){

        if(currentBusInstance == null)
        {
            currentBusInstance = new Bus();
        }
        return currentBusInstance;
    }

    public  String getBaseUrl(){

        return BASE_URL;
    }

    public void setCraListForUser(List<Cra> liste){

            CraListForUser = liste;

    }

    public void setCurrentUser(User user){

        currentUser = user;

    }

    public List<Cra> getCraListForUser(){

        if(CraListForUser == null)
        {
            CraListForUser = new ArrayList<Cra>();
        }
        return CraListForUser;
    }

    public List<CallEndedEvent> getPendingCallList(){

        if(pendigCallList == null)
        {
            pendigCallList = new ArrayList<CallEndedEvent>();
        }
        return pendigCallList;
    }

    public User getCurrentUser(){

        return currentUser;
    }

    public void removeItemFromPendingCallList(int position){

        pendigCallList.remove(position);

    }



}
