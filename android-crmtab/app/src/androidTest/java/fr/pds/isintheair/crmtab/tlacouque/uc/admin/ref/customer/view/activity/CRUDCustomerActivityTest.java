package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.activity;

import android.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.activity.CRUDCustomerActivity;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateHCFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateIndepFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.ListCustomerFragment;


/**
 * Created by tlacouque on 18/01/2016.
 */
public class CRUDCustomerActivityTest extends ActivityInstrumentationTestCase2<CRUDCustomerActivity> {

    CRUDCustomerActivity activity;

    public CRUDCustomerActivityTest(Class<CRUDCustomerActivity> activityClass) {
        super(CRUDCustomerActivity.class);
    }

    public CRUDCustomerActivityTest() {
        super(CRUDCustomerActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public void testInitListFragment() throws Exception {
        Fragment listCustomerFragment =
                activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);
        boolean bool = false;
        if (listCustomerFragment instanceof ListCustomerFragment) {
            bool = true;
        }
        assertTrue(bool);
    }

    public void testCreateHCClick() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                activity.onPositiveClick(0);
            }
        });
        getInstrumentation().waitForIdleSync();
        Fragment createHCFragment =
                activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);
        boolean bool = false;
        if(createHCFragment instanceof CreateHCFragment) {
            bool = true;
        }
        assertTrue(bool);
    }

    public void testCreateIndepClick() throws Exception {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                activity.onPositiveClick(1);
            }
        });
        getInstrumentation().waitForIdleSync();
        Fragment createHCFragment =
                activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);
        boolean bool = false;
        if(createHCFragment instanceof CreateIndepFragment) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Override
    public void tearDown() throws Exception {
        activity.finish();
    }
}