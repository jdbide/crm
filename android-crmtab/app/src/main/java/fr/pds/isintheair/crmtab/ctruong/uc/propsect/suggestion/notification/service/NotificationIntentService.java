package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.receiver.NotificationEventReceiver;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.ConsultProspect;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotificationIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }


    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            if (intent != null) {
                String action = intent.getAction();
                if (ACTION_START.equals(action)) {
                    processStartNotification();

                } else if (ACTION_DELETE.equals(action)) {

                    processDeleteNotification(intent);
                }
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }

    }

    private void processStartNotification() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Prospect Notification").setAutoCancel(true).setColor(getResources().getColor(R.color.colorAccent)).setContentText("We find a new prospect. Are you interesting?").setSmallIcon(R.drawable.mail).setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 }).setLights(Color.RED, 3000, 3000);
        Intent intent = new Intent(this, ConsultProspect.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationEventReceiver receiver = new NotificationEventReceiver();
        final NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void processDeleteNotification(Intent intent) {
        Log.i(getClass().getSimpleName(), "Delete notification");
    }


}
