package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.service.NotificationIntentService;

/**
 * Created by Truong on 1/24/2016.
 */
public class NotificationEventReceiver extends WakefulBroadcastReceiver{

    private static final String ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE";
    private static final String ACTION_DELETE_NOTIFICATION = "ACTION_DELETE_NOTIFICATION";
    private static final int NOTIFICATION_INTERVAL = 5;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent intent1 = null;
        if (ACTION_START_NOTIFICATION_SERVICE.equals(action)){
            Log.i(getClass().getSimpleName(), "onReceive from alarm, starting notification service");
            intent1 = NotificationIntentService.createIntentStartNotificationService(context);
        } else if (ACTION_DELETE_NOTIFICATION.equals(action)){
            Log.i(getClass().getSimpleName(), "onReceive from alarm, delete notification service");
            intent1 = NotificationIntentService.createIntentDeleteNotification(context);
        }

        if (intent1 != null){
            startWakefulService(context, intent1);
        }
    }


    public static void setUpAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getStartPendingIntent(context);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getTriggerAt(new Date()), NOTIFICATION_INTERVAL*AlarmManager.INTERVAL_HOUR, pendingIntent);
    }

    private static PendingIntent getStartPendingIntent(Context context){
        Intent intent = new Intent(context, NotificationEventReceiver.class);
        intent.setAction(ACTION_START_NOTIFICATION_SERVICE);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static PendingIntent getDeleteIntent(Context context){
        Intent intent = new Intent(context, NotificationEventReceiver.class);
        intent.setAction(ACTION_DELETE_NOTIFICATION);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static long getTriggerAt(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    private static void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getStartPendingIntent(context);
        alarmManager.cancel(pendingIntent);
    }
}
