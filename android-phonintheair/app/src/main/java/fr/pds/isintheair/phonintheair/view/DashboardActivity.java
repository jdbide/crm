package fr.pds.isintheair.phonintheair.view;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.phonintheair.R;
import fr.pds.isintheair.phonintheair.helper.GoogleAccountHelper;
import fr.pds.isintheair.phonintheair.model.calendar.CalendarProvider;
import fr.pds.isintheair.phonintheair.model.entity.CalendarData;

public class DashboardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        List<String> permissions = new ArrayList<>();

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CALL_PHONE);
        }

        if (checkSelfPermission(Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.GET_ACCOUNTS);
        }

        if (checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_CALENDAR);
        }

        if (checkSelfPermission(Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_CALENDAR);
        }

        if (permissions.size() > 0) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 0);
        }

        CalendarProvider calendarProvider = new CalendarProvider(this);

        List<String>       accountNames  = GoogleAccountHelper.getAccountNames(this);
        List<CalendarData> calendarDatas = calendarProvider.getCalendars(accountNames.get(0));
    }


}
