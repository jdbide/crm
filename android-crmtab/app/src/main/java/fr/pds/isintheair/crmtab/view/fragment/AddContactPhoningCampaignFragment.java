package fr.pds.isintheair.crmtab.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobsandgeeks.saripaar.Validator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.helper.CustomerHelper;
import fr.pds.isintheair.crmtab.model.entity.Customer;
import fr.pds.isintheair.crmtab.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.model.entity.Independant;
import fr.pds.isintheair.crmtab.model.entity.MessageRestPhoningCampaign;
import fr.pds.isintheair.crmtab.model.entity.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.model.entity.ResponseRestPhoningCampaign;
import fr.pds.isintheair.crmtab.model.rest.RESTCustomerHandlerSingleton;
import fr.pds.isintheair.crmtab.model.rest.RESTPhoningCampaignHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 27/03/2016.
 */
public class AddContactPhoningCampaignFragment extends Fragment {

    @Bind(R.id.add_contact_phoning_campaign_fragment_list_contact)
    ListView customer;

    public static final String KEY_CUSTOMERS_ARGS = "CUSTOMERS";

    List<Customer> customerList;

    public AddContactPhoningCampaignFragment() {
        // Required empty public constructor
    }

    /**
     * Can be called when a new AddContactPhoningCampaignFragment is needed
     *
     * @return AddContactPhoningCampaignFragment
     */
    public static AddContactPhoningCampaignFragment newInstance() {
        AddContactPhoningCampaignFragment fragment = new AddContactPhoningCampaignFragment();
        Bundle args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      customerList = this.getArguments().getParcelableArrayList(KEY_CUSTOMERS_ARGS);
        callRest();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_contact_phoning_campaign, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    public void initContactList() {

    }


    /**
     * Called to do a rest call to have contact for customers selected.
     */
    private void callRest() {
        MessageRestPhoningCampaign message = new MessageRestPhoningCampaign();
        List<Independant> independants = new ArrayList<>();
        List<HealthCenter> healthCenters = new ArrayList<>();
        CustomerHelper.addHCIndependantIntoList(customerList, independants, healthCenters);
        ArrayList<String> customersId = new ArrayList<>();
        for(Independant independant : independants) {
            customersId.add(String.valueOf(independant.getSiretNumber()));
        }
        for(HealthCenter healthCenter : healthCenters) {
            customersId.add(String.valueOf(healthCenter.getSiretNumber()));
        }
        message.setCustomersId(customersId);
        Call<ResponseRestPhoningCampaign> call = RESTPhoningCampaignHandlerSingleton.getInstance()
                .getPhoningCampaignService().getContactsTest(message);

        call.enqueue(new Callback<ResponseRestPhoningCampaign>() {
            @Override
            public void onResponse(Response<ResponseRestPhoningCampaign> response, Retrofit retrofit) {

                if (response.errorBody() == null) {


                }
                //initAdapter(customers);
            }

            @Override
            public void onFailure(Throwable t) {
                //initAdapter(customers);
                Log.d("d","d");
            }
        });
    }

}
