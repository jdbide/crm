package pds.isintheair.fr.crmtab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends Activity {

    @Bind(R.id.txtPhone)
    EditText phone;
    @Bind(R.id.txtMdp) EditText mdp;
    @Bind(R.id.btnSuivant)
    Button suiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        suiv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               login();
        }
    });
    }

    public void login(){

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