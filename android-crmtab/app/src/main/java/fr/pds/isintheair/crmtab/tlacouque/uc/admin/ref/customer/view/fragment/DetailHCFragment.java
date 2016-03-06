package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.FormatValidator;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.receiver.NetworkReceiver;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.CheckInternetConnexion;

/**
 * Created by tlacouque on 01/01/2016.
 * Controller which is used to display an health center. He used to display the view, and to open
 * a web navigator if the user click on the website textview.
 */
public class DetailHCFragment extends Fragment implements DetailFragmentNetworkInterface {

    //Used to have the same key to pass healthcenter from customer list view holder to this fragment
    public static final String KEY_HC_ARGS = "HC";
    // START PERMISSION CHECK
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
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

    @Bind(R.id.detail_hc_fragment_scrollView)
    ScrollView scrollView;

    @Bind(R.id.detail_hc_fragment_map)
    MapView map;
    private HealthCenter healthCenter;
    private OnFragmentInteractionListener mListener;
    private MyLocationNewOverlay locationOverlay;
    private NetworkReceiver networkReceiver;

    public DetailHCFragment() {
        // Required empty public constructor
    }

    /**
     * Can be called when a new DetailHCFragment is needed
     *
     * @return DetailHCFragment
     */
    public static DetailHCFragment newInstance() {
        DetailHCFragment fragment = new DetailHCFragment();
        Bundle           args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        }

    }

    /**
     * Called when the fragment pass to the first plan,
     */
    @Override
    public void onResume() {
        super.onResume();
        networkReceiver = new NetworkReceiver(this);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(networkReceiver, intentFilter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        healthCenter = this.getArguments().getParcelable(KEY_HC_ARGS);
        View v = inflater.inflate(R.layout.fragment_detail_hc, container, false);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Initialise the view before displaying it with health center pass by the list
     */
    private void initView() {
        if (healthCenter.isPublic()) isPublic.setText(R.string.display_hc_fragment_ispublic_yes_textview);
        else isPublic.setText(R.string.display_hc_fragment_ispublic_no_textview);
        name.setText(healthCenter.getName());
        siretNumber.setText(String.valueOf(healthCenter.getSiretNumber()));
        finessNumber.setText(String.valueOf(healthCenter.getFinessNumber()));
        adress.setText(healthCenter.getAdress());
        zipCode.setText(String.valueOf(healthCenter.getZipCode()));
        bedNumber.setText(String.valueOf(healthCenter.getBedNumber()));
        webSite.setText(healthCenter.getWebSite());
        etablishmentType.setText(healthCenter.getEtablishmentType());

        if(healthCenter.getPurchasingCentral() != null) {
            purshasingCentral.setText(healthCenter.getPurchasingCentral().getName());
        }
        if(healthCenter.getHolding() != null) {
            holding.setText(healthCenter.getHolding().getName());
        }

        difficultyHavingContact.setText(String.valueOf(healthCenter.getDifficultyHavingContact()));
        serviceBuilding.setText(String.valueOf(healthCenter.getServiceBuildingImage()));
    }

    /**
     * Initialise the map in this view
     */
    private void initMap() {
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setUseDataConnection(true);
        if(CheckInternetConnexion.isNetworkAvailable(this.getContext())) {
            initOnlineMap();
        } else {
            initClientLocation(true);
        }

        map.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                }
                return map.onTouchEvent(event);
            }
        });
        map.invalidate();
    }

    /**
     * Init client when there is no internet connexion depend on the actual connexion
     * offline parameter
     * @param offline
     */
    public void initClientLocation(boolean offline) {
        IMapController mapController = map.getController();
        mapController.setZoom(15);
        GeoPoint startPoint = new GeoPoint(healthCenter.getLattitude(), healthCenter.getLongitude());
        if(offline) {
            mapController.setCenter(startPoint);
            if(locationOverlay != null) {
                map.getOverlays().remove(locationOverlay);
            }
        }
        Marker marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(android.R.drawable.star_on, null));
        marker.setTitle(healthCenter.getName());
        map.getOverlays().add(marker);
        map.invalidate();
    }

    /**
     * Initialise the map when there is an internet connexion
     */
    public void initOnlineMap() {
        locationOverlay = new MyLocationNewOverlay(getContext(),map);
        GpsMyLocationProvider gpsMyLocationProvider = new GpsMyLocationProvider(this.getContext());
        gpsMyLocationProvider.startLocationProvider(locationOverlay);
        gpsMyLocationProvider.setLocationUpdateMinTime(10);
        gpsMyLocationProvider.setLocationUpdateMinDistance(5);
        locationOverlay.enableMyLocation(gpsMyLocationProvider);
        locationOverlay.enableFollowLocation();
        map.getOverlays().add(locationOverlay);
        initClientLocation(false);
    }



    /**
     * Open a navigator and with the url pass by the website textview
     */
    @OnClick(R.id.detail_hc_fragment_web_site)
    public void openWebSite() {
        String url       = FormatValidator.formatUrl(webSite.getText().toString());
        Intent intentWeb = new Intent(Intent.ACTION_VIEW);
        intentWeb.setData(Uri.parse(url));
        startActivity(intentWeb);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION and WRITE_EXTERNAL_STORAGE
                Boolean location = perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                Boolean storage = perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (location && storage) {
                    // All Permissions Granted
                    Toast.makeText(getActivity().getApplicationContext(), "All permissions granted", Toast.LENGTH_SHORT).show();
                } else if (location) {
                    Toast.makeText(getActivity().getApplicationContext(),
                                   "Storage permission is required to store map tiles to reduce data usage and for offline usage.",
                                   Toast.LENGTH_LONG).show();
                } else if (storage) {
                    Toast.makeText(getActivity().getApplicationContext(),
                                   "Location permission is required to show the user's location on map.",
                                   Toast.LENGTH_LONG).show();
                } else { // !location && !storage case
                    // Permission Denied
                    Toast.makeText(getActivity().getApplicationContext(),
                                   "Storage permission is required to store map tiles to reduce data usage and for offline usage." +
                                           "\nLocation permission is required to show the user's location on map.",
                                   Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        String       message     = "OSMDroid permissions:";
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                                              Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            message += "\nStorage access to store map tiles.";
        }
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                                              Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            message += "\nLocation to show user location.";
        }
        if (!permissions.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
            String[] params = permissions.toArray(new String[permissions.size()]);
            requestPermissions(params, REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        } // else: We already have permissions, so handle as normal
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(networkReceiver);
    }
}
