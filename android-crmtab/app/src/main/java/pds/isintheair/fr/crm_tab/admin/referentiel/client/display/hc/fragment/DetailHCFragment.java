package pds.isintheair.fr.crm_tab.admin.referentiel.client.display.hc.fragment;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailHCFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailHCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailHCFragment extends Fragment {

    public static final String KEY_HC_ARGS = "HC";
    private HealthCenter healthCenter;

    @Bind(R.id.detail_hc_fragment_name)
    TextView name;

    @Bind(R.id.detail_hc_fragment_is_public)
    TextView isPublic;

    @Bind(R.id.detail_hc_fragment_siret_number)
    TextView siretNumber;

    @Bind(R.id.detail_hc_fragment_finess_number)
    TextView finessNumber;

    @Bind(R.id.detail_hc_fragment_adress)
    TextView adress;

    @Bind(R.id.detail_hc_fragment_zip_code)
    TextView zipCode;

    @Bind(R.id.detail_hc_fragment_bed_number)
    TextView bedNumber;

    @Bind(R.id.detail_hc_fragment_web_site)
    TextView webSite;

    @Bind(R.id.detail_hc_fragment_etablishment_type)
    TextView etablishmentType;

    @Bind(R.id.detail_hc_fragment_purshasing_central)
    TextView purshasingCentral;

    @Bind(R.id.detail_hc_fragment_holding)
    TextView holding;

    @Bind(R.id.detail_hc_fragment_difficulty_having_contact)
    TextView difficultyHavingContact;

    @Bind(R.id.detail_hc_fragment_service_building)
    TextView serviceBuilding;

    @Bind(R.id.detail_hc_fragment_map)
    MapView map;


    private OnFragmentInteractionListener mListener;

    public DetailHCFragment() {
        // Required empty public constructor
    }

    public static DetailHCFragment newInstance() {
        DetailHCFragment fragment = new DetailHCFragment();
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
        healthCenter = this.getArguments().getParcelable(KEY_HC_ARGS);
        View v = inflater.inflate(R.layout.fragment_detail_hc, container, false);
        ButterKnife.bind(this,v);
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
        void onFragmentInteraction(Uri uri);
    }

    private void initView() {
        if(healthCenter.isPublic()) isPublic.setText(R.string.display_hc_fragment_ispublic_yes_textview);
        else isPublic.setText(R.string.display_hc_fragment_ispublic_no_textview);
        name.setText(healthCenter.getName());
        siretNumber.setText(String.valueOf(healthCenter.getSiretNumber()));
        finessNumber.setText(String.valueOf(healthCenter.getFinessNumber()));
        adress.setText(healthCenter.getAdress());
        zipCode.setText(String.valueOf(healthCenter.getZipCode()));
        bedNumber.setText(String.valueOf(healthCenter.getBedNumber()));
        webSite.setText(healthCenter.getWebSite());
        etablishmentType.setText(healthCenter.getEtablishmentType());
        purshasingCentral.setText(healthCenter.getPurchasingCentral().getName());
        holding.setText(healthCenter.getHolding().getName());
        difficultyHavingContact.setText(String.valueOf(healthCenter.getDifficultyHavingContact()));
        serviceBuilding.setText(String.valueOf(healthCenter.getServiceBuildingImage()));
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

    @OnClick(R.id.detail_hc_fragment_web_site)
    public void openWebSite() {
        String url = FormatValidator.formatUrl(webSite.getText().toString());
        Intent intentWeb = new Intent(Intent.ACTION_VIEW);
        intentWeb.setData(Uri.parse(url));
        startActivity(intentWeb);
    }
}
