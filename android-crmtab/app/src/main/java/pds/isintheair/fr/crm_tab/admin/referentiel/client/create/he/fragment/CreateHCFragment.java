package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment;

import android.app.AlertDialog;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.message.MessageRest;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.message.ResponseRest;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.enums.EnumMessageCreateCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.RESTCustomerHandlerSingleton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pds.isintheair.fr.crm_tab.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateHCFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateHCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateHCFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    @Bind(R.id.create_he_fragment_name) EditText name;
    @Bind(R.id.create_he_fragment_is_public) RadioGroup isPublic;
    @Bind(R.id.create_he_fragment_siret_number) EditText siretNumber;
    @Bind(R.id.create_he_fragment_finess_number) EditText finesstNumber;
    @Bind(R.id.create_he_fragment_street_number) EditText streetNumber;
    @Bind(R.id.create_he_fragment_street_name) EditText streetName;
    @Bind(R.id.create_he_fragment_town_name) EditText town;
    @Bind(R.id.create_he_fragment_zip_code) EditText zipCode;
    @Bind(R.id.create_he_fragment_web_site) EditText webSite;
    @Bind(R.id.create_he_fragment_bed_number) EditText bedNumber;
    @Bind(R.id.create_he_fragment_etablishment_type) Spinner etablishmentType;
    @Bind(R.id.create_he_fragment_purshasing_central) Spinner purshasingCentral;
    @Bind(R.id.create_he_fragment_holding) Spinner holding;
    @Bind(R.id.create_he_fragment_difficulty_having_contact) RadioGroup difficultyHavingContact;
    @Bind(R.id.create_he_fragment_service_building) RadioGroup serviceBuilding;





    private OnFragmentInteractionListener mListener;

    public CreateHCFragment() {
        // Required empty public constructor
    }


    public static CreateHCFragment newInstance() {
        CreateHCFragment fragment = new CreateHCFragment();
        Bundle args = new Bundle();
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
        View v = inflater.inflate(R.layout.fragment_create_he, container, false);

        ButterKnife.bind(this, v);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @OnClick(R.id.create_he_fragment_validate_button)
    public void insertHE(final View view) {
        List<String> checkFields = checkField();
        if (!checkFields.isEmpty() && 1 == 2) {
            for (String error : checkFields) {
                Log.d("ErrorField", error);
            }

            String errorField = Arrays.toString(checkFields.toArray());
            errorField = errorField.substring(1, errorField.length() - 1);
            AlertDialog alertDialog =
                    new AlertDialog.Builder(this.getActivity()).create();
            alertDialog.setTitle(R.string.create_he_fragment_dialog_error_title);
            alertDialog.setMessage(errorField);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            final HealthCenter healthCenter = initHE();
            MessageRest messageRest = new MessageRest(1, healthCenter);

            Call<ResponseRest> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService()
                    .createHealthEtablishment(messageRest);

                call.enqueue(new Callback<ResponseRest>() {
                    @Override
                    public void onResponse(Response<ResponseRest> response, Retrofit retrofit) {
                        Log.d("IdCustomer", response.message());
                        Log.d("IdCustomer", response.headers().toString());
                        Log.d("IdCustomer", ""+response.code());
                        Log.d("IdCustomer :", ""+response.body().getIdUser());
                        healthCenter.setId(response.body().getIdUser());
                        healthCenter.save();
                        Snackbar.make(view, R.string.create_he_fragment_toast_validation, Snackbar.LENGTH_LONG);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });






        }

    }


   private int getIntFromRadiogroup(RadioGroup radioGroup) {
      return Integer.decode(((RadioButton) getActivity().findViewById(radioGroup.getCheckedRadioButtonId()))
              .getText().toString());
    }

    private List<String> checkField() {
        ArrayList<String> checkFields = new ArrayList<>();
        if(name.getText().toString().length() == 0) {
            checkFields.add(EnumMessageCreateCustomer.ERROR_NAME.toString());
        }
        String siretNumberString = siretNumber.getText().toString();
        if(!isSiretSyntaxValide(siretNumberString)) {
            checkFields.add(EnumMessageCreateCustomer.ERROR_SIRET.toString());
        }
        if(finesstNumber.getText().toString().length() != 9) {
            checkFields.add(EnumMessageCreateCustomer.ERROR_FINESS.toString());
        }
        if(streetNumber.getText().toString().length() == 0) {
            checkFields.add(EnumMessageCreateCustomer.ERROR_STREET_NUMBER.toString());
        }
        if (streetName.getText().toString().length() == 0) {
            checkFields.add(EnumMessageCreateCustomer.ERROR_STREET_NAME.toString());
        }
        if(town.getText().toString().length() == 0) {
            checkFields.add(EnumMessageCreateCustomer.ERROR_TOWN.toString());
        }
        if(zipCode.getText().toString().length() != 5) {
            checkFields.add(EnumMessageCreateCustomer.ERROR_ZIP_CODE.toString());
        }

        return checkFields;
    }



    public boolean isSiretSyntaxValide(String siret){

        if(siret.length() != 14) {
            return false;
        }

        int total = 0;
        int digit = 0;

        for (int i = 0; i<siret.length(); i++) {
            /** Recherche les positions impaires : 1er, 3è, 5è, etc... que l'on multiplie par 2*/

            if ((i % 2) == 0) {
                digit = Integer.parseInt(String.valueOf(siret.charAt(i))) * 2;
                /** if result > 9 => compose of 2 digits, every digits should be added and cannot be
                 * >19 , so we need to do : digit-9 */
                if (digit > 9) digit -= 9;
            }
            else digit = Integer.parseInt(String.valueOf(siret.charAt(i)));
            total += digit;
        }
        /** If sum is a multiple of 10, siret number is valid */
        if ((total % 10) == 0) return true;
        else return false;
    }

    private HealthCenter initHE() {
        HealthCenter healthCenter = new HealthCenter();
        healthCenter.setName(name.getText().toString());
        healthCenter.setSiretNumber(Long.decode(siretNumber.getText().toString()));
        healthCenter.setFinessNumber(Long.decode(finesstNumber.getText().toString()));
        healthCenter.setStreetNumber(Integer.decode(streetNumber.getText().toString()));
        healthCenter.setStreetName(streetName.getText().toString());
        healthCenter.setTown(town.getText().toString());
        healthCenter.setZipCode(Integer.decode(zipCode.getText().toString()));
        healthCenter.setBedNumber(Integer.decode(bedNumber.getText().toString()));
        healthCenter.setWebSite(webSite.getText().toString());
        if (((RadioButton) getActivity().findViewById(isPublic.getCheckedRadioButtonId())).getText().toString().equals("Yes"))
            healthCenter.setIsPublic(true);
        else healthCenter.setIsPublic(false);
        healthCenter.setDifficultyHavingContact(getIntFromRadiogroup(difficultyHavingContact));
        healthCenter.setServiceBuildingImage(getIntFromRadiogroup(serviceBuilding));
        return healthCenter;
    }






}
