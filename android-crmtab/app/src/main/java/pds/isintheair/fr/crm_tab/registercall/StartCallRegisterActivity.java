package pds.isintheair.fr.crm_tab.registercall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.squareup.otto.Bus;

import java.util.Calendar;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.registercall.Objects.Events.CallEndedEvent;
import pds.isintheair.fr.crm_tab.registercall.Objects.CallType;
import pds.isintheair.fr.crm_tab.registercall.Objects.Singleton;
import pds.isintheair.fr.crm_tab.registercall.Views.displaycalls.LaunchDisplayLogFragment;

public class StartCallRegisterActivity extends AppCompatActivity {

    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_call_register_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        //start local services
        startService(new Intent(this, ListennerCallEndedEvent.class));
        startService(new Intent(this, CheckPendingLogs.class));
        bus = Singleton.getInstance().getCurrentBusInstance();
        bus.register(this);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }
    //@OnClick(R.id.fab)
    public void goo(View v){
        bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "11111111"));
        bus.post(new CallEndedEvent(CallType.OUTGOING, Calendar.getInstance().getTime().toLocaleString(), "502", "33333333"));
        bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(),"1038","5555555"));
        bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "7777777777"));
    }

    //@OnClick(R.id.showlist)
    public void go(View v){
        startActivity(new Intent(StartCallRegisterActivity.this, LaunchDisplayLogFragment.class));
    }



}
