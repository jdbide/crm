package fr.pds.isintheair.crmtab.controller.service;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.ContactsContract;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayPopUpFragmentEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.dao.CallEndedDAO;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.model.dao.ContactDAO;
import fr.pds.isintheair.crmtab.model.mock.Contact;
import fr.pds.isintheair.crmtab.view.activity.MainActivity;


/**
 * Created by j-d on 21/12/2015.
 */
public class ListennerCallEndedEvent extends Service {

    private static final int notification_id = 10000;
    private NotificationManager mNM;
    private int numMessages;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //registering service for events on the bus
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        BusHandlerSingleton.getInstance().getBus().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Listener start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        //mNM.cancel(NOTIFICATION);

        // Tell the user we stopped.
        Toast.makeText(this, "ContactRetrofitService stopped", Toast.LENGTH_SHORT).show();
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
    public void showPopup(final CallEndedEvent event) {

        boolean found = false;
        List<Contact> liste = ContactDAO.getAll();


            event.setIdcontact(event.getIdcontact().replace("+33","0"));
            Contact co = (Contact) Contact.getNameFromNumber(event.getIdcontact());
            if(co!=null)
                found = true;



       if(found) {
/*
           //if no popup displayed show
           if (!Constant.isPopUpDisplayed()) {
               Constant.setPopUpDisplayed(true);

               Handler handler = new Handler();
               handler.postDelayed(new Thread(new delay(event)),2000);




           } else {  //else add to job
               //add event to pending list

               event.save();
               //Constants.getInstance().getPendindList().add(event);
               //tell subscribers that list has been updated
               //Constants.getInstance().getCurrentBusInstance().post(new PendingLogEvent());
               notifyLocally();
           }*/
       }
    }

    public class delay implements Runnable{

        private CallEndedEvent myevent;
        public delay(CallEndedEvent event){
            myevent = event;
        }

        @Override
        public void run() {
            BusHandlerSingleton.getInstance().getBus().post(new DisplayPopUpFragmentEvent(myevent));
        }
    }


    /**
     * Shows notifications if popup already displayed
     */
    public void notifyLocally() {


        List<CallEndedEvent> liste = CallEndedDAO.getAll();
        Log.v("size",String.valueOf(liste.size()));
        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)  // the status icon
                .setTicker("Nouvel appel à historiser")  // the status text
                        //.setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("CRM-TAB")  // the label of the entry
                .setContentText("Appels en attente d'enregistrement")
                .setAutoCancel(true);



        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        // The PendingIntent to launch our activity if the user selects this notification
          // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("msg","notification");
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //stackBuilder.addParentStack(NotificationView.class);
         // Adds the Intent that starts the Activity to the top of the stack
        //stackBuilder.addNextIntent(resultIntent);
        //PendingIntent contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        notification.setContentIntent(resultPendingIntent);

        notification.setNumber(++numMessages);
        String[] events = new String[liste.size()];
        for (int i=0; i < liste.size(); i++) {
            Contact co = (Contact) Contact.getNameFromNumber(liste.get(i).getIdcontact());
            if(co!=null)
                events[i] = liste.get(i).getDate() + ":" + co.getLastName()+" "+co.getFirstName() ;
            else
            events[i] = liste.get(i).getDate() + ":" + liste.get(i).getIdcontact();
        }
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Comptes-rendus d'appels à enregistrer");

        // Moves events into the expanded layout
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        // Moves the expanded layout object into the notification object.
        notification.setStyle(inboxStyle);

        // Send the notification.
        mNM.notify(notification_id, notification.build());
    }


    public static class ContactService extends Service {

        private int mContactCount;

        @Override
        public IBinder onBind(Intent arg0) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            mContactCount = getContactCount();
            this.getContentResolver().registerContentObserver(
                    ContactsContract.Contacts.CONTENT_URI, true, mObserver);
        }

        private int getContactCount() {
            Cursor cursor = null;
            try {
                cursor = getContentResolver().query(
                        ContactsContract.Contacts.CONTENT_URI, null, null, null,
                        null);
                if (cursor != null) {
                    return cursor.getCount();
                } else {
                    return 0;
                }
            } catch (Exception ignore) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            return 0;
        }

        private ContentObserver mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                final int currentCount = getContactCount();
                if (currentCount < mContactCount) {
                    // DELETE HAPPEN.
                } else if (currentCount == mContactCount) {
                    // UPDATE HAPPEN
                    String id, name, phone, hasPhone;
                    int idx;

                    Cursor cur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);


                    if(cur.moveToFirst()) {

                        while (cur.isAfterLast() == false) {
                            String nam  = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            String phoneNumber = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                            cur.moveToNext();
                        }
                    }

                    cur.close();


                } else {
                    // INSERT HAPPEN.
                }
                mContactCount = currentCount;
            }
        };


        public void test(){
            Account[] accountList = AccountManager.get(this).getAccounts();

            String accountSelection = "";
            for(int i = 0 ; i < accountList.length ; i++) {
                if(accountSelection.length() != 0)
                    accountSelection = accountSelection + " AND ";
                accountSelection = accountSelection + ContactsContract.Groups.ACCOUNT_TYPE + " != '" +  accountList[i].type + "'";
                accountSelection.contains("");
            }
        }






        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return START_NOT_STICKY;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            getContentResolver().unregisterContentObserver(mObserver);
        }

    }
}