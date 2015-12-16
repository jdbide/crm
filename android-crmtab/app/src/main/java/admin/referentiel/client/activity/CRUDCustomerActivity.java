package admin.referentiel.client.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import admin.referentiel.client.create.he.fragment.CreateCustomerAlertDialog;
import admin.referentiel.client.create.he.fragment.CreateHEFragment;
import admin.referentiel.client.create.he.fragment.ListCustomerFragment;
import pds.isintheair.fr.crm_tab.R;

public class CRUDCustomerActivity extends AppCompatActivity implements CreateCustomerAlertDialog.AlertPositiveListener {

    Toolbar toolbar;
    CreateHEFragment createHEFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudcustomer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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
                createHEFragment = new CreateHEFragment();
                toolbar.setTitle(R.string.create_he_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                        .replace(R.id.create_customer_fragment_container,createHEFragment).commit();

        }
    }

}
