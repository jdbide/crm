package pds.isintheair.fr.crmtab.registercall;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import pds.isintheair.fr.crmtab.registercall.Objects.CallType;
import pds.isintheair.fr.crmtab.registercall.Objects.Events.CallEndedEvent;
import pds.isintheair.fr.crmtab.registercall.Objects.Events.PendingCallEndedEventListUpdated;
import pds.isintheair.fr.crmtab.registercall.Objects.Singleton;
import pds.isintheair.fr.crmtab.registercall.Views.registeracall.RegisterCallActivity;

/**
 * Created by j-d on 21/12/2015.
 */
public class ListennerCallEndedEvent extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //registering service for events on the bus
        Singleton.getInstance().getCurrentBusInstance().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        //mNM.cancel(NOTIFICATION);

        // Tell the user we stopped.
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
    }


    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    /*private final IBinder mBinder = new LocalBinder();

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    /*public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }*/
    @Subscribe
    public void showPopup(CallEndedEvent event) {
        //if no popup displayed show
        if(!Singleton.getInstance().isPopUpDisplayed()) {
            Singleton.getInstance().setPopUpDisplayed(true);
            Intent intent = new Intent(this, RegisterCallActivity.class);
            intent.putExtra("idcontact", event.getIdcontact());
            intent.putExtra("date", event.getDate());
            intent.putExtra("duration", event.getDuration());
            intent.putExtra("calltype", event.getCalltype() == CallType.INCOMING ? "Reçu" : "Emis");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{  //else add to job
            //add event to pending list
            Singleton.getInstance().getCallEndedList().add(event);
            //tell subscribers that list has been updated
            Singleton.getInstance().getCurrentBusInstance().post(new PendingCallEndedEventListUpdated());
        }
    }

}