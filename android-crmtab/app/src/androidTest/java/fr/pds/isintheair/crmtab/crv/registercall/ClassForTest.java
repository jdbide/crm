package fr.pds.isintheair.crmtab.crv.registercall;

import android.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.registercall.Views.callsnotregistered.PendingLogsFragment;
import fr.pds.isintheair.crmtab.registercall.Views.registeracall.AddLogFragment;

/**
 * Created by bide2 on 21/01/2016.
 */
public class ClassForTest extends ActivityInstrumentationTestCase2<MainActivity> {



        MainActivity activity;

        public ClassForTest(Class<MainActivity> activityClass) {
            super(activityClass);
        }

        public ClassForTest() {
            super(MainActivity.class);
        }


        @Override
        public void setUp() throws Exception {
            super.setUp();
            activity = getActivity();
        }

        public void addLogFragment() throws Exception {
            Fragment frag =
                    activity.getFragmentManager().findFragmentById(R.id.container);
            boolean bool = false;
            if (frag instanceof PendingLogsFragment) {
                bool = true;
            }
            assertTrue(bool);
        }

    public void pendingLogFragment() throws Exception {
        Fragment frag =
                activity.getFragmentManager().findFragmentById(R.id.container);
        boolean bool = false;
        if (frag instanceof AddLogFragment) {
            bool = true;
        }
        assertTrue(bool);
    }

}
