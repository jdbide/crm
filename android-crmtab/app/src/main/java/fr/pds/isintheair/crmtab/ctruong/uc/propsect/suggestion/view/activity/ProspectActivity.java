package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.config.ProspectRestConfig;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.domain.Prospect;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.rest.ProspectRetrofitAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ProspectActivity extends Activity {

    @Bind(R.id.btn_prospect)
    Button btn_prospect;
    @Bind(R.id.tv_prospect)
    TextView tv_prospect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospect);
        ButterKnife.bind(this);
        btn_prospect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ProspectRestConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                ProspectRetrofitAPI api = retrofit.create(ProspectRetrofitAPI.class);
                Call call = api.getProspect();
                call.enqueue(new Callback<Prospect>() {
                    @Override
                    public void onResponse(Response<Prospect> response, Retrofit retrofit) {
                        Prospect prospect = response.body();

                        if (prospect == null){
                            // 404 error or others
                            ResponseBody body = response.errorBody();
                            if (body!= null){
                                try {
                                    tv_prospect.setText("response body: " + body.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                tv_prospect.setText("response body = null");
                            }
                        } else {
                            tv_prospect.setText("Name : " + prospect.getName() + "\nSiret Number : " + prospect.getSiretNumber());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        tv_prospect.setText("t = " + t.getMessage());
                    }
                });
            }
        });
    }


}
