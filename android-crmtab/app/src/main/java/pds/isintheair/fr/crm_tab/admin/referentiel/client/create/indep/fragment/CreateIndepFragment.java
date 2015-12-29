package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.SiretValidator;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding$Table;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral$Table;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.enums.EtablishmentType;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Company;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Company$Table;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Independant;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Specialty;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Specialty$Table;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.enums.IndependantType;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.fragment.ListCustomerFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.MessageRestCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.ResponseRestCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by user on 27/12/2015.
 */
public class CreateIndepFragment extends Fragment implements Validator.ValidationListener {


    @Bind(R.id.create_indep_fragment_name)
    @Order(1)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_name)
    EditText name;


    @Bind(R.id.create_indep_fragment_siret_number)
    @Order(2)
    @Length(min = 14, max = 14,messageResId = R.string.create_he_fragment_error_siret_empty)
    EditText siretNumber;

    @Bind(R.id.create_indep_fragment_finess_number)
    @Order(3)
    @Length(min = 9, max = 9, messageResId = R.string.create_he_fragment_error_finess)
    EditText finesstNumber;

    @Bind(R.id.create_indep_fragment_street_number)
    @Order(4)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_street_number)
    EditText streetNumber;

    @Bind(R.id.create_indep_fragment_street_name)
    @Order(5)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_street_name)
    EditText streetName;

    @Bind(R.id.create_indep_fragment_town_name)
    @Order(6)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_town)
    EditText town;


    @Bind(R.id.create_indep_fragment_zip_code)
    @Order(7)
    @Length(min = 5, max = 5, messageResId = R.string.create_he_fragment_error_zip_code)
    EditText zipCode;


    @Bind(R.id.create_indep_fragment_web_site) EditText webSite;
    @Bind(R.id.create_indep_fragment_independant_type)
    Spinner independantType;
    @Bind(R.id.create_indep_fragment_company) Spinner company;
    @Bind(R.id.create_indep_fragment_specialty) Spinner specialty;
    @Bind(R.id.create_indep_fragment_long_term_fidelity) RadioGroup longTermFidelity;


    List<Specialty> specialties;
    List<Company> companies;
    Validator validator;
    View view;
    boolean createCalled;


    private OnFragmentInteractionListener mListener;

    public CreateIndepFragment() {
        // Required empty public constructor
    }


    public static CreateIndepFragment newInstance() {
        CreateIndepFragment fragment = new CreateIndepFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_indep, container, false);
        ButterKnife.bind(this, v);
        initSpinner();


        validator.put(siretNumber, new QuickRule<EditText>() {
            @Override
            public boolean isValid(EditText view) {
                return SiretValidator.isSiretSyntaxValide(view.getText().toString());
            }

            @Override
            public String getMessage(Context context) {
                return getString(R.string.create_he_fragment_error_siret);
            }


        });

        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                List<Company> companies = new Select().from(Company.class)
                        .where(Condition.column(Company$Table.ZIPCODE)
                                .like(zipCode.getText().toString().concat("%")))
                        .or(Condition.column(Company$Table.ID).eq(1)).queryList();
                company.setAdapter(new ArrayAdapter<Company>
                        (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, companies));
            }
        });


        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @OnClick(R.id.create_indep_fragment_validate_button)
    public void insertIndep(final View view) {
        this.view = view;

        validator.validate(true);

    }

    private int getIntFromRadiogroup(RadioGroup radioGroup) {
        return Integer.decode(((RadioButton) getActivity().findViewById(radioGroup.getCheckedRadioButtonId()))
                .getText().toString());
    }

    private Independant initIndep() {

        Independant independant = new Independant();
        independant.setName(name.getText().toString());
        independant.setSiretNumber(Long.decode(siretNumber.getText().toString()));
        independant.setFinessNumber(Long.decode(finesstNumber.getText().toString()));
        independant.setStreetNumber(Integer.decode(streetNumber.getText().toString()));
        independant.setStreetName(streetName.getText().toString());
        independant.setTown(town.getText().toString());
        independant.setZipCode(Integer.decode(zipCode.getText().toString()));
        independant.setWebSite(webSite.getText().toString());
        independant.setOrigin("Prospection");
        independant.setIndependantType(independantType.getSelectedItem().toString());
        independant.setLongTermFidelity(getIntFromRadiogroup(longTermFidelity));
        Specialty specialtyInit = new Select().from(Specialty.class)
                .where(Condition.column(Specialty$Table.NAME).eq(specialty.getSelectedItem().toString())).querySingle();
        Company companyInit = new Select().from(Company.class)
                .where(Condition.column(Company$Table.NAME)
                        .eq(company.getSelectedItem().toString())).querySingle();
        independant.setCompanyId(companyInit.getId());
        independant.setSpecialtyId(specialtyInit.getId());
        return independant;
    }

    private void initSpinner() {
        companies = new Select(Company$Table.NAME).from(Company.class).queryList();

        company.setAdapter(new ArrayAdapter<Company>
                (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view,companies));
        specialties = new Select(Specialty$Table.NAME)
                .from(Specialty.class).queryList();
        specialty.setAdapter(new ArrayAdapter<Specialty>
                (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view,specialties));
        independantType.setAdapter(new ArrayAdapter<IndependantType>
                (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, IndependantType.values()));

    }

    @Override
    public void onValidationSucceeded() {
        final Independant independant = initIndep();

        MessageRestCustomer messageRestCustomer = new MessageRestCustomer(1, independant);
        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService()
                .createIndependant(messageRestCustomer);
        call.enqueue(new Callback<ResponseRestCustomer>() {
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                if(response.body().getIsInserted()) {
                    independant.save();
                    createCalled = true;
                }

                ListCustomerFragment listCustomerFragment = new ListCustomerFragment();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.customer_list_title);
                getFragmentManager().beginTransaction()
                        .replace(R.id.create_customer_fragment_container, listCustomerFragment).commit();
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        String errorString = "";
        for(ValidationError error : errors) {
            errorString = errorString + error.getCollatedErrorMessage
                    (getActivity().getApplicationContext()) +".\n";
        }
        AlertDialog alertDialog =
                new AlertDialog.Builder(this.getActivity()).create();
        alertDialog.setTitle(R.string.create_he_fragment_dialog_error_title);
        alertDialog.setMessage(errorString);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    @Override
    public void onStop() {
        super.onStop();
        if(createCalled) Snackbar.make(view, R.string.create_he_fragment_toast_validation, Snackbar.LENGTH_LONG).show();
    }



}
