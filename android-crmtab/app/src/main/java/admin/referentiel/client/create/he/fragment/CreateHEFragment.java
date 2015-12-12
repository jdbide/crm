package admin.referentiel.client.create.he.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import butterknife.Bind;
import pds.isintheair.fr.crm_tab.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateHEFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateHEFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateHEFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    @Bind(R.id.create_he_fragment_name) EditText name;
    @Bind(R.id.create_he_fragment_name) RadioGroup isPublic;
    @Bind(R.id.create_he_fragment_name) EditText siretNumber;
    @Bind(R.id.create_he_fragment_name) EditText finesstNumber;
    @Bind(R.id.create_he_fragment_name) EditText streetNumber;
    @Bind(R.id.create_he_fragment_name) EditText streetName;
    @Bind(R.id.create_he_fragment_name) EditText town;
    @Bind(R.id.create_he_fragment_name) EditText zipCode;
    @Bind(R.id.create_he_fragment_name) EditText webSite;
    @Bind(R.id.create_he_fragment_name) EditText bedNumber;
    @Bind(R.id.create_he_fragment_name) Spinner etablishmentType;
    @Bind(R.id.create_he_fragment_name) Spinner purshasingCentral;
    @Bind(R.id.create_he_fragment_name) Spinner holding;
    @Bind(R.id.create_he_fragment_name) RadioGroup difficultyHavingContact;
    @Bind(R.id.create_he_fragment_name) RadioGroup serviceBuilding;



    private OnFragmentInteractionListener mListener;

    public CreateHEFragment() {
        // Required empty public constructor
    }


    public static CreateHEFragment newInstance() {
        CreateHEFragment fragment = new CreateHEFragment();
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
        return inflater.inflate(R.layout.fragment_create_he, container, false);
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


    public void insertHE(View view) {

    }
}
