package pds.isintheair.fr.crm_tab.registercall;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.registercall.Objects.CallEndedEvent;
import pds.isintheair.fr.crm_tab.registercall.Objects.CallType;
import pds.isintheair.fr.crm_tab.registercall.Objects.Singleton;
import pds.isintheair.fr.crm_tab.registercall.Views.registeracall.RegisterCallActivity;

/**
 * Created by j-d on 21/12/2015.
 */
public class ListennerCallEndedSEvent extends Service {

    private Singleton singleton;
    private NotificationManager mNM;
    private int numMessages;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // Unique Identification Number for the Notification.
    private static final int notification_id = 10000;
    private List<CallEndedEvent> callEndedEventList;


    @Override
    public void onCreate() {
        //registering service for events on the bus
       singleton = Singleton.getInstance();
       singleton.getCurrentBusInstance().register(this);
        callEndedEventList = new ArrayList<CallEndedEvent>();
        numMessages = 0;
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Display a notification about us starting.  We put an icon in the status bar.
       // showNotification();
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
        if(!singleton.isPopUpDisplayed()) {
            Intent intent = new Intent(this, RegisterCallActivity.class);
            intent.putExtra("idcontact", event.getIdcontact());
            intent.putExtra("date", event.getDate());
            intent.putExtra("duration", event.getDuration());
            intent.putExtra("calltype", event.getCalltype() == CallType.INCOMING ? "Reçu" : "Emis");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{  //else local notification
            callEndedEventList.add(event);
            showNotification(event);
        }
    }

    /**
     * Shows notifications if popup already displayed
     */
    private void showNotification(CallEndedEvent event) {



        // Set the info for the views that show in the notification panel.
        android.support.v7.app.NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)  // the status icon
                .setTicker("Nouvel appel à historiser")  // the status text
                        //.setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Title")  // the label of the entry
                .setContentText("text");

        // the contents of the entry
        //.setContentIntent(contentIntent)  // The intent to send when the entry is clicked
        //.build();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, RegisterCallActivity.class), 0);

          /* Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);
         /* Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent contentIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);*/



        notification.setNumber(++numMessages);
        String[] events = new String[6];
        for (int i=0; i < callEndedEventList.size(); i++) {

            events[i] = "A Historiser :" + callEndedEventList.get(i).getIdcontact();

        }
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Vous avez des appels à historiser");

        // Moves events into the expanded layout
        for (int i=0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
// Moves the expanded layout object into the notification object.
        notification.setStyle(inboxStyle);

        // Send the notification.
            mNM.notify(notification_id, notification.build());
    }
}