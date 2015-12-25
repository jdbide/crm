package pds.isintheair.fr.crm_tab.registercall.Objects;

import com.squareup.otto.Bus;

/**
 * Created by j-d on 21/12/2015.
 */
public class Singleton {
    private  static Singleton instance = null;
    private String BASE_URL ;

    private  Bus currentBusInstance;
    private  Boolean popupdisplayed;

    private Singleton(){
        currentBusInstance = new Bus();
        popupdisplayed = false;
        BASE_URL = "http://192.168.1.16:8080/api/";
    }

    public static Singleton getInstance(){
        if(instance == null)
        {
            instance = new Singleton();
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

        if(BASE_URL == null)
        {
            BASE_URL = "http://192.168.1.16:8080/api/";
        }
        return BASE_URL;

    }

}
