package pds.isintheair.fr.crm_tab.admin.referentiel.client.fragment;


import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.adapter.ListCustomerAdapter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Customer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment.CreateHCFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Independant;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.fragment.CreateIndepFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.display.hc.fragment.DetailHCFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.ResponseRestCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.CheckInternetConnexion;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.InitValue;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A fragment representing a list of Customers.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListCustomerFragment extends ListFragment implements CreateCustomerAlertDialog.AlertPositiveListener {

    private OnListFragmentInteractionListener mListener;
    int position = 0;
    public static int idUser = 1;
    public List<Customer> customers;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListCustomerFragment() {
    }


    @SuppressWarnings("unused")
    public static ListCustomerFragment newInstance(int columnCount) {
        ListCustomerFragment fragment = new ListCustomerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initCustomers();
        //healthCenters = InitValue.initHealthCenter();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.list_customer_menu, menu);
       // super.onCreateOptionsMenu(menu, inflater);

    }

        @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPositiveClick(int position) {
        switch (position) {
            case 0 :
                CreateHCFragment createHCFragment = new CreateHCFragment();
                getActivity().getFragmentManager().beginTransaction()
                    .replace(R.id.create_customer_fragment_container, createHCFragment)
                        .commit();
                    break;
            case 1 :

                CreateIndepFragment createIndepFragment = new CreateIndepFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.create_customer_fragment_container, createIndepFragment)
                        .commit();
                    break;
        }
    }


    public interface OnListFragmentInteractionListener {
       // void onListFragmentInteraction(DummyItem item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_list_customer_add) {


            /** Instantiating the DialogFragment class */
            CreateCustomerAlertDialog alert = new CreateCustomerAlertDialog();

            /** Creating a bundle object to store the selected item's index */
            Bundle b  = new Bundle();

            /** Storing the selected item's index in the bundle object */
            b.putInt("position", position);

            /** Setting the bundle object to the dialog fragment object */
            alert.setArguments(b);
            FragmentManager manager = getFragmentManager();
            /** Creating the dialog fragment object, which will in turn open the alert dialog window */
            alert.show(manager, "CreateCustomerDialog");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onResume() {
        super.onResume();
        InitValue.doInit();
    }


    public void initCustomers() {

        final List<Customer> customers  = (List<Customer>)(List<?>) new Select().from(HealthCenter.class).queryList();
        final List<Customer> indeps  = (List<Customer>)(List<?>) new Select().from(Independant.class).queryList();
        customers.addAll(indeps);
        if(CheckInternetConnexion.isNetworkAvailable(this.getActivity().getApplicationContext())) {
            callRest(customers);
        } else {
            initAdapter(customers);
        }
    }

    private void callRest(final List<Customer> customers) {

        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance()
                .getCustomerService().getCustomers(ListCustomerFragment.idUser);

        call.enqueue(new Callback<ResponseRestCustomer>() {
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                for (HealthCenter healthCenter : response.body().getHealthCenters()) {
                    customers.add(healthCenter);
                }
                for (Independant independant : response.body().getIndependants()) {
                    customers.add(independant);
                }
                initAdapter(customers);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void initAdapter(List<Customer> customers) {
        ListCustomerFragment.this.customers = customers;
        ListCustomerAdapter customerAdapter =
                new ListCustomerAdapter(ListCustomerFragment.this.getActivity().getApplicationContext(),0,customers);
        ListCustomerFragment.this.setListAdapter(customerAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Customer customer = customers.get(position);
        if(customer instanceof HealthCenter) startDetailHCFragment((HealthCenter) customer);
        else startDetailIndepFragment((Independant) customer);
    }

    private void startDetailHCFragment(HealthCenter healthCenter) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailHCFragment.KEY_HC_ARGS,healthCenter);
        DetailHCFragment detailHCFragment = new DetailHCFragment();
        detailHCFragment.setArguments(bundle);
        ((AppCompatActivity)getActivity()).getSupportActionBar()
                .setTitle(R.string.display_hc_fragment_title_action_bar);
        getFragmentManager().beginTransaction().addToBackStack("list")
                .replace(R.id.create_customer_fragment_container, detailHCFragment).commit();
    }

    private void startDetailIndepFragment(Independant independant) {

    }


}
