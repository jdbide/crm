package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Calendar;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.create.he.fragment.CreateHCFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.create.indep.fragment.CreateIndepFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.fragment.CreateCustomerAlertDialog;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.fragment.ListCustomerFragment;
import fr.pds.isintheair.crmtab.crv.view.ClientListFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.ListennerCallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Objects.CallType;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Objects.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Objects.Events.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Objects.Events.DisplayAddLogFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.User;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.callsnotregistered.PendingLogsFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.displaycalls.CallDetailsFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.displaycalls.DisplayCallLogFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.registeracall.AddLogFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.registeracall.PopUpFragment;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.ContactDetailActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,DisplayCallLogFragment.OnListFragmentInteractionListener,
        PendingLogsFragment.OnListFragmentInteractionListener,CreateCustomerAlertDialog.AlertPositiveListener,
        ListCustomerFragment.OnListFragmentInteractionListener {

    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


        //start local services
        startService(new Intent(this, ListennerCallEndedEvent.class));
        bus = Constants.getInstance().getCurrentBusInstance();
        bus.register(this);

        User user = new User();
        user.setTel(01234567);
        Constants.getInstance().setCurrentUser(user);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //
        if(getIntent().hasExtra("msg"))
            showNotificationListFrag();
    }

 /**   @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_passer_appel) {
            Intent intentContact = new Intent(MainActivity.this, ContactDetailActivity.class);
            startActivity(intentContact);
        } else if (id == R.id.nav_historiser_appel) {
            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "11111111",true));
            bus.post(new CallEndedEvent(CallType.OUTGOING, Calendar.getInstance().getTime().toLocaleString(), "502", "33333333",true));
            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1038", "5555555",true));
            bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "7777777777",true));

            //DisplayCallLogFragment fragment = DisplayCallLogFragment.newInstance(1) ;
            //ft.replace(R.id.container, fragment, "FRAGMENT_AJOUT").addToBackStack(null).commit();
        } else if (id == R.id.nav_editer_crv) {
            ClientListFragment clientListFragment = new ClientListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, clientListFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_ref_client) {
           // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
           // setSupportActionBar(toolbar);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new ListCustomerFragment());
            fragmentTransaction.addToBackStack("menu");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_envoyer_sms) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // UC Register a call
    private PendingLogsFragment pend;

    public void showNotificationListFrag() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
        pend = PendingLogsFragment.newInstance(1);
        ft.replace(R.id.container, pend, "FRAGMENT_LISTE_NOTIF").addToBackStack(null).commit();
    }

    @Subscribe
    public void showaddlogfrag(DisplayAddLogFragment event) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);

        AddLogFragment fragment = AddLogFragment.newInstance(event.getCallEndedEvent().getIdcontact()
                , event.getCallEndedEvent().getDate()
                , event.getCallEndedEvent().getDuration()
                , event.getCallEndedEvent().getCalltype()==CallType.INCOMING?"Entrant":"Sortant",event.getCallEndedEvent().getDisplaypopUp());
        ft.replace(R.id.container, fragment, "FRAGMENT_AJOUT").commit();

        if(event.getCallEndedEvent().getDisplaypopUp()) {
            PopUpFragment pop = PopUpFragment.newInstance(getIntent().getStringExtra("idcontact"));
            pop.show(getFragmentManager(), "dialog");
            //make popup not cancellable
            pop.setCancelable(false);
        }
    }



    @Override
    public void onListFragmentInteraction(Cra cra) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
        /*Fragment prev = getFragmentManager().findFragmentByTag("FRAGMENT_AJOUT");
        if (prev != null) {
            ft.remove(prev);
        }*/

        ft.addToBackStack(null);
        CallDetailsFragment fragment = CallDetailsFragment.newInstance(cra) ;
        ft.replace(R.id.container, fragment).addToBackStack(null).commit();

    }

    @Override
    public void onListFragmentInteraction(CallEndedEvent item) {

    }

    public void removefrag(String fragtag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragtag);
        if(fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }


    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            Intent intent = new Intent(this,MainActivity.class);
            finish();
            startActivity(intent);
        }
    }


    @Override
    public void onPositiveClick(int position) {
        switch (position) {
            case 0 :
                CreateHCFragment createHCFragment = new CreateHCFragment();
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
                toolbar.setTitle(R.string.create_he_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                        .replace(R.id.create_customer_fragment_container, createHCFragment).commit();
                break;
            case 1 :

                CreateIndepFragment createIndepFragment = new CreateIndepFragment();
                Toolbar toolbarindep = (Toolbar) findViewById(R.id.toolbar1);
                toolbarindep.setTitle(R.string.create_indep_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                        .replace(R.id.create_customer_fragment_container, createIndepFragment)
                        .commit();
                break;

        }
    }



}

