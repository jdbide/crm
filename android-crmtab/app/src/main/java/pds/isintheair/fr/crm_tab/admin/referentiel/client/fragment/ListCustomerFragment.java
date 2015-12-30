package pds.isintheair.fr.crm_tab.admin.referentiel.client.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.adapter.CustomerAdapter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Customer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment.CreateHCFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Company;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Independant;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Specialty;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.fragment.CreateIndepFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.ResponseRestCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.CheckInternetConnexion;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.InitValue;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A fragment representing a list of Items.
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
/**
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // View view = inflater.inflate(R.layout.fragment_customer_list, container, false);

        return view;
    }*/

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
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
/**
    private void initIndep(final List<Customer> customers) {

        final List<Customer> indeps  = (List<Customer>)(List<?>) new Select().from(Independant.class).queryList();
        customers.addAll(indeps);

        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance()
                .getCustomerService().getIndependants(ListCustomerFragment.idUser);

        call.enqueue(new Callback<ResponseRestCustomer>() {
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                for (Independant independant : response.body().getIndependants()) {
                    customers.add(independant);
                }
                ListCustomerFragment.this.customers = customers;
                CustomerAdapter customerAdapter =
                        new CustomerAdapter(ListCustomerFragment.this.getActivity().getApplicationContext(),0,customers);
                ListCustomerFragment.this.setListAdapter(customerAdapter);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }*/

    private void callRest(final List<Customer> customers) {

        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance()
                .getCustomerService().getCustomers(ListCustomerFragment.idUser);

        call.enqueue(new Callback<ResponseRestCustomer>() {
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                for (HealthCenter healthCenter : response.body().getHealthCenters()) {
                    customers.add(healthCenter);
                }
                for(Independant independant : response.body().getIndependants()) {
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
        CustomerAdapter customerAdapter =
                new CustomerAdapter(ListCustomerFragment.this.getActivity().getApplicationContext(),0,customers);
        ListCustomerFragment.this.setListAdapter(customerAdapter);
    }

}
