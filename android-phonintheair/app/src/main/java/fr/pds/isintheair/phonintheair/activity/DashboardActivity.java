package fr.pds.isintheair.phonintheair.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.pds.isintheair.phonintheair.R;
import fr.pds.isintheair.phonintheair.service.CallService;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final Intent intent = new Intent(this, CallService.class);

        startService(intent);
    }
}
