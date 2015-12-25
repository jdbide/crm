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
import pds.isintheair.fr.crm_tab.registercall.Objects.CallEndedEvent;
import pds.isintheair.fr.crm_tab.registercall.Objects.CallType;
import pds.isintheair.fr.crm_tab.registercall.Objects.Singleton;

public class StartCallRegisterActivity extends AppCompatActivity {

    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_call_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        Intent intent = new Intent(this, ListennerCallEndedSEvent.class);
        startService(intent);
        bus = Singleton.getInstance().getCurrentBusInstance();
        bus.register(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }
    @OnClick(R.id.fab)
    public void goo(View v){
        bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(),"1034","11111111"));
        bus.post(new CallEndedEvent(CallType.OUTGOING, Calendar.getInstance().getTime().toLocaleString(),"502","33333333"));
        bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(),"1038","5555555"));
        bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(),"1034","7777777777"));
    }





}
