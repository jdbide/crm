package fr.pds.isintheair.phonintheair.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fr.pds.isintheair.phonintheair.R;
import fr.pds.isintheair.phonintheair.service.CallService;

public class DashboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final Intent intent = new Intent(this, CallService.class);

        startService(intent);
    }
}
