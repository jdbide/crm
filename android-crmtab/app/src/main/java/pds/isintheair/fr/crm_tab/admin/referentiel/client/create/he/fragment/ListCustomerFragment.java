package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.Select;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.message.ResponseRestCustomer;
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
public class ListCustomerFragment extends Fragment implements CreateCustomerAlertDialog.AlertPositiveListener {

    private OnListFragmentInteractionListener mListener;
    int position = 0;


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

        if (getArguments() != null) {

        }
        initHolding();
        initPurchasingCentral();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);



        return view;
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
                getFragmentManager().beginTransaction().addToBackStack("listCustomerFragment")
                    .replace(R.id.create_customer_fragment_container, createHCFragment).commit();

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
            alert.show(manager,"CreateCustomerDialog");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initHolding() {

        if(new Select().count().from(Holding.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getHoldings();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    for (Holding holding : response.body().getHoldings()) {
                        holding.save();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    private void initPurchasingCentral() {

        if(new Select().count().from(PurchasingCentral.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getPurchasingCentral();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    for (PurchasingCentral purchasingCentral : response.body().getPurchasingCentrals()) {
                        purchasingCentral.save();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

}
