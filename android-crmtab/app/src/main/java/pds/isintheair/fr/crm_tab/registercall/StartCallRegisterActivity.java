package pds.isintheair.fr.crm_tab.registercall;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.squareup.otto.Bus;

import java.util.Calendar;

import butterknife.OnClick;
import pds.isintheair.fr.crm_tab.R;

public class StartCallRegisterActivity extends AppCompatActivity {

    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_call_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = new Intent(this, ListennerCallEndedSEvent.class);
        startService(intent);
        bus = Singleton.getInstance().getCurrentBusInstance();
        bus.register(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }
    @OnClick(R.id.fab)
    public void goo(View v){
        bus.post(new CallEndedEvent(true, Calendar.getInstance().getTime().toLocaleString(),1034,00000000,11111111));
    }





}
