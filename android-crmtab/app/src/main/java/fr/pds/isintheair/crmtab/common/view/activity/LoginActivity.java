package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;

public class LoginActivity extends Activity {
    @Bind(R.id.txtPhone)
    EditText phone;
    @Bind(R.id.txtMdp)
    EditText mdp;

    @OnClick(R.id.btnSuivant)
    public void onButtonNextClick() {
        login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    public void login() {

        /*User user = null;
                Methods loginService =
                ServiceGenerator.createService(Methods.class, 123456789, "password");
        Call<User> call = null;
        try {
            call = loginService.basicLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
          user =  call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.v("login", user != null ? user.toString() : null);*/
    }
}