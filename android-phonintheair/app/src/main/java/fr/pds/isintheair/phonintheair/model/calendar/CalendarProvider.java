package fr.pds.isintheair.phonintheair.model.calendar;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.phonintheair.model.entity.CalendarData;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/19/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarProvider {
    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,
            CalendarContract.Calendars.ACCOUNT_NAME,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            CalendarContract.Calendars.OWNER_ACCOUNT
    };

    private static final int PROJECTION_ID_INDEX            = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX  = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX  = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

    private Context context;

    public CalendarProvider(Context context) {
        this.context = context;
    }

    public List<CalendarData> getCalendars(String account) {
        ContentResolver    contentResolver    = context.getContentResolver();
        Cursor             cursor             = null;
        List<CalendarData> foundCalendarsData = new ArrayList<>();
        Uri                uri                = CalendarContract.Calendars.CONTENT_URI;

        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";

        String[] selectionArgs = new String[]{account, "com.google", account};

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
            cursor = contentResolver.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

            if (cursor != null) {
                CalendarData foundCalendarData = new CalendarData();

                while (cursor.moveToNext()) {
                    foundCalendarData.setId(cursor.getLong(PROJECTION_ID_INDEX));
                    foundCalendarData.setName(cursor.getString(PROJECTION_DISPLAY_NAME_INDEX));
                    foundCalendarData.setOwner(cursor.getString(PROJECTION_OWNER_ACCOUNT_INDEX));

                    foundCalendarsData.add(foundCalendarData);
                }

                cursor.close();
            }
        }

        return foundCalendarsData;
    }
}
