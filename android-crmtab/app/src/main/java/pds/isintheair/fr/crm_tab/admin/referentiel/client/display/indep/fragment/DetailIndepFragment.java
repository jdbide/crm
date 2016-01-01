package pds.isintheair.fr.crm_tab.admin.referentiel.client.display.indep.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.FormatValidator;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Independant;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailIndepFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailIndepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailIndepFragment extends Fragment {

    public static final String KEY_INDEP_ARGS = "INDEP";
    private Independant independant;

    @Bind(R.id.detail_indep_fragment_name)
    TextView name;

    @Bind(R.id.detail_indep_fragment_siret_number)
    TextView siretNumber;

    @Bind(R.id.detail_indep_fragment_finess_number)
    TextView finessNumber;

    @Bind(R.id.detail_indep_fragment_adress)
    TextView adress;

    @Bind(R.id.detail_indep_fragment_zip_code)
    TextView zipCode;

    @Bind(R.id.detail_indep_fragment_web_site)
    TextView webSite;

    @Bind(R.id.detail_indep_fragment_independant_type)
    TextView independantType;

    @Bind(R.id.detail_indep_fragment_company)
    TextView company;

    @Bind(R.id.detail_indep_fragment_specialty)
    TextView specialty;

    @Bind(R.id.detail_indep_fragment_long_term_fidelisation)
    TextView longTermFidelisation;

    @Bind(R.id.detail_indep_fragment_map)
    MapView map;


    private OnFragmentInteractionListener mListener;

    public DetailIndepFragment() {
        // Required empty public constructor
    }

    public static DetailIndepFragment newInstance(String param1, String param2) {
        DetailIndepFragment fragment = new DetailIndepFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        independant = this.getArguments().getParcelable(KEY_INDEP_ARGS);
        View v = inflater.inflate(R.layout.fragment_detail_indep, container, false);
        ButterKnife.bind(this, v);
        initView();
        initMap();
        return v;
    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void initView() {
        name.setText(independant.getName());
        siretNumber.setText(String.valueOf(independant.getSiretNumber()));
        finessNumber.setText(String.valueOf(independant.getFinessNumber()));
        adress.setText(independant.getAdress());
        zipCode.setText(String.valueOf(independant.getZipCode()));
        webSite.setText(independant.getWebSite());
        independantType.setText(independant.getIndependantType());
        company.setText(independant.getCompany().getName());
        specialty.setText(independant.getSpecialty().getName());
        longTermFidelisation.setText(String.valueOf(independant.getLongTermFidelity()));
    }

    private void initMap() {
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setUseDataConnection(true);
        IMapController mapController = map.getController();
        mapController.setZoom(10);
        GeoPoint startPoint = new GeoPoint(48.8534100,2.3488000);
        mapController.setCenter(startPoint);
        map.invalidate();
    }

    @OnClick(R.id.detail_indep_fragment_web_site)
    public void openWebSite() {
        String url = FormatValidator.formatUrl(webSite.getText().toString());
        Intent intentWeb = new Intent(Intent.ACTION_VIEW);
        intentWeb.setData(Uri.parse(url));
        startActivity(intentWeb);
    }


}
