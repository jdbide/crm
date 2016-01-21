package fr.pds.isintheair.crmtab.registercall.Views.displaycalls;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.registercall.Rest.Model.Cra;

/**
 * Created by j-d on 26/12/2015.
 */
public class LaunchDisplayLogFragment extends AppCompatActivity implements DisplayCallLogFragment.OnListFragmentInteractionListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaycalls_container);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);

        // if (findViewById(R.id.launch_display_fragment_container) != null) {

        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        //if (savedInstanceState != null) {
        //    return;
        // }

        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
            /*Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }*/
        //ft.addToBackStack(null);
        //Create a popup instance
        //get callEnded parameters from the service and pass them to the popup
        DisplayCallLogFragment fragment = DisplayCallLogFragment.newInstance(1);
        ft.add(R.id.launch_display_fragment_container, fragment, "FRAGMENT_LISTE").commit();

        //}
    }

    //Click on button display
    @Override
    public void onListFragmentInteraction(Cra cra) {

        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
        Fragment prev = getFragmentManager().findFragmentByTag("FRAGMENT_LISTE");
        if (prev != null) {
            ft.remove(prev);
        }

        //ft.addToBackStack(null);
        //startActivity(new Intent(this,CallDetailsActivity.class));
        //get callEnded parameters from the service and pass them to the popup
        CallDetailsFragment fragment = CallDetailsFragment.newInstance(cra);
        ft.add(R.id.launch_display_fragment_container, fragment).commit();
        //ft.detach(fragment);
        //ft.attach(fragment);
        //ft.commit();
    }
}
