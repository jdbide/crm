package pds.isintheair.fr.crm_tab.registercall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.registercall.Rest.CraServiceInterface;
import pds.isintheair.fr.crm_tab.registercall.Rest.CreateCraResponse;
import pds.isintheair.fr.crm_tab.registercall.Rest.Model.Cra;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class AddLogFragment extends Fragment {

    private String BASE_URL = "locahost";

    static AddLogFragment newInstance(int num) {
        AddLogFragment f = new AddLogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_log_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.addlogmenu, menu);

    }

    public void go(View v){

        


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CraServiceInterface service = retrofit.create(CraServiceInterface.class);
        Call<Cra> call = service.createcra();
        //asynchronous call
        call.enqueue(new Callback<CreateCraResponse>() {
            @Override
            public void onResponse(Response<CreateCraResponse> response) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    CreateCraResponse result = response.body();
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }


        }
    }

}
