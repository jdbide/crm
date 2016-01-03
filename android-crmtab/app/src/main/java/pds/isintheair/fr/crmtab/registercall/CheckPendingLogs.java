package pds.isintheair.fr.crmtab.registercall;


import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.List;

import pds.isintheair.fr.crmtab.R;
import pds.isintheair.fr.crmtab.registercall.Objects.Events.CallEndedEvent;
import pds.isintheair.fr.crmtab.registercall.Objects.Events.PendingCallEndedEventListUpdated;
import pds.isintheair.fr.crmtab.registercall.Objects.Singleton;

/**
 * Created by j-d on 29/12/2015.
 */
public class CheckPendingLogs extends Service {

    private static final int notification_id = 10000;
    private NotificationManager mNM;
    private int numMessages;

    @Override
    public void onCreate() {
        super.onCreate();
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Singleton.getInstance().getCurrentBusInstance().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Shows notifications if popup already displayed
     */
    @Subscribe
    public void showNotification(PendingCallEndedEventListUpdated pop) {

        List<CallEndedEvent> liste = Singleton.getInstance().getCallEndedList();

        Log.v("ok", "ok");
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
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
         //       new Intent(this, RegisterCallActivity.class), 0);
          /* Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, NotificationView.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);
         /* Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent contentIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);*/
        notification.setNumber(++numMessages);
        String[] events = new String[liste.size()];
        for (int i=0; i < liste.size(); i++) {
            events[i] = "A Historiser :" + liste.get(i).getIdcontact();
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
