package fr.pds.isintheair.crmtab;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.SerciceGenerator;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.displaycalls.CallLogRecyclerViewAdapter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static org.junit.Assert.assertTrue;

/**
 * Created by jbide on 02/03/2016.
 */
public class RegisterCallTest {

@Test
    public void testDisplayCallLogFragment(){
    List<Cra> listecra = new ArrayList<Cra>();
    CallLogRecyclerViewAdapter adapter = new CallLogRecyclerViewAdapter(listecra);

    Gson gson = new GsonBuilder().create();

    OkHttpClient httpClient = new OkHttpClient();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.getInstance().getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build();

    SerciceGenerator service = retrofit.create(SerciceGenerator.class);
    Call<List<Cra>> call = service.listcraforuser("1");
    call.enqueue(new Callback<List<Cra>>() {
        @Override
        public void onResponse(Response<List<Cra>> response, Retrofit retrofit) {
            assertTrue(response.isSuccess());
            //Toast.makeText(context, "status code" + response.message(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Throwable t) {
            //  Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
            Log.v("listcraforuser Failure", "msg = " + t.getMessage());
            //Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}
    

}
