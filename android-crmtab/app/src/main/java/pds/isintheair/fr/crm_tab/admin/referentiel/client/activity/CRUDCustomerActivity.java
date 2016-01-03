package pds.isintheair.fr.crm_tab.admin.referentiel.client.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.fragment.CreateIndepFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.fragment.CreateCustomerAlertDialog;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment.CreateHCFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.fragment.ListCustomerFragment;
import pds.isintheair.fr.crm_tab.R;

public class CRUDCustomerActivity extends AppCompatActivity implements CreateCustomerAlertDialog.AlertPositiveListener,
        ListCustomerFragment.OnListFragmentInteractionListener {

    Toolbar toolbar;
    CreateHCFragment createHCFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudcustomer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.create_customer_fragment_container,new ListCustomerFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle(R.string.customer_list_title);
    }

    @Override
    public void onPositiveClick(int position) {
        switch (position) {
            case 0 :
                createHCFragment = new CreateHCFragment();
                toolbar.setTitle(R.string.create_he_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                        .replace(R.id.create_customer_fragment_container, createHCFragment).commit();
                break;
            case 1 :

                CreateIndepFragment createIndepFragment = new CreateIndepFragment();
                toolbar.setTitle(R.string.create_indep_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                        .replace(R.id.create_customer_fragment_container, createIndepFragment)
                        .commit();
                break;

        }
    }

}
