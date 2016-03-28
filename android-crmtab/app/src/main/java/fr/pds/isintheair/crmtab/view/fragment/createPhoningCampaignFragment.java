package fr.pds.isintheair.crmtab.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.helper.CheckInternetConnexion;
import fr.pds.isintheair.crmtab.helper.CustomerHelper;
import fr.pds.isintheair.crmtab.model.dao.UserDAO;
import fr.pds.isintheair.crmtab.model.entity.Customer;
import fr.pds.isintheair.crmtab.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.model.entity.Independant;
import fr.pds.isintheair.crmtab.model.entity.IndependantType;
import fr.pds.isintheair.crmtab.model.entity.PhoningCampaign;
import fr.pds.isintheair.crmtab.model.entity.PhoningCampaignType;
import fr.pds.isintheair.crmtab.model.entity.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.model.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 26/03/2016.
 */
public class CreatePhoningCampaignFragment extends Fragment  implements Validator.ValidationListener {

    public String idUser = UserDAO.getCurrentUser().getId();

    @Bind(R.id.create_phoning_campaign_fragment_title)
    @NotEmpty
    EditText title;

    @Bind(R.id.create_phoning_campaign_fragment_objective)
    @NotEmpty
    EditText objective;

    @Bind(R.id.create_phoning_campaign_fragment_campaign_type)
    Spinner type;

    @Bind(R.id.create_phoning_campaign_fragment_list_customer)
    ListView customer;

    @Bind(R.id.create_phoning_campaign_fragment_contact_list_button)
    Button addContactButton;

    List<Customer> customers;

    List<Customer> customersAdded;

    Validator       validator;

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
        customersAdded = new ArrayList<Customer>();
        validator = new Validator(this);
        validator.setValidationListener(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_phoning_campaign, container, false);
        ButterKnife.bind(this, v);
        initCustomers();
        initSpinner();
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
                android.R.layout.simple_list_item_multiple_choice, CustomerHelper.getCustomersName(customers));
        customer.setAdapter(adapter);
        customer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ViewGroup.LayoutParams params = customer.getLayoutParams();

        // Set the height of the Item View
        params.height = 1000;
        customer.setLayoutParams(params);

    }



    private void initSpinner() {
        type.setAdapter(new ArrayAdapter<PhoningCampaignType>
                (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, PhoningCampaignType.values()));

    }

    @OnClick(R.id.create_phoning_campaign_fragment_contact_list_button)
    public void onButtonClick(final View view) {
        validator.validate(true);
    }

    @Override
    public void onValidationSucceeded() {
        SparseBooleanArray position = customer.getCheckedItemPositions();
        int len = customer.getCount();
        for (int i = 0; i < len; i++) {
            if (position.get(i)) {
                customersAdded.add(CustomerHelper.getCustomerFromName(
                        (String) customer.getItemAtPosition(i), customers));
            }
        }
        String typeString =  type.getSelectedItem().toString();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(AddContactPhoningCampaignFragment.KEY_CUSTOMERS_ARGS,
                (ArrayList<? extends Parcelable>) customersAdded);
        bundle.putParcelable(AddContactPhoningCampaignFragment.KEY_PHONING_CAMPAIGN_ARGS,
                new PhoningCampaign(title.getText().toString(),typeString,objective.getText().toString()));
        AddContactPhoningCampaignFragment addContactPhoningCampaignFragment = new AddContactPhoningCampaignFragment();
        addContactPhoningCampaignFragment.setArguments(bundle);

        ((AppCompatActivity) getActivity()).getFragmentManager().beginTransaction().addToBackStack("detailHc")
                .replace(R.id.container, addContactPhoningCampaignFragment).commit();
    }



    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }



}
