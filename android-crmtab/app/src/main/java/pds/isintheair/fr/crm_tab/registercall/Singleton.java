package pds.isintheair.fr.crm_tab.registercall;

import com.squareup.otto.Bus;

/**
 * Created by j-d on 21/12/2015.
 */
public class Singleton {
    private  static Singleton instance = null;

    private  Bus currentBusInstance;

    private Singleton(){
        currentBusInstance = new Bus();
    }

    public static Singleton getInstance(){
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    public  Bus getCurrentBusInstance(){

        if(currentBusInstance == null)
        {
            currentBusInstance = new Bus();
        }
        return currentBusInstance;

    }

}
