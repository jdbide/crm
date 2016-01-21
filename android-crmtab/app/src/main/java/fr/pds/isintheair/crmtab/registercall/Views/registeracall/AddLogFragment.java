package fr.pds.isintheair.crmtab.registercall.Views.registeracall;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.registercall.Objects.Singleton;
import fr.pds.isintheair.crmtab.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.registercall.Rest.ServiceGenerator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AddLogFragment extends Fragment {

    @Bind(R.id.formtitle)
    TextView formtitle;
    @Bind(R.id.edittextcontatctname)
    EditText contactname;
    @Bind(R.id.edittextclientname)
    EditText clientname;
    @Bind(R.id.edittextcontatctnumber)
    EditText contactnumber;
    @Bind(R.id.edittextduration)
    EditText duration;
    @Bind(R.id.edittextcomments)
    EditText comments;
    @Bind(R.id.edittextsubject)
    EditText subject;
    @Bind(R.id.edittextdate)
    EditText date;
    @Bind(R.id.edittextiduser)
    EditText iduser;
    @Bind(R.id.edittextcalltype)
    EditText calltype;
    @Bind(R.id.buttonregistercra)
    Button   validation;


    static AddLogFragment newInstance(String idcontact, String date, String duration, String calltype) {
        AddLogFragment f = new AddLogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("idcontact", idcontact);
        args.putString("duration", duration);
        args.putString("date", date);
        args.putString("calltype", calltype);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide  keyboard
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_informations_fragment, container, false);
        ButterKnife.bind(this, view);


        formtitle.setText("Ajout d'un compte-rendu");
        contactnumber.setText(getArguments().getString("idcontact"));
        date.setText(getArguments().getString("date"));
        duration.setText(getArguments().getString("duration"));
        calltype.setText(getArguments().getString("calltype"));
        iduser.setText("1");
        //idcontact.setText(getArguments().getString("idcontact"));
        //should be taken from a request
        contactname.setText("nom contactttt");
        clientname.setText("nom client");

        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendForm();
            }
        });
        return view;
    }

    private void sendForm() {

        Cra newCra = new Cra();
        newCra.setCalltype(calltype.getText().toString());
        newCra.setClientname(clientname.getText().toString());
        newCra.setComments(comments.getText().toString());
        newCra.setContactname(contactname.getText().toString());
        newCra.setDate(date.getText().toString());
        newCra.setDuration(Long.parseLong(String.valueOf(duration.getText())));
        newCra.setIdcontact(Long.parseLong(contactnumber.getText().toString()));
        newCra.setSubject(subject.getText().toString());
        newCra.setIduser(Long.parseLong(iduser.getText().toString()));

        //Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        // add logging as last interceptor
        httpClient.interceptors().add(logging);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        ServiceGenerator service = retrofit.create(ServiceGenerator.class);
        Call<Boolean>    call    = service.createcra(newCra);


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        ServiceGenerator service = retrofit.create(ServiceGenerator.class);
        Call<String> call = service.createcro(new Cro("o"));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    Log.v("ok", "ok");

                } else {
                    Log.v("ok", response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    Log.v("ok", "cra enregistre");

                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.v("rest", "no rep" + response.message());
                    Toast.makeText(getActivity(), "no rep", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //  Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
                Log.v("Failure", t.getMessage());
            }
        });
        //Redirect to Call log list view
        //((RegisterCallActivity)(getActivity())).showCallLogList();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
