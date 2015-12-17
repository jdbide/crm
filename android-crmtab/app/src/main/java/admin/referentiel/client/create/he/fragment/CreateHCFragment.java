package admin.referentiel.client.create.he.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import admin.referentiel.client.create.he.entities.HealthEtablishment;
import admin.referentiel.client.enums.EnumMessageCreateCustomer;
import admin.referentiel.client.rest.RESTCustomerHandlerSingleton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pds.isintheair.fr.crm_tab.R;
import retrofit.Call;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateHCFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateHCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateHCFragment extends Fragment  {
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
    public void insertHE(View view) {
        List<String> checkFields = checkField();
        if(!checkFields.isEmpty() && 1 == 2) {
            for(String error : checkFields) {
                Log.d("ErrorField", error);
            }

            String errorField = Arrays.toString(checkFields.toArray());
            errorField = errorField.substring(1,errorField.length()-1);
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
        }   else {
            HealthEtablishment healthEtablishment = initHE();

     Call<String> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService()
             .createHealthEtablishment(1,healthEtablishment);
        String reponse = "";
        String idCustomer = "";
        try {
            reponse = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(reponse);
      idCustomer = jsonObject.getString("idCustomer");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        healthEtablishment.setId(Integer.decode(idCustomer));
        healthEtablishment.save();

        Toast.makeText(this.getActivity().getApplicationContext(),
                R.string.create_he_fragment_toast_validation, Toast.LENGTH_SHORT).show();



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



    private boolean isSiretSyntaxValide(String siret){

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

    private HealthEtablishment initHE() {
        HealthEtablishment healthEtablishment = new HealthEtablishment();
        healthEtablishment.setName(name.getText().toString());
        healthEtablishment.setSiretNumber(Long.decode(siretNumber.getText().toString()));
        healthEtablishment.setFinessNumber(Long.decode(finesstNumber.getText().toString()));
        healthEtablishment.setStreetNumber(Integer.decode(streetNumber.getText().toString()));
        healthEtablishment.setStreetName(streetName.getText().toString());
        healthEtablishment.setTown(town.getText().toString());
        healthEtablishment.setZipCode(Integer.decode(zipCode.getText().toString()));
        healthEtablishment.setBedNumber(Integer.decode(bedNumber.getText().toString()));
        healthEtablishment.setWebSite(webSite.getText().toString());
        if (((RadioButton) getActivity().findViewById(isPublic.getCheckedRadioButtonId())).getText().toString().equals("Yes"))
            healthEtablishment.setIsPublic(true);
        else healthEtablishment.setIsPublic(false);
        healthEtablishment.setDifficultyHavingContact(getIntFromRadiogroup(difficultyHavingContact));
        healthEtablishment.setServiceBuildingImage(getIntFromRadiogroup(serviceBuilding));
        return healthEtablishment;
    }





}
