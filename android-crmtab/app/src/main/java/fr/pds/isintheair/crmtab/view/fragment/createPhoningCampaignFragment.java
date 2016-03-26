package fr.pds.isintheair.crmtab.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.helper.CheckInternetConnexion;
import fr.pds.isintheair.crmtab.model.dao.UserDAO;
import fr.pds.isintheair.crmtab.model.entity.Customer;
import fr.pds.isintheair.crmtab.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.model.entity.Independant;
import fr.pds.isintheair.crmtab.model.entity.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.model.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 26/03/2016.
 */
public class CreatePhoningCampaignFragment extends Fragment {

    public String idUser = UserDAO.getCurrentUser().getId();

    @Bind(R.id.create_phoning_campaign_fragment_title)
    @NotEmpty
    EditText title;

    @Bind(R.id.create_phoning_campaign_fragment_objective)
    @NotEmpty
    EditText objective;

    @Bind(R.id.create_phoning_campaign_fragment_campaign_type)
    @NotEmpty
    Spinner type;

    @Bind(R.id.create_phoning_campaign_fragment_list_customer)
    @NotEmpty
    ListView customer;

    @Bind(R.id.create_phoning_campaign_fragment_list_customer_added)
    @NotEmpty
    ListView customerAdded;

    List<Customer> customers;

    List<Customer> customersAdded;

    public CreatePhoningCampaignFragment() {
        // Required empty public constructor
    }

    /**
     * Can be called when a new CreatePhoningCampaignFragment is needed
     *
     * @return CreatePhoningCampaignFragment
     */
    public static CreatePhoningCampaignFragment newInstance() {
        CreatePhoningCampaignFragment fragment = new CreatePhoningCampaignFragment();
        Bundle args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_phoning_campaign, container, false);
        ButterKnife.bind(this, v);
        return v;
    }


    /**
     * Initialise the list of customer. if the user have an internet connexion
     * a rest call is send to have customer added by other commercials.
     */
    public void initCustomers() {

        final List<Customer> customers        = (List<Customer>) (List<?>) new Select().from(HealthCenter.class).queryList();
        final List<Customer>     customersindeps  = (List<Customer>) (List<?>) new Select().from(Independant.class).queryList();
        final List<HealthCenter> healthCenterList = new Select().from(HealthCenter.class).queryList();
        final List<Independant>  independantList  = new Select().from(Independant.class).queryList();
        customers.addAll(customersindeps);
        //Check if there is a network available
        if (CheckInternetConnexion.isNetworkAvailable(this.getActivity().getApplicationContext())) {
            callRest(customers, healthCenterList, independantList);
        }
        else {
            initAdapter(customers);
        }
    }

    /**
     * Called to do a rest call to have customer added by other commercials.
     *
     * @param customers
     */
    private void callRest(final List<Customer> customers, final List<HealthCenter> healthCenterList,
                          final List<Independant> independantList) {

        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance()
                .getCustomerService().getCustomers(this.idUser);

        call.enqueue(new Callback<ResponseRestCustomer>() {
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {

                if (response.errorBody() == null) {
                    List<HealthCenter> healthCenters = response.body().getHealthCenters();
                    List<Independant> independants = response.body().getIndependants();
                    if (healthCenters != null) {
                        for (HealthCenter healthCenter : response.body().getHealthCenters()) {
                            boolean alreadyOnTablet = false;
                            for (HealthCenter healthCenterTab : healthCenterList) {
                                if (healthCenterTab.getSiretNumber() == healthCenter.getSiretNumber()) {
                                    alreadyOnTablet = true;
                                }
                            }
                            if (!alreadyOnTablet) {
                                customers.add(healthCenter);
                            }

                        }
                    }
                    if (independants != null) {
                        for (Independant independant : response.body().getIndependants()) {
                            customers.add(independant);
                        }
                    }
                }
                initAdapter(customers);
            }

            @Override
            public void onFailure(Throwable t) {
                initAdapter(customers);
            }
        });
    }

    /**
     * Method called to initialiase the list of Customer
     *
     * @param customers
     */
    private void initAdapter(List<Customer> customers) {

        this.customers = customers;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_multiple_choice, getCustomersName(customers));
        customer.setAdapter(adapter);
        customer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    public static List<String> getCustomersName(List<Customer> customers) {
        List<String> customersName = new ArrayList<>();
        for(Customer customer : customers) {
            customersName.add(customer.getName());
        }
        return customersName;
    }

}
