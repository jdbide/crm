package pds.isintheair.fr.crm_tab.registercall;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.registercall.Rest.CraServiceInterface;
import pds.isintheair.fr.crm_tab.registercall.Rest.Model.Cra;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class AddLogFragment extends Fragment {



    @Bind(R.id.edittextcontatctname) EditText contactname;
    @Bind(R.id.edittextclientname) EditText clientname;
    @Bind(R.id.edittextcontatctnumber) EditText contactnumber;
    @Bind(R.id.edittextduration) EditText duration;
    @Bind(R.id.edittextcomments) EditText comments;
    @Bind(R.id.edittextsubject) EditText subject;
    @Bind(R.id.edittextdate) EditText date;
    @Bind(R.id.edittextidcontact) EditText idcontact;
    @Bind(R.id.edittextiduser) EditText iduser;
    @Bind(R.id.edittextcalltype) EditText calltype;
    @Bind(R.id.buttonregistercra)  Button validation;


    private String BASE_URL = "http://192.168.1.16:8080/api/";

    static AddLogFragment newInstance(int num) {
        AddLogFragment f = new AddLogFragment();
       /* // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);*/
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide  keyboard
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_log_fragment, container,false);
        ButterKnife.bind(this, view);
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendForm();
            }
        });
        return view;
    }

    private void sendForm() {

        Cra newCra = new Cra(Integer.parseInt(String.valueOf(iduser.getText()))
                ,Integer.parseInt(String.valueOf(idcontact.getText()))
                ,String.valueOf(clientname.getText())
                ,String.valueOf(contactname.getText())
                ,String.valueOf(comments.getText())
                ,String.valueOf(subject.getText())
                , String.valueOf(date.getText())
                ,Integer.parseInt(String.valueOf(duration.getText())));

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CraServiceInterface service = retrofit.create(CraServiceInterface.class);
        Call<Boolean> call = service.createcra(newCra);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    //String result = response.message();
                    //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                    if(response.body()) Log.v("ok", "cra enregistre");
                    else Log.v("ok", "erreur bdd");

                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.v("rest","no rep");
                    Toast.makeText(getActivity(), "no rep", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
                Log.v("Failure",t.getMessage());
            }
        });


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



    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
