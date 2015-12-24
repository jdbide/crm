package pds.isintheair.fr.crm_tab.registercall;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import pds.isintheair.fr.crm_tab.R;

public class PopUpActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_call_log_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            /*// Create a new Fragment to be placed in the activity layout
            LogDialogBoxFragment firstFragment = LogDialogBoxFragment.newInstance(2);

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();*/

            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            //Create a popup instance
            //get callEnded parameters from the service and pass them to the popup
            PopUpFragment pop = PopUpFragment.newInstance(getIntent().getStringExtra("idcontact")) ;
            AddLogFragment fragment = AddLogFragment.newInstance(getIntent().getStringExtra("idcontact")
            ,getIntent().getStringExtra("date")
            ,getIntent().getStringExtra("duration")
            ,getIntent().getStringExtra("calltype"));

            ft.add(R.id.fragment_container, fragment).commit();
            pop.show(getFragmentManager(), "");
            //make popup not cancellable
            pop.setCancelable(false);
            Singleton.getInstance().setPopUpDisplayed(true);
        }
    }

    public void replace(){

        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        //Create a popup instance
        //get callEnded parameters from the service and pass them to the popup
        //PopUpFragment fragment = PopUpFragment.newInstance(8,getIntent().getStringExtra("idcontact")) ;

        //AddLogFragment fragment = AddLogFragment.newInstance(4);
        //fragment.show(getFragmentManager(), "");

    }

    /*@Subscribe
    public void onCallEndedEventReceived(CallEndedEvent callended){
        Toast.makeText(this, "event received", Toast.LENGTH_SHORT).show();

    }*/

}
