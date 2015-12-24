package pds.isintheair.fr.crm_tab.registercall;

import com.squareup.otto.Bus;

/**
 * Created by j-d on 21/12/2015.
 */
public class Singleton {
    private  static Singleton instance = null;

    private  Bus currentBusInstance;
    private Boolean popupdisplayed;

    private Singleton(){
        currentBusInstance = new Bus();
        popupdisplayed = false;
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

        if(popupdisplayed == null) popupdisplayed = state;
    }

    public  Bus getCurrentBusInstance(){

        if(currentBusInstance == null)
        {
            currentBusInstance = new Bus();
        }
        return currentBusInstance;

    }

}
