package pds.isintheair.fr.crm_tab.registercall;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import pds.isintheair.fr.crm_tab.R;

/**
 * Created by j-d on 21/12/2015.
 */
public class ListennerCallEndedSEvent extends Service {

    private Singleton singleton;
    private NotificationManager mNM;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = 10000;


    @Override
    public void onCreate() {
        //registering service for events on the bus
        Singleton.getInstance().getCurrentBusInstance().register(this);

        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Display a notification about us starting.  We put an icon in the status bar.
       // showNotification();
    }

    @Subscribe
    public void showPopup(CallEndedEvent event) {
        //if no popup displayed show
        if(!Singleton.getInstance().isPopUpDisplayed()) {
            Intent dialogIntent = new Intent(this, PopUpActivity.class);
            dialogIntent.putExtra("idcontact", event.getIdcontact());
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);
        }else{  //else local notification
            showNotification(event);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        mNM.cancel(NOTIFICATION);

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

    /**
     * Show a notification while this service is running.
     */
    private void showNotification(CallEndedEvent event) {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = "Service started";

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, PopUpActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.logo)  // the status icon
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Title")  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();

        // Send the notification.
            mNM.notify(i, notification);
    }
}