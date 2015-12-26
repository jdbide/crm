package pds.isintheair.fr.crm_tab.registercall.Views.registeracall;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.registercall.Objects.Singleton;
import pds.isintheair.fr.crm_tab.registercall.Views.displaycalls.DisplayCallLogFragment;

public class RegisterCallActivity extends FragmentActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_call_container);
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

            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            //ft.addToBackStack(null);
            //Create a popup instance
            //get callEnded parameters from the service and pass them to the popup
            PopUpFragment pop = PopUpFragment.newInstance(getIntent().getStringExtra("idcontact")) ;
            AddLogFragment fragment = AddLogFragment.newInstance(getIntent().getStringExtra("idcontact")
            ,getIntent().getStringExtra("date")
            ,getIntent().getStringExtra("duration")
            ,getIntent().getStringExtra("calltype"));
            ft.add(R.id.fragment_container, fragment, "FRAGMENT_AJOUT").commit();
            pop.show(getFragmentManager(), "");
            //make popup not cancellable
            pop.setCancelable(false);
            Singleton.getInstance().setPopUpDisplayed(true);
        }
    }

    public void showCallLogList(){
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
        Fragment prev = getFragmentManager().findFragmentByTag("FRAGMENT_AJOUT");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        ft.add(R.id.fragment_container, DisplayCallLogFragment.newInstance(1),"FRAGMENT_LISTE").commit();
    }


    /*@Subscribe
    public void onCallEndedEventReceived(CallEndedEvent callended){
        Toast.makeText(this, "event received", Toast.LENGTH_SHORT).show();

    }*/

}
