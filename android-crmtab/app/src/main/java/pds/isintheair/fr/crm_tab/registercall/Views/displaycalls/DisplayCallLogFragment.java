package pds.isintheair.fr.crm_tab.registercall.Views.displaycalls;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.registercall.Objects.Singleton;
import pds.isintheair.fr.crm_tab.registercall.Rest.Model.Cra;
import pds.isintheair.fr.crm_tab.registercall.Rest.ServiceGenerator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DisplayCallLogFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount;
    private List<Cra> listecra;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DisplayCallLogFragment() {
    }

    // initialization
    public static DisplayCallLogFragment newInstance(int columnCount) {
        DisplayCallLogFragment fragment = new DisplayCallLogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listecra = new ArrayList<Cra>();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        Gson gson = new GsonBuilder().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        // add logging as last interceptor
        httpClient.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        ServiceGenerator service = retrofit.create(ServiceGenerator.class);
        Call<List<Cra>> call = service.listcraforuser(1);

        call.enqueue(new Callback<List<Cra>>() {
            @Override
            public void onResponse(Response<List<Cra>> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    List<Cra> liste = response.body();
                    for(int i =0;i<liste.size();i++) {
                        Cra cra = new Cra();
                        cra.setCalltype(liste.get(i).getCalltype());
                        cra.setClientname(liste.get(i).getClientname());
                        cra.setComments(liste.get(i).getComments());
                        cra.setContactname(liste.get(i).getContactname());
                        cra.setDate(liste.get(i).getDate());
                        cra.setDuration(liste.get(i).getDuration());
                        cra.setIdcontact(liste.get(i).getIdcontact());
                        cra.setSubject(liste.get(i).getSubject());
                        cra.setIduser(liste.get(i).getIduser());
                        listecra.add(cra);
                    }
                } else {
                    Log.v("listcraforuser", "no rep");
                    //Toast.makeText(getActivity(), "no rep", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //  Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
                Log.v("listcraforuser Failure",t.getMessage());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.callloglist_container, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //add line divider
            recyclerView.addItemDecoration(new LineDivider(
                    getActivity()));

            recyclerView.setAdapter(new CallLogRecyclerViewAdapter(listecra, mListener));
        }
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Cra item);
    }
}
